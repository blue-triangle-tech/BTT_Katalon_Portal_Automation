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

TestObject input_username = findTestObject('Object Repository/Page_Blue Triangle Sign In/input_Username')
TestObject input_password = findTestObject('Object Repository/Page_Blue Triangle Sign In/input_Password')
TestObject login_button = findTestObject('Object Repository/Page_Blue Triangle Sign In/button_Sign In')

TestObject verify_login_successful = findTestObject('Object Repository/Common Portal Elements/button_menu')


WebUI.openBrowser(GlobalVariable.url)
WebUI.maximizeWindow()

WebUI.setText(input_username, 'giavanna.esposito@bluetriangle.com')
WebUI.setText(input_password, System.getenv('BTT_Password'))

WebUI.click(login_button)

WebUI.verifyElementPresent(verify_login_successful, 5, FailureHandling.STOP_ON_FAILURE)

Navigation menu = new Navigation()
Filters time_period = new Filters()

menu.select_menu_page("Real User Monitoring", "Web Browser", "Real User Performance Overview")
time_period.set_time_period("Last 14 Days")