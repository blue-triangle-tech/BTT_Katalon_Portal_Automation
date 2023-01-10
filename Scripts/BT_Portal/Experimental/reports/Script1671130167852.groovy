import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*

Login user_login = new Login()
user_login.login('qa.dptadmin1', System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site('GDC Test Site 1')
menu.select_menu_page('real-user-monitoring-li', 'Native App', 'Native App Performance Detail')

WebUI.click(findTestObject('Object Repository/Filters/button_Edit_filters'))

String filter_text = WebUI.getText(findTestObject('Object Repository/Automated Reports/Report Filters/filter_trafficSegment'))

if (filter_text == '') {
	
	KeywordUtil.markFailed('Filter in blank')
}

else {
	
	KeywordUtil.markPassed('Filter is populated')
}

