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

public class Alerts {

	TestObject alert_name = findTestObject('Object Repository/Alerts/Create Alert/input__alert-name')
	TestObject alert_status = findTestObject('Object Repository/Alerts/Create Alert/span_Enabled')
	TestObject recipients = findTestObject('Object Repository/Alerts/Create Alert/input__recipients')
	TestObject notification_group = findTestObject('Object Repository/Alerts/Create Alert/input_Notification Group')
	TestObject sms_number = findTestObject('Object Repository/Alerts/Create Alert/input__sms-integration')
	TestObject slack_integration = findTestObject('Object Repository/Alerts/Create Alert/input_Configure a webhook_slack-integration')
	TestObject graph = findTestObject('Object Repository/Alerts/Create Alert/span_Graph')
	TestObject subject_line = findTestObject('Object Repository/Alerts/Create Alert/pre_')
	TestObject next = findTestObject('Object Repository/Alerts/Create Alert/button_Next')
	TestObject previous = findTestObject('Object Repository/Alerts/Create Alert/button_Previous')



	public void alert_general(TestData alert_data, int x = 1) {

		String name = alert_data.getValue('Alert Name', x)
		String recipient_emails = alert_data.getValue('Recipients', x)
		String group = alert_data.getValue('Notification Group', x)
		String phone_number = alert_data.getValue('SMS Numbers', x)
		String webhook_url = alert_data.getValue('Webhook', x)
		String subject_line_text

		WebUI.setText(alert_name, name)
		//WebUI.selectOptionByValue(alert_status, 'Enabled', false)

		if (recipient_emails != null) {
			WebUI.setText(recipients, recipient_emails)
		}

		if (notification_group != null) {
			WebUI.selectOptionByValue(notification_group, group, false)
		}

		if (phone_number != null) {
			WebUI.setText(sms_number, phone_number)
		}

		if (webhook_url != null) {
			WebUI.setText(slack_integration, webhook_url)
		}

		if (subject_line_text != null) {
			WebUI.setText(subject_line, subject_line_text)
		}
		
		WebUI.click(next)
	}

	public void alert_condition() {
	}
}
