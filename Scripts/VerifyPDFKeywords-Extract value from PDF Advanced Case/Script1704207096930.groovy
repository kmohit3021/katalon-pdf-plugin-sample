import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import java.util.regex.Matcher as Matcher
import java.util.regex.Pattern as Pattern

String path = RunConfiguration.getProjectDir() + '/Data Files/'

//Get the page count of the document
CustomKeywords.'com.kms.katalon.keyword.pdf.PDF.getPageNumber'(path + 'Health Plan Snapshot.pdf')

//Get the content of the document as plain text
String matchedLine = CustomKeywords.'com.kms.katalon.keyword.pdf.PDF.getTextFromPage'(path + 'Health Plan Snapshot.pdf', 
    1)

String inputString

Pattern pattern = Pattern.compile('Inpatient PMPM.*?%')

Matcher matcher = pattern.matcher(matchedLine)

// Find the first match
if (matcher.find()) {
    // Print the matched line
    inputString = matcher.group()

    System.out.println(inputString)
} else {
    System.out.println('No match found')
}

Pattern pattern2 = Pattern.compile('Inpatient PMPM (.*) (.*) (.*) (.*)')

Matcher matcher2 = pattern2.matcher(inputString)

if (matcher2.matches()) {
    // Extract the PMPM value
    pmpmValue1 = matcher2.group(1)
	pmpmValue2 = matcher2.group(2)
	pmpmValue3 = matcher2.group(3)
	pmpmValue4 = matcher2.group(4)

    println('Extracted PMPM value1: ' + pmpmValue1)
	println('Extracted PMPM value2: ' + pmpmValue2)
	println('Extracted PMPM value3: ' + pmpmValue3)
	println('Extracted PMPM value4: ' + pmpmValue4)
} else {
    System.out.println('Pattern not matched. Unable to extract PMPM value.')
}