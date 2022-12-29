package btt_portal

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.googlecode.javacv.FrameGrabber.Array
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonSlurper



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

	public static report_settings(String report_name, String subject_line, String time_period = '1', String time_unit = 'days', String screenshot = 'No') {

		WebUI.setText(findTestObject('Object Repository/Automated Reports/Set Report Name'), report_name)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Automated Reports/Report Interval Unit'), time_unit, true)
		String text = WebUI.getAttribute(findTestObject('Object Repository/Automated Reports/Report Interval Unit'), 'value')

		WebUI.setText(findTestObject('Object Repository/Automated Reports/Report Interval Period'), time_period)
		String text2 = WebUI.getAttribute(findTestObject('Object Repository/Automated Reports/Report Interval Period'), 'value')

		WebUI.setText(findTestObject('Object Repository/Automated Reports/Subject Line'), subject_line)

		WebUI.selectOptionByLabel(findTestObject('Object Repository/Automated Reports/Screenshot'), screenshot, true)
		WebUI.click(findTestObject('Object Repository/Automated Reports/Next'))
	}

	public static report_type() {
	}

	public static set_filters(Array filters) {


		for (filter in filters.keySet()) {

			TestObject filter_name = new TestObject('filter')
			filter_name.addProperty('id', ConditionType.EQUALS, filter)
			WebUI.click(filter_name)

			TestObject filter_text = new TestObject('filter text')
			filter_text.addProperty('text', ConditionType.EQUALS, filters[filter])
			WebUI.click(filter_text)
		}
	}

	public static create_report() {

		WebUI.click(findTestObject('Object Repository/Automated Reports/Create'))
	}

	public static validate_report_row(String report_name) {

		WebUI.selectOptionByValue(findTestObject('Object Repository/Automated Reports/Page Size'), "all", false)

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

		WebUI.click(generate_report)
		WebUI.click(findTestObject('Object Repository/Automated Reports/Generate Report'))
		WebUI.click(findTestObject('Object Repository/Automated Reports/OK'))
	}

	public static get_token() {

		String body = "client_id="+System.getenv('client_id')+"&client_secret="+System.getenv('client_secret')+"&grant_type=password&resource=https://graph.microsoft.com&username="+System.getenv('automation_email')+"&password="+System.getenv('automation_email_pass')
		String endpoint = "https://login.microsoftonline.com/"+System.getenv('tenant_id')+"/oauth2/token"
		String request_method = "POST"

		HttpTextBodyContent httpBodyContent = new HttpTextBodyContent(body);

		RequestObject token = new RequestObject("token")
		token.setRestUrl(endpoint)
		token.setRestRequestMethod(request_method)
		token.setBodyContent(httpBodyContent)

		ResponseObject token_response = WS.sendRequest(token)
		String token_text = token_response.getResponseBodyContent()

		String access_token = StringUtils.substringBetween(token_text, '"access_token":"', '","refresh_token":')
		return access_token
	}

	public static report_email_request(String subject_line) {

		String subject_format = subject_line.replaceAll(' ', '+')
		String subject_format_final = subject_format.replaceAll(':', '%3a')
		String subject_endpoint = 'https://graph.microsoft.com/v1.0/me/messages?%24search=%22'+subject_format_final+'%22'

		String access_token = "Bearer " + get_token()

		RequestObject get_email = new RequestObject('get email')
		get_email.setRestUrl(subject_endpoint)
		get_email.setRestRequestMethod('GET')

		List<TestObjectProperty> headerProperties = get_email.getHttpHeaderProperties()
		TestObjectProperty authorizationProperty = new TestObjectProperty("Authorization", ConditionType.EQUALS, access_token, true)
		headerProperties.add(authorizationProperty)
		get_email.setHttpHeaderProperties(headerProperties)

		ResponseObject respObj = WS.sendRequest(get_email)
		String email_text = respObj.getResponseBodyContent()

		JsonSlurper slurper = new JsonSlurper()
		Map parsedJson = slurper.parseText(email_text)

		return parsedJson
	}

	public static open_report_link(String subject_line) {

		Map parsedJson = report_email_request(subject_line)

		String subject = parsedJson.value.subject[0]

		if (subject == subject_line) {

			String body = parsedJson.value.body[0]
			String link = StringUtils.substringBetween(body,'<a href="','">View Report:');
			WebUI.navigateToUrl(link)
			KeywordUtil.markPassed(subject_line + " email has been found.")

			return "Email Found"
		}
		KeywordUtil.markFailed(subject_line + " email has not been found.")
		return "Email not found"
	}

	public static verify_report_contents(String subject_line, String report_name, int row) {

		String email_status = open_report_link(subject_line)

		if (email_status == "Email Found") {

			WebUI.verifyTextPresent(report_name, true)
			WebUI.verifyTextNotPresent('No data to display', true)

			String report_contents = findTestData('Data Files/Report Types').getValue(2, row)

			TestObject report_table = new TestObject('report table or graph')
			report_table.addProperty('id', ConditionType.EQUALS, report_contents)

			if (WebUI.verifyElementPresent(report_table, 10)) {
				KeywordUtil.markPassed(subject_line +' contains the correct contents')
				return "Report generation passed"
			}

			else {
				KeywordUtil.markFailed(subject_line + 'does not contain the correct contents.')
				return "Report generation failed"
			}
		}
		KeywordUtil.markFailed(subject_line +" email has not been found so there are no contents to verify.")
		return "Email generation failed"
	}
}