import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject


String subject = 'Synthetic hourlyPerformance825368663 for: GDC Test Site 1'
String subject_format = subject.replaceAll(' ', '+')
String subject_format2 = subject_format.replaceAll(':', '%3a')
String subject_line = 'https://graph.microsoft.com/v1.0/me/messages?%24$search=%22'+subject_format2+'%22'

println subject_line

RequestObject url = findTestObject('Object Repository/Microsoft Graph/Get Email')
String restUrl = url.getRestUrl()

println restUrl