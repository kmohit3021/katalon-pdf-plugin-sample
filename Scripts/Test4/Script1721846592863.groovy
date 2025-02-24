import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;


String imagePath = "/Users/mohit/git/katalon-studio-pdf-keywords-plugin/Data Files/temp/pdf_validaton_2.png";

// Create an instance of Tesseract
ITesseract tesseract = new Tesseract();

// Set the path to the tessdata directory (if not in default location)
tesseract.setDatapath("/Users/mohit/git/katalon-studio-pdf-keywords-plugin/Data Files/tessdata"); // Make sure to set this to your tessdata directory

// Optionally, set the language if different from English
// tesseract.setLanguage("eng");

try {
	// Perform OCR on the image
	String extractedText = tesseract.doOCR(new File(imagePath));
	
	// Save the extracted text to a variable
	String ocrText = extractedText.trim();
	
	// Print the extracted text
	System.out.println("Extracted Text: " + ocrText);
	
	// You can now use the `ocrText` variable as needed
} catch (TesseractException e) {
	e.printStackTrace();
}