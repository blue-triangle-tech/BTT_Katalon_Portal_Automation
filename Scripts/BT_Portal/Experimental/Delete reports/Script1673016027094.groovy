import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.Login
import btt_portal.Navigation

Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site(site)
menu.select_menu_page('alerts-li', null, 'Automated Reports')

WebUI.selectOptionByValue(findTestObject('Object Repository/Automated Reports/Page Size'), "all", false)

WebUI.click(findTestObject('Object Repository/Automated Reports/Toggle All Reports'))

WebUI.click(findTestObject('Object Repository/Automated Reports/Bulk Actions'))

WebUI.click(findTestObject('Object Repository/Automated Reports/Bulk Actions_Delete Reports'))

WebUI.click(findTestObject('Object Repository/Automated Reports/button_confirmDelete'))