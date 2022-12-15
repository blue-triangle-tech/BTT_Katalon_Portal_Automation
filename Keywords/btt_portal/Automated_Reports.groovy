package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



public class Automated_Reports {

	public static select_automated_report(String report_data_type, String report_name) {

		WebUI.click(findTestObject('Object Repository/Automated Reports/Create Report'))

		TestObject data_type = new TestObject('data type')
		data_type.addProperty('id', ConditionType.EQUALS, report_data_type)

		TestObject report = new TestObject('report')
		report.addProperty('data-report-type', ConditionType.EQUALS, report_name)

		WebUI.click(data_type)
		WebUI.click(report)
		return report_name
	}

	public static report_settings(String report_name, String subject_line, String time_period = '1', String time_unit = 'days') {

		WebUI.setText(findTestObject('Object Repository/Automated Reports/Set Report Name'), report_name)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Automated Reports/Report Interval Unit'), time_unit, true)
		String text = WebUI.getAttribute(findTestObject('Object Repository/Automated Reports/Report Interval Unit'), 'value')

		println text
		WebUI.setText(findTestObject('Object Repository/Automated Reports/Report Interval Period'), time_period)
		String text2 = WebUI.getAttribute(findTestObject('Object Repository/Automated Reports/Report Interval Period'), 'value')
		println text2 + ' ' + text
		WebUI.setText(findTestObject('Object Repository/Automated Reports/Subject Line'), subject_line)
		WebUI.click(findTestObject('Object Repository/Automated Reports/Next'))
	}

	public static report_type() {
	}

	public static report_filters() {

		WebUI.click(findTestObject('Object Repository/Automated Reports/Create'))
	}

	public static validate_report_row(String report_name) {

		WebDriver driver = DriverFactory.getWebDriver()
		WebElement report_table = driver.findElement(By.xpath('//*[@id="reports-table"]'))

		List<WebElement> report_rows = report_table.findElements(By.tagName('tr'))
		int rows_count = report_rows.size()

		for (int row = 1; row <=rows_count; row++) {
			TestObject report_column = new TestObject('columns')
			report_column.addProperty('xpath', ConditionType.EQUALS, '//*[@id="reports-table"]/tbody/tr['+row+']/td[4]')

			String report_name_text = WebUI.getText(report_column)

			if (report_name_text == report_name) {
				return row
			}
		}
	}

	public static generate_report(String report_name) {

		int row = validate_report_row(report_name)

		TestObject generate_report = new TestObject('generate report button')
		generate_report.addProperty('xpath', ConditionType.EQUALS, '//*[@id="reports-table"]/tbody/tr['+row+']/td[8]/button')

		if (WebUI.verifyElementPresent(generate_report, 10)) {

			WebUI.click(generate_report)
			WebUI.click(findTestObject('Object Repository/Automated Reports/Generate Report'))
			WebUI.click(findTestObject('Object Repository/Automated Reports/OK'))
		}
		else {
			WebUI.click(findTestObject('Object Repository/Automated Reports/Next Page'))
			WebUI.click(generate_report)
			WebUI.click(findTestObject('Object Repository/Automated Reports/Generate Report'))
			WebUI.click(findTestObject('Object Repository/Automated Reports/OK'))
		}
	}
	
	public static verify_report_email(String report_name) {
		
		RequestObject get_email = findTestObject('Object Repository/Microsoft Graph/Get Email')
		String endpoint = 'https://graph.microsoft.com/v1.0/me/messages?'
		get_email.setRestUrl(endpoint)
		String url = get_email.getRestUrl()

		ResponseObject respObj = WS.sendRequest(get_email)
		String email_text = respObj.getResponseBodyContent()
		
		List missing_emails = []

		
		if (email_text.contains(report_name)) {
			println report_name + " email has been found."
		}
		else {
			missing_emails.add(report_name)
			
		}
		for (email in missing_emails) {
			WebUI.delay(10)
			ResponseObject respObj1 = WS.sendRequest(get_email)
			String email_text1 = respObj1.getResponseBodyContent()
			if (email_text1.contains(report_name)) {
				
				println report_name + " email has been found on the 2nd check."
			}
			else {
				KeywordUtil.markWarning("No email has been found for " + report_name)
			}
			
		}
	}
}