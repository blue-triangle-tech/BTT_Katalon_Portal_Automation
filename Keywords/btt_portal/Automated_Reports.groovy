package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class Automated_Reports {

	public static select_automated_report(String report_data_type, String report_name) {

		WebUI.click(findTestObject('Object Repository/Automated Reports/Create Report'))

		TestObject data_type = new TestObject('data type')
		data_type.addProperty('id', ConditionType.EQUALS, report_data_type)

		TestObject report = new TestObject('report')
		report.addProperty('data-report-type', ConditionType.EQUALS, report_name)

		WebUI.click(data_type)
		WebUI.click(report)
	}

	public static report_settings(String report_name, String subject_line, String time_period = '1', String time_unit = 'days') {

		WebUI.setText(findTestObject('Object Repository/Automated Reports/Set Report Name'), report_name)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Automated Reports/Report Interval Unit'), time_unit, true)
		String text = WebUI.getAttribute(findTestObject('Object Repository/Automated Reports/Report Interval Unit'), 'value')

		println text
		WebUI.setText(findTestObject('Object Repository/Automated Reports/Report Interval Period'), time_period)
		String text2 = WebUI.getAttribute(findTestObject('Object Repository/Automated Reports/Report Interval Period'), 'value')
		println text2 + ' ' + text
		WebUI.setText(findTestObject('Object Repository/Automated Reports/Subject Line'), subject_line)
		WebUI.click(findTestObject('Object Repository/Automated Reports/Next'))
	}

	public static report_filters() {

		WebUI.click(findTestObject('Object Repository/Automated Reports/Create'))
	}

	public static validate_report_row() {
	}
}
