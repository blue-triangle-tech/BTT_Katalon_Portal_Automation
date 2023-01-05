import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_helper.*
import btt_portal.*

/**
Commenting line 1
Still commenting
Pestering Kevin
 */
Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site(site)
menu.select_menu_page('alerts-li', null, 'Automated Reports')

TestData report_type = findTestData('Data Files/Report Types')
int report_row = report_type.getRowNumbers()

List report_subjects = []


for (int row = 1; row <=3; row++) {
		
Time timestamp = new Time()
String time = timestamp.current_time()
String report_name = "Real User " + report_type.getValue(1, row)+time
String report_subject = report_name + " for: " + site

report_subjects.add(report_subject)

Automated_Reports report = new Automated_Reports()
report.select_automated_report('real-user-reports-type', report_type.getValue(1, row))
report.report_settings(report_name, report_subject, '7', 'hours', 'No')

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