import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import org.apache.pdfbox.Loader as Loader
import java.io.File
import java.io.IOException
import java.io.StringWriter
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

String path = RunConfiguration.getProjectDir() + '/Data Files/'
PDDocument doc = Loader.loadPDF(new File(path + 'pdf_validaton.pdf'))
String extractedText = getText(doc)
println(extractedText)
doc.close()

String getText(PDDocument doc) throws IOException {
    StringWriter outputStream = new StringWriter()
    PDFTextStripper pdfStripper = new PDFTextStripper()
    
    // Set additional properties
    pdfStripper.setSortByPosition(true) // Try sorting by text position
    pdfStripper.setStartPage(1) // Extract text from all pages (1-indexed)
    pdfStripper.setEndPage(doc.getNumberOfPages())
    
    pdfStripper.writeText(doc, outputStream)
    String text = outputStream.toString().trim()
    
    // If text extraction returns empty, try extracting each page separately
    if (text.isEmpty()) {
        StringBuilder pageText = new StringBuilder()
        for (int i = 1; i <= doc.getNumberOfPages(); i++) { // 1-indexed pages
            pdfStripper.setStartPage(i)
            pdfStripper.setEndPage(i)
            outputStream = new StringWriter()
            pdfStripper.writeText(doc, outputStream)
            pageText.append(outputStream.toString().trim()).append("\n")
        }
        text = pageText.toString().trim()
    }
    
    return text
}
