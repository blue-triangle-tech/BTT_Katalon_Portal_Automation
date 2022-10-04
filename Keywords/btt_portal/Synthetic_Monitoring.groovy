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

public class Synthetic_Monitoring {


	public static performance_overview() {

		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Overview/div_Error Tracking and Performance'), 30)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Overview/div_Performance Breakdown'), 15)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Overview/div_Performance By Geography'), 15)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Overview/div_Performance Overview For All Pages'), 15)
	}

	public static performance_detail() {

		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Page Hits by HTTP Code'), 30)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Site Availability Over Time'), 15)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Performance Details by Page'), 15)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Performance Details'), 15)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Performance Details Graph'), 15)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Page Views'), 15)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Page Views for Selected Session'), 15)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Performance Breakdown'), 15)

		//WebUI.click(findTestObject('Object Repository/Page_Synthetic Performance Detail/Green Plot Point'))

		//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Performance Breakdown'), 15)
		//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Synthetic Performance Detail/Domain Level Activity'), 15)
	}

	public static test_results() {
	}

	public static error_state_tracking() {

		WebUI.verifyElementPresent(findTestObject(''), 30)
		WebUI.verifyElementPresent(findTestObject(''), 30)
		WebUI.verifyElementPresent(findTestObject(''), 30)
		WebUI.verifyElementPresent(findTestObject(''), 30)
	}

	public static page_performance_comparison() {
	}

	public static aggregate_waterfall() {
	}

	public static errors_explorer() {
	}

	public static performance_budget() {
	}

	public static verify_real_browser_pages(int x) {
		if (x==1) {
			performance_overview()
			println "po val"
		}
		if (x==2) {
			performance_detail()
			println "pd val"
		}
		if (x>2) {
			println "val not complete"
		}
	}
}
