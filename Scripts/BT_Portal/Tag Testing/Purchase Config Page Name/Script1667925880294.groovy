import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*
import btt_helper.*

Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

Time timestamp = new Time()
String time = timestamp.current_time()

String PCPN_text = purchase_config + time

TestObject PCPN = findTestObject('Object Repository/Tag Testing/Settings_Sites/Create Site/input_PurchaseConfigurationPageName')

Tags tag = new Tags()
tag.edit_site(site_id, PCPN, PCPN_text)
tag.check_tag(site_prefix, PCPN_text)
