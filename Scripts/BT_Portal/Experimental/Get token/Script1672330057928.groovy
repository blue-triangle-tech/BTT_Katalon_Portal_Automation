import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('https://developer.microsoft.com/en-us/graph/graph-explorer')

TestObject sign_in = new TestObject('sign in')
sign_in.addProperty('aria-label', ConditionType.EQUALS, 'Sign in')
sign_in.addProperty('type', ConditionType.EQUALS, 'button')

WebUI.click(sign_in)