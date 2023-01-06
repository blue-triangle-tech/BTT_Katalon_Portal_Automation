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

	//***************************************************************************
	// Function Name: select_automated_report
	//
	// Function Overview: selects the report data type (rum vs. synth) and selects the report type
	//
	// Function Input Variable(s):
	//
	// 		report_data_type (String, required) - The "ID" property of the object used to select a rum or synthetic report
	//		report_type (String, required) - The "data-report-type" property of the object used to select a report
	//
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: report_type
	//***************************************************************************


	public static select_automated_report(String report_data_type, String report_type) {

		WebUI.click(findTestObject('Object Repository/Automated Reports/Create Report'))

		TestObject data_type = new TestObject('data type')
		data_type.addProperty('id', ConditionType.EQUALS, report_data_type)

		TestObject report = new TestObject('report')
		report.addProperty('data-report-type', ConditionType.EQUALS, report_type)

		WebUI.click(data_type)
		WebUI.click(report)
		return report_type
	}

	//***************************************************************************
	// Function Name: report_settings
	//
	// Function Overview: Configures the "settings" page of the report.
	//		Adds a report name
	//		Adds a subject line for the email
	//		Sets the time period
	//      Sets screenshot to "yes" or "no"
	// 		Clicks "Next"
	//
	// Function Input Variable(s):
	//
	// 	report_name (String, required) - The given name of the report
	//		subject_line (String, required) - The given subject line of the email
	//      time_period (String, optional) - The number of hours or days included in the report email (String, but the string should be an integer from 1-31)
	//		time_unit (String, optional) - Sets whether the time period is "hours" or "days" (accepts "hours" or "days")
	//		screenshot (String, optional) - Sets whether a screenshot is included in the report email (accepts "yes" or "no")
	//
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************


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
	
	//***************************************************************************
	 // Function Name: set_filter
	 //
	 // Function Overview: Sets a filter value in the report configuration
	 //		Checks if a filter object is added as an input variable
	 //		Sets the filter option by label
	 //
	 // Function Input Variable(s):
	 //
	 // 	filter (TestObject, optional) - The test object for the filter you want to set
	 //		label (String, optional) - The label of the item you want to select in the filter
	 //
	 //
	 // Function Output Variable(s): None
	 //
	 // Function Return Value: None
	 //***************************************************************************

	public static set_filter(TestObject filter = null, String label = null) {
		
		if (filter != null) {

		WebUI.selectOptionByLabel(filter, label, true)

	}
	}

	//***************************************************************************
	// Function Name: report_settings
	//
	// Function Overview: Clicks the "Create" button to create the report. If a page name is required it selects one and hits create again.
	//
	// Function Input Variable(s): page (String, optional) - enter a page name to ensure the report is created if this is required
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public static create_report(String page = 'home') {

		WebUI.click(findTestObject('Object Repository/Automated Reports/Create'))
		
		if (WebUI.verifyTextNotPresent('Missing page name', true)) {
			
		}
		else {
			WebUI.click(findTestObject('Object Repository/Automated Reports/OK'))
			set_filter(findTestObject('Object Repository/Automated Reports/Report Filters/filter_pageName'), page)
			WebUI.click(findTestObject('Object Repository/Automated Reports/Create'))
		}
	}


	//***************************************************************************
	// Function Name: validate_report_row
	//
	// Function Overview: Determines the row number of a report with a specific name (input variable) in the reports table
	//		Expands the table to view "All" rows of the report
	//		Defines the table
	//		Gets the total number of rows in the table
	//		Loops through the table rows with the report name and compares it to the input variables
	//		If the table cell matches the text of the input variable, the row number is returned
	//
	// Function Input Variable(s): report_name (String, required) - the name of a report in the reports table
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: row - the row number of the report with the name in the input variable
	//***************************************************************************

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

	//***************************************************************************
	// Function Name: generate_report
	//
	// Function Overview: Clicks the "Generate Report" button for a specified report
	//		Calls the validate_report_row method and stores it as an int (which is the row number)
	//		Creates a dynamic test object for the "Generate Report" button
	//		Adds the xPath property to the "Generate Report" button object; the row number integer is plugged into the xPath
	//		The "Generate Report" button is clicked
	//		Additional confirmation buttons are clicked to return to the normal reports page screen
	//
	// Function Input Variable(s): report_name (String, required) - the name of a report in the reports table
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public static generate_report(String report_name) {

		int row = validate_report_row(report_name)

		TestObject generate_report = new TestObject('generate report button')
		generate_report.addProperty('xpath', ConditionType.EQUALS, '//*[@id="reports-table"]/tbody/tr['+row+']/td[8]/button')

		WebUI.click(generate_report)
		WebUI.click(findTestObject('Object Repository/Automated Reports/Generate Report'))
		WebUI.click(findTestObject('Object Repository/Automated Reports/OK'))
	}

	//***************************************************************************
	// Function Name: get_token
	//
	// Function Overview: A POST request, using REST, which retrieves an access token required to authenticate a GET request and read an inbox
	//		Requires the following account environment variables to be set on your machine:
	//			client_id
	//			client_secret
	//			automation_email
	//			automation_email_pass
	//			tenant_id
	//
	// Function Input Variable(s): report_name (String, required) - the name of a report in the reports table
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: access_token - the token used to authenticate the GET request and read the outlook inbox
	//***************************************************************************

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

	//***************************************************************************
	// Function Name: report_email_request
	//
	// Function Overview: A GET request used to read an outlook inbox and find an email with a specfic subject line
	//		Formats the subject line
	//		Places the formatted subject line into the endpoint (hardcoded endpoint formatted for special characters)
	//		Calls the get_token method and stores it as a String with "Bearer " added as the type of authentication
	//		Creates a new request object; Sets the rest url and request method
	//		Defines header properties; Adds the authorization property and sets it equal to the stored access token string
	//		Sends the request
	// 		Parses the responses
	//		Returns the parsed response
	//
	// Function Input Variable(s): report_name (String, required) - the name of a report in the reports table
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: parsedJson - The parsed response of the request for email with a certain subject
	//***************************************************************************

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

	//***************************************************************************
	// Function Name: open_report_link
	//
	// Function Overview: Opens the report link if the report email is found
	//		Calls report_email_request to get the parsedJson
	//		Gets the value of the subject line in the response
	//		Compares the subject line in the response to the subject line in the input variables
	//		If the subject lines match, the body value is retrieved
	//		The report link is extracted from the body
	//		The browser navigates to the url of the report link
	//
	// Function Input Variable(s): subject_line (String, required) - the subject line of a report email
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: String, which states "Email Found" or "Email Not Found"
	//***************************************************************************

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

	//***************************************************************************
	// Function Name: verify_report_contents
	//
	// Function Overview: Checks that the report contains (or does not contain) specified contents
	//		Calls open_report_link and stores the returned string
	//		If the string is "Email Found" it looks for the report name, a graph or table, and verfies "No data to display" is not present.
	// 		If the string is "Email Not Found" it skips the above steps and returns the string, "Email generation failed".
	//
	// Function Input Variable(s):
	//		subject_line (String, required) - the subject line of a report email
	//		report_name (String, required) - the name of the report
	//		row (int, required) - the row number of the excel which contains the id property for a graph or table of the report you want to verify (int)
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: String of report status - "Report generation passed", "Report generation failed", "Email generation failed"
	//***************************************************************************

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