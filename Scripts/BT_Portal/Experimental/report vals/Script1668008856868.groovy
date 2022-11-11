import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
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

		
Time timestamp = new Time()
String time = timestamp.current_time()
report_name = 'bea'+time

TestObject report = new TestObject('report')
report.addProperty('text', ConditionType.EQUALS, 'Bounce Rate Report')
//report.addProperty('xpath', ConditionType.CONTAINS, '//*[@id="reports-table"]/tbody/')

String xpath = WebUI.getAttribute(report, 'xpath')
println xpath
//Automated_Reports report = new Automated_Reports()
//report.select_automated_report('synthetic-reports-type', 'executiveAvailabilityV2')
//report.report_settings(report_name, 'Automated automated report', '6', 'hours')