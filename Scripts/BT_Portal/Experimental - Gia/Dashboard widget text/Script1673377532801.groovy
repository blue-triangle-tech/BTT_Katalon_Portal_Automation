import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.Login
import btt_portal.Navigation

Login user_login = new Login()
user_login.login('qa.dptadmin1', System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site('GDC Test Site 1')
menu.select_menu_page('dashboard-li', null, null)

TestObject to = new TestObject('fds')
to.addProperty('data-widget-type', ConditionType.EQUALS, 'performance')

String text = WebUI.getText(to)
println text