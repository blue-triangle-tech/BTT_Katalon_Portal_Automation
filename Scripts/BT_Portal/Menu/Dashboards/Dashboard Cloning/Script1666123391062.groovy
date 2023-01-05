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
import btt_portal.*

Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_menu_page('dashboard-li', null, null)

for (int x = 0; x<=75; x++) {
	
WebUI.click(findTestObject('Object Repository/Dashboards/button_AddDashboard'))

WebUI.click(findTestObject('Object Repository/Page_Dashboards/input_Me_clone-dashboards-checkbox'))

WebUI.click(findTestObject('Object Repository/Page_Dashboards/button_Clone'))

WebUI.click(findTestObject('Object Repository/Dashboards/button_SelectUser'))

WebUI.click(findTestObject('Object Repository/Page_Dashboards/li_(Myself)'))

WebUI.click(findTestObject('Object Repository/Dashboards/button_SelectUser'))

WebUI.click(findTestObject('Object Repository/Page_Dashboards/button_Clone Dashboard'))


}