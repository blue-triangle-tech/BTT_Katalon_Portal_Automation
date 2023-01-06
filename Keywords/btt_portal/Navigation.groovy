package btt_portal
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class Navigation {
	
	//***************** Object Definitions ********************\\
	
	//Create Test Object Definitions - Common Portal Elements
	TestObject menu = findTestObject('Object Repository/Common Portal Elements/button_menu')
	TestObject settings = findTestObject('Object Repository/Common Portal Elements/button_Settings')
	
	//***************** Function Library ********************\\

	//***************************************************************************
	// Function Name: select_menu_page
	//
	// Function Overview: Selects a page from the menu
	//
	// Function Input Variable(s):
	//		category (String, required) - The "id" property of the menu category or first layer of a page in the menu. (Dashboards, Synthetic Monitoring, Business Analytics, etc.)
	//		type (String, optional) - The "text" property of the 2nd layer of the menu when there are 3 layers (Real User Monitoring -> Web Browser)
	//		page (String, optional) - The "data-original-title" property of the last layer of the menu when there are 2 or 3 layers (Real User Monitoring -> Native App -> Performance Detail, Executive Reports -> null -> Digital Experience Overview)
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************


	public void select_menu_page(String category, String type = null, String page = null) {

		WebUI.click(menu)

		TestObject select_category = new TestObject("category")
		TestObject select_type = new TestObject("type")
		TestObject select_page = new TestObject("page")

		//select_category.addProperty("data-original-title", ConditionType.EQUALS, category)
		select_category.addProperty("id", ConditionType.EQUALS, category)
		select_type.addProperty("text", ConditionType.EQUALS, type)
		select_page.addProperty("data-original-title", ConditionType.EQUALS, page)

		WebUI.click(select_category)

		if (type != null) {
			WebUI.click(select_type)
		}

		if (page != null) {
			WebUI.click(select_page)
		}
	}

	//***************************************************************************
	// Function Name: select_site
	//
	// Function Overview: Selects the site to view data for in the portal
	//		Clicks the dropdown arrow
	//		Sets the site name as text
	//		Clicks on the site name
	//
	// Function Input Variable(s): site_name (String, required) - The name of the site in plain text
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void select_site(String site_name) {

		TestObject dropdown_arrow = findTestObject('Object Repository/Page_My Profile/span_arrow')
		TestObject search = findTestObject('Object Repository/Page_My Profile/input__search_site')

		TestObject website = new TestObject("website")
		website.addProperty("text", ConditionType.EQUALS, site_name)

		WebUI.click(dropdown_arrow)
		WebUI.setText(search, site_name)
		WebUI.click(website)
	}

	//***************************************************************************
	// Function Name: settings_and_administration
	//
	// Function Overview: Opens the settings and administration menu and selects a page from it
	//
	// Function Input Variable(s): page_name (String, required) - The name of the page in plain text
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void settings_and_administration(String page_name) {
		//click settings by default

		WebUI.click(settings, FailureHandling.CONTINUE_ON_FAILURE)

		TestObject page = new TestObject("page")
		page.addProperty("text", ConditionType.EQUALS, page_name)

		WebUI.click(page)
	}
}