package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class Executive_Reports_KPI {


// adds all the widgets in the KPI report to a list
	public static KPI_report_objects() {

		TestObject HM = findTestObject('Object Repository/Executive KPI Report/text_HighlightedMetric')
		String HM_text = WebUI.getText(HM)
		String highlighted_metric = HM_text.minus('Highlighted Metric: ')

		TestObject HM_widget = findTestObject('Object Repository/Executive KPI Report/widget_HighlightedMetric')
		TestObject MD_widget = findTestObject('Object Repository/Executive KPI Report/widget_MetricDefinitions')
		TestObject TC = findTestObject('Object Repository/Executive KPI Report/widget_TopCards')
		TestObject RU = findTestObject('Object Repository/Executive KPI Report/widget_RollupTable')
		TestObject MT = findTestObject('Object Repository/Executive KPI Report/widget_MetricTrends')
		TestObject MST = findTestObject('Object Repository/Executive KPI Report/widget_MetricScoreandTrends')
		TestObject TP = findTestObject('Object Repository/Executive KPI Report/widget_TopXPages')

		TP.addProperty("text", ConditionType.CONTAINS, "%")
		TP.addProperty("text", ConditionType.CONTAINS, highlighted_metric)

		TestObject CP = findTestObject('Object Repository/Executive KPI Report/widget_CustomPages')
		CP.addProperty("text", ConditionType.CONTAINS, "%")
		CP.addProperty("text", ConditionType.CONTAINS, highlighted_metric)

		TestObject TD = findTestObject('Object Repository/Executive KPI Report/widget_TrafficDistribution')
		TD.addProperty("text", ConditionType.CONTAINS, "%")

		TestObject AT = findTestObject('Object Repository/Executive KPI Report/widget_AggregateTrend')

		List all_objects = [HM_widget, MD_widget, TC, RU, MT, MST, TP, CP, TD, AT]
	}

	// verifies that the highlighted metric is highlighted in widgets that contain this
	public static validate_object_highlights() {

		TestObject HM = findTestObject('Object Repository/Executive KPI Report/text_HighlightedMetric')
		String HM_text = WebUI.getText(HM)
		String highlighted_metric = HM_text.minus('Highlighted Metric: ')

		TestObject TCC_highlight = new TestObject('top card comparison highlight')
		TCC_highlight.addProperty('class', ConditionType.EQUALS, 'executive-kpi-report-comparison-card-column column highlighted-metric-shading')

		TestObject TCD_highlight = new TestObject('top distribution highlight')
		TCD_highlight.addProperty('class', ConditionType.EQUALS, 'executive-kpi-report-distribution-card-column column highlighted-metric-shading')

		TestObject MD_highlight = new TestObject('metric definition highlight')
		MD_highlight.addProperty('class', ConditionType.EQUALS, 'metric-definition-presentation highlighted-metric-shading')
		MD_highlight.addProperty('data-metric-term', ConditionType.EQUALS, highlighted_metric)

		List highlighted_objects = [TCC_highlight, TCD_highlight, MD_highlight]
		for (object in highlighted_objects) {
			String highlighted_object = WebUI.getText(object, FailureHandling.CONTINUE_ON_FAILURE)
			if (highlighted_object.contains(highlighted_metric)) {

				println "The highlighted metric is: " + highlighted_metric
			}
		}
	}

	// verifies the report does not have any null points, show NaN%, or show "No data to display" anywhere on the page
	public static verify_not_present() {
		TestObject null_point = new TestObject("null")
		null_point.addProperty("class", ConditionType.EQUALS, "highcharts-point highcharts-null-point")
		WebUI.verifyElementNotPresent(null_point, 10)
		WebUI.verifyTextNotPresent("NaN%", true)
		WebUI.verifyTextNotPresent("No data to display", true)
	}

	public static verify_top_cards() {
	}

	// checks the date the report was generated, formats it, and compares it to the current date
	public static verify_date() {
		TestObject report_date_object = new TestObject("report date")
		report_date_object.addProperty('id', ConditionType.EQUALS, 'executive-kpi-report-by-date-selector')
		String report_date = WebUI.getText(report_date_object)
		String current_date = report_date[13..18]

		Date date = new Date()
		String current_date1 = date.toString()
		String current_date2 = current_date1[4..9]

		if (current_date == current_date2) {
			println "A report has been generated for the current date"
		}
		else {
			//println "A report has not been generated today"
			KeywordUtil.markWarning("A report has not been generated today")
		}
	}

	//method in progress - will verify the rollup table contains data
	public static verify_rollup_table() {

		WebDriver driver = DriverFactory.getWebDriver()
		WebElement rollup_table = driver.findElement(By.className('executive-kpi-report-rollup-table table table-striped'))
		List<WebElement> rollup_table_rows = rollup_table.findElements(By.tagName('tr'))
		int rows_count = rollup_table_rows.size()
		println rows_count
	}

	//method in progress - will validate report intervals and check that data matches
	public static validate_interval() {

		String report_interval = WebUI.getText(findTestObject('Object Repository/Executive KPI Report/obj_ReportInterval'))
		if (report_interval == "Daily") {

			String interval = "Day"
		}
		if (report_interval == "Weekly") {

			String interval = "Week"
		}
	}

	//method in progress - will be used to verify all bar colors are present
	public static verify_TD_bar_colors() {

		TestObject red = findTestObject('Object Repository/Executive KPI Report/obj_TrafficDistribution_RedBars')
		TestObject green = findTestObject('Object Repository/Executive KPI Report/obj_TrafficDistribution_GreenBars')
		TestObject yellow = findTestObject('Object Repository/Executive KPI Report/obj_TrafficDistribution_YellowBars')

		List bars = [red, yellow, green]

		for (bar in bars) {
			WebUI.verifyElementPresent(bar, 10)
		}
	}

	// Step 1: Selects a tab (interates through each tab on the report)
	// Step 2: Verifies all widgets are present by using the KPI_report_objects() method to get the list of objects
	// Step 3: Verified the highlighted metric is highlighted in select widgets with validate_object_highlights()
	// Step 4: Verifies objects/text that should not be present are not found with verify_not_present() 
	// Step 5: Verifies whether a report has been generated for the current date with verify_date()
	public static verify_KPI_report() {

		TestObject mobile_tab = findTestObject('Object Repository/Executive KPI Report/tab_Mobile')
		TestObject tablet_tab = findTestObject('Object Repository/Executive KPI Report/tab_Tablet')
		TestObject desktop_tab = findTestObject('Object Repository/Executive KPI Report/tab_Desktop')

		List device_tabs = [desktop_tab, mobile_tab, tablet_tab]

		for (device in device_tabs) {

			WebUI.click(device)

			List all_objects = KPI_report_objects()
			for (object in all_objects) {
				WebUI.verifyElementPresent(object, 10, FailureHandling.CONTINUE_ON_FAILURE)
			}
			validate_object_highlights()
			verify_not_present()
			verify_date()
		}
	}
}
