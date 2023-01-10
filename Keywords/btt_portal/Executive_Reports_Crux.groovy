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


	//***************************************************************************
	// Function Name: verify_crux_text
	//
	// Function Overview: Verifies text is present, indicating objects have loaded
	//
	// Function Input Variable(s): None
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

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

	//***************************************************************************
	// Function Name: verify_crux_tables
	//
	// Function Overview: Verifies table elements are present in the crux report
	//
	// Function Input Variable(s): None
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************
	public static verify_crux_tables() {

		TestObject bestToWorst = findTestObject('Object Repository/Executive CrUX Report/table_BestToWorst')
		TestObject ranksByMetric = findTestObject('Object Repository/Executive CrUX Report/table_RanksbyMetric')
		TestObject metricTable1 = findTestObject('Object Repository/Executive CrUX Report/table_MetricTable1')
		TestObject metricTable2 = findTestObject('Object Repository/Executive CrUX Report/table_MetricTable2')
		TestObject metricTable3 = findTestObject('Object Repository/Executive CrUX Report/table_MetricTable3')

		WebUI.verifyElementPresent(bestToWorst, 10)
		WebUI.verifyElementPresent(ranksByMetric, 10)
		WebUI.verifyElementPresent(metricTable1, 10)
		WebUI.verifyElementPresent(metricTable2, 10)
		WebUI.verifyElementPresent(metricTable3, 10)
	}

	//***************************************************************************
	// Function Name: verify_crux_report
	//
	// Function Overview: Verifies crux methods for each tab in the report
	//
	//		Defines tabs and puts them in a list
	//		For each tab in the list it calls verify_crux_text and verify_crux_tables
	//
	// Function Input Variable(s): None
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public static verify_crux_report() {

		TestObject mobile_tab = findTestObject('Object Repository/Executive CrUX Report/tab_Mobile')
		TestObject desktop_tab = findTestObject('Object Repository/Executive CrUX Report/tab_Desktop')

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
