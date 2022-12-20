import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_helper.*
import btt_portal.*

Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site(site)
menu.select_menu_page('alerts-li', null, 'Automated Reports')

TestData report_type = findTestData('Data Files/Report Types')
int report_row = report_type.getRowNumbers()

List report_names = []

for (int row = 1; row <=12; row++) {
		
Time timestamp = new Time()
String time = timestamp.current_time()
report_name = "Synthetic " + report_type.getValue(2, row)+time
report_names.add(report_name)

Automated_Reports report = new Automated_Reports()
report.select_automated_report('synthetic-reports-type', report_type.getValue(2, row))
report.report_settings(report_name, report_name + " for: " + site, '7', 'hours', 'No')
report.create_report()
report.generate_report(report_name)
}

for (name in report_names) {
	Automated_Reports report1 = new Automated_Reports()
	report1.verify_report_email(name)
}