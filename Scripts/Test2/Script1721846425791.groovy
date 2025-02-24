import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import org.apache.pdfbox.Loader as Loader
import java.io.IOException as IOException
import java.io.StringWriter as StringWriter
import org.apache.pdfbox.pdmodel.PDDocument as PDDocument
import org.apache.pdfbox.text.PDFTextStripper as PDFTextStripper

String path = RunConfiguration.getProjectDir() + '/Data Files/'
PDDocument doc = Loader.loadPDF(new File(path + 'pdf_validaton.pdf'))
String extractedText = getText(doc)
println(extractedText)
doc.close()

String getText(PDDocument doc) throws IOException {
    StringWriter outputStream = new StringWriter()
    PDFTextStripper pdfStripper = new PDFTextStripper()

    pdfStripper.setSortByPosition(true)
    pdfStripper.setStartPage(0)
    pdfStripper.setEndPage(doc.getNumberOfPages())

    pdfStripper.writeText(doc, outputStream)
    String text = outputStream.toString().trim()

    // If text extraction returns empty, try extracting each page separately
    if (text.isEmpty()) {
        StringBuilder pageText = new StringBuilder()
        for (int i = 0; i < doc.getNumberOfPages(); i++) {
            pdfStripper.setStartPage(i + 1)
            pdfStripper.setEndPage(i + 1)
            outputStream = new StringWriter()
            pdfStripper.writeText(doc, outputStream)
            pageText.append(outputStream.toString().trim()).append("\n")
        }
        text = pageText.toString().trim()
    }
    
    return text
}