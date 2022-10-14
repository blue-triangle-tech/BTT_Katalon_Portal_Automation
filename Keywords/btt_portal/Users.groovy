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

public class Users {

	TestObject accessLevel = findTestObject('Object Repository/Create User/select_UserAccessLevel')
	TestObject partner = findTestObject('Object Repository/Create User/select_Partner')
	TestObject account = findTestObject('Object Repository/Create User/select_Account')
	TestObject username = findTestObject('Object Repository/Create User/input__Usersusername')
	TestObject password = findTestObject('Object Repository/Create User/input_Password_Userspassword')
	TestObject email = findTestObject('Object Repository/Create User/input__Usersemail')
	TestObject status = findTestObject('Object Repository/Create User/select_Status')
	TestObject firstName = findTestObject('Object Repository/Create User/input_First Name_Profilesfirstname')
	TestObject lastName = findTestObject('Object Repository/Create User/input_Last Name_Profileslastname')
	TestObject defaultSite = findTestObject('')
	TestObject landingPage = findTestObject('')
	TestObject mfa = findTestObject('')
	TestObject theme = findTestObject('')
	TestObject splineChartStyle = findTestObject('')
	TestObject lineChartStyle = findTestObject('')


	public void create_new_user(TestData user_data, int x = 1) {

		WebUI.selectOptionByLabel(accessLevel, user_data.getValue('Access Level', x), false)
		WebUI.selectOptionByLabel(partner, user_data.getValue('Partner', x), false)
		WebUI.setText(account, user_data.getValue('Account', x))

		if (user_data.getValue('Access Level', x).contains('Department')){
			WebUI.selectOptionByLabel(account, user_data.getValue('Department', x), false)
		}

		WebUI.setText(username, user_data.getValue('Username', x))
		WebUI.setText(password, user_data.getValue('Password', x))
		WebUI.setText(email, user_data.getValue('Email', x))

		WebUI.selectOptionByLabel(status, user_data.getValue('Status', x), false)
		WebUI.setText(firstName, user_data.getValue('First Name', x), false)
		WebUI.setText(lastName, user_data.getValue('Last Name', x), false)
	}
}
