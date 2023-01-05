import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*
import internal.GlobalVariable

Logging gv = new Logging()
gv.addGlobalVariable('test', 'hello world')

println GlobalVariable.test



Login user_login = new Login()
user_login.login('qa.bttcons4', System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site('Demo eCommerce Global')
menu.select_menu_page('alerts-li', null, 'Automated Reports')

TestObject o = new TestObject('o')
o.addProperty('text', ConditionType.EQUALS, 'bounceRate1467911684')
xpath = WebUI.getAttribute(o, 'xpath')

println xpath


