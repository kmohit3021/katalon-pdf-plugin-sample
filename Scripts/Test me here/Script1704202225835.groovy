import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

String path = RunConfiguration.getProjectDir() + '/Data Files/ManuLife_PDFs/'
boolean result = CustomKeywords.'com.kms.katalon.keyword.pdf.PDF.compareAllPages'(path+ "CASRNB_1.pdf",
		path + "CASRNB_2.pdf",[".?\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}(\\. \\d+)?",".00","Peso Policies","Dollar Policies","Pending Cases","Policy Release",":"])
if (result) {
	println("Match found")
}
else {
	KeywordUtil.markFailedAndStop("PDFs are different")
}