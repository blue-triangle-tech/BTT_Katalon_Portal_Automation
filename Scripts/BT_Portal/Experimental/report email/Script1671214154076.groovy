import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import btt_portal.*

Automated_Reports report = new Automated_Reports()
String access_token = report.get_token()
println access_token

//report.verify_report_email('bounce')

RequestObject get_email = new RequestObject("get email")
String endpoint = 'https://graph.microsoft.com/v1.0/me/messages?'
get_email.setRestUrl(endpoint)
get_email.setRestRequestMethod('GET')

TestObjectProperty header1 = new TestObjectProperty("Authorization", ConditionType.EQUALS, "Bearer "+access_token)
TestObjectProperty header2 = new TestObjectProperty("Accept", ConditionType.EQUALS, "application/json")
List headers = [header1, header2]
get_email.setHttpHeaderProperties(headers)
List<TestObjectProperty> headerProperties = get_email.getHttpHeaderProperties()
println headerProperties
String url = get_email.getRestUrl()

ResponseObject respObj = WS.sendRequest(get_email)
String email_text = respObj.getResponseBodyContent()

if (email_text.contains('bounce')) {
	
	println "yay"
}
