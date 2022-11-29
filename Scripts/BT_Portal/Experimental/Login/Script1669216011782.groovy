import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*

Login user_login = new Login()
user_login.login('qa.bttcons4', System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site('Demo eCommerce Global')
menu.select_menu_page('dashboard-li', null, null)

TestObject performance = new TestObject('performance widget')
performance.addProperty('data-widget-title', ConditionType.EQUALS, 'Performance')
performance.addProperty('data-widget-type', ConditionType.EQUALS, 'performance')







