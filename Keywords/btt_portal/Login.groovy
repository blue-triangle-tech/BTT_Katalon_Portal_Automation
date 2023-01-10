package btt_portal

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chrome.ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.logging.KeywordLogger

import btt_portal.*


public class Login {

	//***************** Object Definitions ********************\\
	 
	//Create Test Object Definitions - Page_Blue Triangle Sign In
	TestObject input_username = findTestObject('Object Repository/Page_Blue Triangle Sign In/input_Username')
	TestObject input_password = findTestObject('Object Repository/Page_Blue Triangle Sign In/input_Password')
	TestObject login_button = findTestObject('Object Repository/Page_Blue Triangle Sign In/button_Sign In')
	
	//Create Test Object Definitions - Common Portal Elements
	TestObject verify_login_successful = findTestObject('Object Repository/Common Portal Elements/button_menu')

	
	//***************** Function Library ********************\\

	//***************************************************************************
	// Function Name: login
	//
	// Function Overview: Login to the Blue Triangle Portal
	// 	Checks if the browser is currently open in order to not log in repeatedly between test cases
	//		Opens the browser to the Blue Triangle Portal
	//		Enters the username
	//		Enters the password
	//		Clicks the login button
	// 	Verifies if the login is successful by checking for the menu button that only exists after logging in
	//
	// Function Input Variable(s):
	//
	// 	username (String, required) - The username of the account logging in
	//		password (String, required) - The password of the account logging in (Please store the password as an account environment variable - System.getenv('BTT_Automation_Pass')
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************


	public void login(String username, String password) {

		// Check to see if a browser window is open, if not then open the window and perform a user login
		try {
			def x = DriverFactory.getCurrentWindowIndex()
			if ( ( x != null ) && ( x >= 0 ) ) {

				println("Browser is already open")
			}
		}
		catch(Exception e) {

			WebUI.openBrowser(GlobalVariable.url)
			WebUI.maximizeWindow()

			WebUI.setText(input_username, username)
			WebUI.setText(input_password, password)

			WebUI.click(login_button)

			WebUI.verifyElementPresent(verify_login_successful, 3, FailureHandling.STOP_ON_FAILURE)
		}
	}


	//***************************************************************************
	// Function Name: chrome_profile_login
	//
	// Function Overview: Login to the Blue Triangle Portal using your Chrome profile history
	// 	Checks if the browser is currently open in order to not log in repeatedly between test cases
	// 	Gets the path to the chrome driver
	//		Sets the chrome driver path
	// 	Gets the chrome profile path from environment variables - must set your chrome profile path in account environment variables
	// 	Sets the chrome profile path
	//		Creates a new driver
	//		Opens the browser to the Blue Triangle Portal with your profile history
	//		Enters the username
	//		Enters the password
	//		Clicks the login button
	// 	Verifies if the login is successful by checking for the menu button that only exists after logging in
	//
	// Function Input Variable(s):
	//
	// 	username (String, required) - The username of the account logging in
	//		password (String, required) - The password of the account logging in (Please store the password as an account environment variable - System.getenv('BTT_Automation_Pass')
	//		profile_path (String, required) - Please store your chrome profile path as an environment variable for your account - System.getenv('Chrome_Profile_Path')
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************
	public void chrome_profile_login(String username, String password, String profile_path) {

		String pathToChromeDriver = DriverFactory.getChromeDriverPath()
		System.setProperty("webdriver.chrome.driver", pathToChromeDriver)

		String chromeProfilePath = System.getenv('Chrome_Profile_Path')
		ChromeOptions chromeProfile = new ChromeOptions();
		chromeProfile.addArguments("user-data-dir=" + chromeProfilePath);

		WebDriver driver = new ChromeDriver(chromeProfile);
		driver.get(GlobalVariable.url);
		DriverFactory.changeWebDriver(driver)

		WebUI.maximizeWindow()

		WebUI.setText(input_username, username)
		WebUI.setText(input_password, password)

		WebUI.click(login_button)

		WebUI.verifyElementPresent(verify_login_successful, 5, FailureHandling.STOP_ON_FAILURE)
	}
}
