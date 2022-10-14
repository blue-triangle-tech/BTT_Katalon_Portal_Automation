package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class Executive_Reports_KPI {



	public static KPI_report_objects() {

		TestObject HM = findTestObject('Object Repository/Executive KPI Report/Highlighted Text')
		String HM_text = WebUI.getText(HM)
		String highlighted_metric = HM_text.minus('Highlighted Metric: ')

		TestObject HM_widget = findTestObject('Object Repository/Executive KPI Report/Highlighted Metric')
		TestObject MD_widget = findTestObject('Object Repository/Executive KPI Report/Metric Definitions')
		TestObject TC = findTestObject('Object Repository/Executive KPI Report/Top Cards')
		TestObject RU = findTestObject('Object Repository/Executive KPI Report/Rollup Table')
		TestObject MT = findTestObject('Object Repository/Executive KPI Report/Metric Trends')
		TestObject MST = findTestObject('Object Repository/Executive KPI Report/Metric Score and Trends')
		TestObject TP = findTestObject('Object Repository/Executive KPI Report/Top X Pages')

		TP.addProperty("text", ConditionType.CONTAINS, "%")
		TP.addProperty("text", ConditionType.CONTAINS, highlighted_metric)

		TestObject CP = findTestObject('Object Repository/Executive KPI Report/Custom Pages')
		CP.addProperty("text", ConditionType.CONTAINS, "%")
		CP.addProperty("text", ConditionType.CONTAINS, highlighted_metric)

		TestObject TD = findTestObject('Object Repository/Executive KPI Report/Traffic Distribution')
		TD.addProperty("text", ConditionType.CONTAINS, "%")

		TestObject AT = findTestObject('Object Repository/Executive KPI Report/Aggregate Trend')

		List all_objects = [HM_widget, MD_widget, TC, RU, MT, MST, TP, CP, TD, AT]
	}

	public static validate_object_highlights() {

		TestObject HM = findTestObject('Object Repository/Executive KPI Report/Highlighted Text')
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

	public static verify_not_present() {
		TestObject null_point = new TestObject("null")
		null_point.addProperty("class", ConditionType.EQUALS, "highcharts-point highcharts-null-point")
		WebUI.verifyElementNotPresent(null_point, 10)
		WebUI.verifyTextNotPresent("NaN%", true)
		WebUI.verifyTextNotPresent("No data to display", true)
	}

	public static verify_top_cards() {
	}

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
			println "A report has not been generated today"
		}
	}

	public static verify_rollup_table() {

		WebDriver driver = DriverFactory.getWebDriver()
		WebElement rollup_table = driver.findElement(By.className('executive-kpi-report-rollup-table table table-striped'))
		List<WebElement> rollup_table_rows = rollup_table.findElements(By.tagName('tr'))
		int rows_count = rollup_table_rows.size()
		println rows_count
	}

	public static validate_interval() {

		String report_interval = WebUI.getText(findTestObject('Object Repository/Executive KPI Report/Report Interval'))
		if (report_interval == "Daily") {

			String interval = "Day"
		}
		if (report_interval == "Weekly") {

			String interval = "Week"
		}
	}

	public static verify_TD_bar_colors() {

		TestObject red = findTestObject('Object Repository/Executive KPI Report/Traffic Distribution Red Bars')
		TestObject green = findTestObject('Object Repository/Executive KPI Report/Traffic Distribution Green Bars')
		TestObject yellow = findTestObject('Object Repository/Executive KPI Report/Traffic Distribution Yellow Bars')

		List bars = [red, yellow, green]

		for (bar in bars) {
			WebUI.verifyElementPresent(bar, 10)
		}
	}

	public static verify_KPI_report() {

		TestObject mobile_tab = findTestObject('Object Repository/Executive KPI Report/Mobile Tab')
		TestObject tablet_tab = findTestObject('Object Repository/Executive KPI Report/Tablet Tab')
		TestObject desktop_tab = findTestObject('Object Repository/Executive KPI Report/Desktop Tab')

		List device_tabs = [desktop_tab, mobile_tab, tablet_tab]

		for (device in device_tabs) {

			WebUI.click(device)

			List all_objects = KPI_report_objects()
			for (object in all_objects) {
				int x = 0
				WebUI.verifyElementPresent(object, 10, FailureHandling.CONTINUE_ON_FAILURE)
				//println WebUI.getText(object)
			}
			validate_object_highlights()
			verify_not_present()
			verify_date()
		}
	}
}
