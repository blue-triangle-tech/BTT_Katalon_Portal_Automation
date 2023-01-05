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
user_login.login('qa.bttcons4', System.getenv('BTT_Automation_Pass'))

WebUI.navigateToUrl('https://portal.bluetriangle.com/btportal/web/index.php?r=sites/update&id=409')

Time timestamp = new Time()
String time = timestamp.current_time()

String PCPN = 'Random Page' + time
String text = WebUI.getText(findTestObject('Object Repository/Tag Testing/Settings_Sites/Create Site/input_PurchaseConfigurationPageName'))
println text
//WebUI.click(findTestObject('Object Repository/Tag Testing/Settings_Sites/Create Site/button_Save'))

WebDriver driver = DriverFactory.getWebDriver()
JavascriptExecutor js = ((driver) as JavascriptExecutor)
js.executeScript("window.open();")

WebUI.switchToWindowIndex(1)
WebUI.navigateToUrl('https://testsite1.btttag.com/btt.js')


int x = 0
while (x<=180) {
if (WebUI.verifyTextPresent(PCPN, true)) {
	return x
}
else {
	WebUI.delay(20)
	WebUI.refresh()
	x = x + 20
	println x
}}
