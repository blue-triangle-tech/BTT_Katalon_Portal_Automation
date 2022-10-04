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

Mobile.startApplication('bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c', true)

Mobile.closeApplication()

Mobile.startApplication('bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c', true)

Mobile.tap(findTestObject('Object Repository/Mobile Objects/BrowserStack Wikipedia App/android.widget.TextView'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile Objects/BrowserStack Wikipedia App/android.widget.TextView - Log in to Wikipedia'), 
    0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/BrowserStack Wikipedia App/android.widget.EditText'), 'username', 
    0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/BrowserStack Wikipedia App/Password'), 
    'password', 0)

Mobile.tap(findTestObject('Object Repository/Mobile Objects/BrowserStack Wikipedia App/android.widget.TextView - Log in'), 
    0)

Mobile.closeApplication()

