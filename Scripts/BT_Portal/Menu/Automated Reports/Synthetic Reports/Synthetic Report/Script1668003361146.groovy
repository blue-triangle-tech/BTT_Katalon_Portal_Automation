import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_helper.*
import btt_portal.*

Login user_login = new Login()
user_login.login('qa.bttcons4', System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site('Demo eCommerce Global')
menu.select_menu_page('alerts-li', null, 'Automated Reports')

TestData report_type = findTestData('Data Files/Report Types')
int report_row = report_type.getRowNumbers()

for (int row = 1; row <=12; row++) {
		
Time timestamp = new Time()
String time = timestamp.current_time()
report_name = report_type.getValue(2, row)+time

Automated_Reports report = new Automated_Reports()
report.select_automated_report('synthetic-reports-type', report_type.getValue(2, row))
report.report_settings(report_name, 'Automated automated report', '6', 'hours')
report.report_filters()
WebUI.verifyTextPresent(report_name, true)
}