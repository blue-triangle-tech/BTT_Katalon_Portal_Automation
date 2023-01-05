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

String var = "CV Page Group" + time
String TYV_text = '(function(){PTDB='+ var +'; return void 0;})()'
TestObject TYV = findTestObject('Object Repository/Tag Testing/Settings_Sites/Create Site/input_PurchaseConfigurationPageName')

Tags tag = new Tags()
tag.edit_site(site_id, TYV, TYV_text)
tag.check_tag(site_prefix, TYV_text)
