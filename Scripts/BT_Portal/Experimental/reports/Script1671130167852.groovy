import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_helper.*
import btt_portal.*

Login user_login = new Login()
user_login.login('qa.dptadmin1', System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site('GDC Test Site 1')
menu.select_menu_page('alerts-li', null, 'Automated Reports')

TestData report_type = findTestData('Data Files/Report Types')
int report_row = report_type.getRowNumbers()
Time timestamp = new Time()
String time = timestamp.current_time()
report_name = (report_type.getValue(1, 1) + time)

Automated_Reports report = new Automated_Reports()
report.select_automated_report('real-user-reports-type', report_type.getValue(1, 1))
report.report_settings(report_name, 'Automated automated report', '7', 'hours')
//WebUI.click(findTestObject('Object Repository/Automated Reports/Report Filters/filter_trafficSegment'))

TestObject filter = new TestObject('filter')
filter.addProperty('text', ConditionType.EQUALS, 'eCommerce')

//TestObject filter = findTestObject('Object Repository/Automated Reports/Report Filters/filter_trafficSegment')
String traf = 'traffic-segment'
Array fil = [traf:'eCommerce']

report.set_filters(fil)
//WebUI.click(filter)
//report.create_report()
report.generate_report(report_name)

WebUI.delay(10)
report.verify_report_email(report_name)

