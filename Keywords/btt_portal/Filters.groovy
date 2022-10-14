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


public class Filters {

	TestObject time_period = findTestObject('Object Repository/Filters/time_period')
	TestObject apply = findTestObject('Object Repository/Filters/button_Apply')
	TestObject apply_filters = findTestObject('Object Repository/Filters/button_Apply Filters')
	TestObject lookback = findTestObject('Object Repository/Page_Synthetic Performance Overview/button_Lookback')

	public void set_time_period(String data_range_key) {

		WebUI.waitForElementClickable(time_period, 30)
		WebUI.click(time_period)

		TestObject time_range = new TestObject("time range")
		time_range.addProperty("data-range-key", ConditionType.EQUALS, data_range_key)

		WebUI.click(time_range)
		WebUI.click(apply_filters)
	}

	public void set_lookback_period(String lookback_period) {

		WebUI.click(lookback)

		TestObject select_lookback = new TestObject("lookback")
		select_lookback.addProperty("data-value", ConditionType.EQUALS, lookback_period)

		WebUI.click(select_lookback)
	}
}
