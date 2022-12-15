import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
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
report.report_filters()
report.generate_report(report_name)

WebUI.delay(10)
report.verify_report_email(report_name)

