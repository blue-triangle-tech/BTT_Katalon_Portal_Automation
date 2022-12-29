import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.Automated_Reports

WebUI.openBrowser('')
WebUI.maximizeWindow()
List report_subjects = ['Core Metrics By Site Report For Site: GDC Test Site 1']
//List report_subjects = ['Real User lostRevenue994789189 for: GDC Test Site 1', 'Real User bounceRate995787473 for: GDC Test Site 1', 'Real User conversionsOverTime995836737 for: GDC Test Site 1']
Automated_Reports report1 = new Automated_Reports()
for (subject in report_subjects) {
	report1.open_report_link(subject)
	report1.verify_report_contents(20)

}
