import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import btt_portal.Automated_Reports



Automated_Reports report = new Automated_Reports()

String subject = 'Real User bounceRate1602803723 for: GDC Test Site 1'
String subject_format = subject.replaceAll(' ', '+')
String subject_format_final = subject_format.replaceAll(':', '%3a')
String subject_endpoint = 'https://graph.microsoft.com/v1.0/me/messages?%24search=%22'+subject_format_final+'%22'

String access_token = report.get_token()
RequestObject get_email = new RequestObject('get email')
List<TestObjectProperty> headerProperties = get_email.getHttpHeaderProperties()
TestObjectProperty authorizationProperty = new TestObjectProperty("Authorization", ConditionType.EQUALS, "Bearer " +access_token, true)
headerProperties.add(authorizationProperty)
get_email.setHttpHeaderProperties(headerProperties)
get_email.setRestUrl(subject_endpoint)
get_email.setRestRequestMethod('GET')

ResponseObject respObj = WS.sendRequest(get_email)
String email_text = respObj.getResponseBodyContent()

println email_text

//WebUI.openBrowser('')



//println report.get_token()
//report.verify_report_contents(subject, 'Real User bounceRate1602803723', 1)
//report.open_report_link(subject)


