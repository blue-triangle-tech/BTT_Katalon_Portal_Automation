import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_helper.*
import btt_portal.*

//***************************************************************************
 
 // Script Name: Real User Report - Browser Version
 //
 // Script Overview: Creates each type of real user report from the Automated Reports page with a filter for Chrome-108-Windows 10
 //		Logs in - Calls the login() method
 //		Selects a site - Calls the select_site() method
 //		Selects the automated reports page - Calls the select_menu_page() method
 //		Defines the data file to use in the script (contains each report type)
 //		Gets the number of rows in the excel file
 //		Creates a list to store the email subjects which will be set during report creation
 //		Uses a for loop to iterate through each row of the excel
 //		Creates a timestamp and inserts it to the report_name to ensure the name is unique
 //		Creates a subject for the report email based on the report name and site
 //		Adds the subjects to the list as they are created
 //		Begins report creation by using select_automated_report() to select the data type and report type
 //		Configures the report settings with report_settings() to set the name, subject, time period, and whether to include a screenshot
 //		Sets the browser version to Chrome-108-Windows 10
 //		If core metrics by site is selected it also chooses a page name
 //		Uses create_report() to click the "create" button
 // 	Uses generate_report() to click the "Generate Report" button of the report that was most recently created by the script
 //		Iterates through each report subject added to the list
 //		Sends a request to get email with the specified subject, extracts the report link, and verifies the report contents using verify_report_contents()
 //
 // Change History: <Insert Change History Table with a username, change date, and change description for each major change made>
 //***************************************************************************

Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site(site)
menu.select_menu_page('alerts-li', null, 'Automated Reports')

TestData report_type = findTestData('Data Files/Report Types')
int report_row = report_type.getRowNumbers()

List report_subjects = []


for (int row = 1; row <=report_row; row++) {
		
Time timestamp = new Time()
String time = timestamp.current_time()
String report_name = "Real User " + report_type.getValue(1, row)+time
String report_subject = report_name + " for: " + site

report_subjects.add(report_subject)

Automated_Reports report = new Automated_Reports()
report.select_automated_report('real-user-reports-type', report_type.getValue(1, row))
report.report_settings(report_name, report_subject, '7', 'hours', 'No')

TestObject browser_version = new TestObject('browser version')
browser_version.addProperty('text', ConditionType.EQUALS, 'Chrome-108-Windows 10')
WebUI.click(findTestObject('Object Repository/Automated Reports/Report Filters/filter_browserOSversion'))
WebUI.click(browser_version)

if (report_type.getValue(1, row) == 'coreMetricsBySite') {
	TestObject page = new TestObject('page')
	page.addProperty('text', ConditionType.EQUALS, 'HomePage' )
	WebUI.click(findTestObject('Object Repository/Automated Reports/Report Filters/filter_pageName'))
	WebUI.click(page)
}
report.create_report()
report.generate_report(report_name)

}
WebUI.delay(30)
int data_row = 1
for (subject in report_subjects) {

	String report_name = subject.replaceAll(" for: " + site, "")

	Automated_Reports report1 = new Automated_Reports()
	report1.verify_report_contents(subject, report_name, data_row)
	data_row++
	
}



