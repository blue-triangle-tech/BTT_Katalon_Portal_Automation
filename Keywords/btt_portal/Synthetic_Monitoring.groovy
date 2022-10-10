	package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class Synthetic_Monitoring {


	public static performance_overview() {
		
		TestObject error_tracking = findTestObject('Object Repository/Page_Synthetic Performance Overview/div_Error Tracking and Performance')
		error_tracking.addProperty('text', ConditionType.CONTAINS, 'Error State')
		error_tracking.addProperty('text', ConditionType.CONTAINS, 'Avg')
		error_tracking.addProperty('text', ConditionType.CONTAINS, 'Last Measured')
		
		TestObject performance_breakdown = findTestObject('Object Repository/Page_Synthetic Performance Overview/div_Performance Breakdown')
		TestObject performance_geography = findTestObject('Object Repository/Page_Synthetic Performance Overview/div_Performance By Geography')
		TestObject overview_all_pages = findTestObject('Object Repository/Page_Synthetic Performance Overview/div_Performance Overview For All Pages')

		WebUI.verifyElementPresent(error_tracking, 30)
		WebUI.verifyElementPresent(performance_breakdown, 15)
		WebUI.verifyElementPresent(performance_geography, 15)
		WebUI.verifyElementPresent(overview_all_pages, 15)
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
