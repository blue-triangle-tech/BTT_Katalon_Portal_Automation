import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import btt_portal.*
import groovy.json.JsonSlurper

Automated_Reports report = new Automated_Reports()

//report.verify_report_email('bounce')

String body = "client_id="+System.getenv("client_id")+"&client_secret="+System.getenv("client_secret")+"&grant_type=client_credentials&scope=https://graph.microsoft.com/.default"
String endpoint = "https://login.microsoftonline.com/61ae8b63-71f8-4095-8b01-df8e5856416b/oauth2/v2.0/token"
String request_method = "POST"

println body

HttpTextBodyContent httpBodyContent = new HttpTextBodyContent(body);

RequestObject token = new RequestObject("token")
token.setRestUrl(endpoint)
token.setRestRequestMethod(request_method)
token.setBodyContent(httpBodyContent)

ResponseObject token_response = WS.sendRequest(token)
String token_text = token_response.getResponseBodyContent()
println token_text

JsonSlurper slurper = new JsonSlurper()
Map parsedJson = slurper.parseText(token_text)

String access_token = parsedJson.get("access_token")
println access_token

