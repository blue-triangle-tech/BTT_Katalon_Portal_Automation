import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import btt_helper.*
import btt_portal.*


Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site(site)
menu.select_menu_page('dashboard-li', null, null)

Time timestamp = new Time()
String time = timestamp.current_time()

TestData widget_data_file = findTestData('Data Files/Dashboard Widget Types')
String widget_data_type = widget_data_file.getValue(column, 1)
String dashboard_name = widget_data_type + time

Dashboards dashboard = new Dashboards()
dashboard.create_dashboard(dashboard_name)

int dashboard_row = widget_data_file.getRowNumbers()
for (int row = 2; row <= dashboard_row; row++ ) {

String widget_graph_type = widget_data_file.getValue(column, row)
String widget_name = widget_graph_type + time

dashboard.create_dashboard_widget(widget_data_type, widget_graph_type, widget_name)
dashboard.verify_widget_loads(widget_graph_type, widget_name, site)
}