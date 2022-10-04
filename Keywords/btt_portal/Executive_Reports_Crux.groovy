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

public class Executive_Reports_Crux {

	public static verify_crux_text(String tab){

		WebUI.verifyTextPresent('About CrUX Data', true)
		WebUI.verifyTextPresent('Overall CrUX Rank by Site', true)
		WebUI.verifyTextPresent('CrUX Site Ranks by Page - ', true)
		WebUI.verifyTextPresent('First Contentful Paint - CrUX Data - '+ tab, true)
		WebUI.verifyTextPresent('Largest Contentful Paint - CrUX Data - '+ tab, true)
		WebUI.verifyTextPresent('First Input Delay - CrUX Data - '+ tab, true)
		WebUI.verifyTextPresent('Cumulative Layout Shift - CrUX Data - ' + tab, true)
		WebUI.verifyTextPresent('CrUX Data Trend From', true)
	}

	public static verify_crux_tables() {

		TestObject bestToWorst = findTestObject('Object Repository/Executive CrUX Report/Best to Worst table')
		TestObject ranksByMetric = findTestObject('Object Repository/Executive CrUX Report/Ranks by Metric Table')
		TestObject metricTable1 = findTestObject('Object Repository/Executive CrUX Report/Metric Table 1')
		TestObject metricTable2 = findTestObject('Object Repository/Executive CrUX Report/Metric Table 2')
		TestObject metricTable3 = findTestObject('Object Repository/Executive CrUX Report/Metric Table 3')

		WebUI.verifyElementPresent(bestToWorst, 10)
		WebUI.verifyElementPresent(ranksByMetric, 10)
		WebUI.verifyElementPresent(metricTable1, 10)
		WebUI.verifyElementPresent(metricTable2, 10)
		WebUI.verifyElementPresent(metricTable3, 10)
	}

	public static verify_crux_report() {

		TestObject mobile_tab = findTestObject('Object Repository/Executive CrUX Report/Mobile Tab')
		TestObject desktop_tab = findTestObject('Object Repository/Executive CrUX Report/Desktop Tab')

		List tabs = [mobile_tab, desktop_tab]

		for (tab in tabs) {

			if (tab == mobile_tab) {
				String tab_text = "Mobile"
				WebUI.click(tab)
				verify_crux_text(tab_text)
			}
			else {
				WebUI.click(tab)
				verify_crux_text("Desktop")
			}
		}
	}
}
