package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class Dashboards {
	
	
	public static create_dashboard(String dashboard_name) {
		
		WebUI.click(findTestObject('Object Repository/Dashboards/button_AddDashboard'))
		WebUI.setText(findTestObject('Object Repository/Dashboards/Create Dashboard/input_DashboardTitle'), dashboard_name)
		WebUI.click(findTestObject('Object Repository/Dashboards/Create Dashboard/button_SaveDashboard'))
	}
	
	public static create_dashboard_widget(String widget_type_id, String widget_graph_type, String widget_title) {
		
		TestObject widget_type = new TestObject('widget type')
		widget_type.addProperty('id', ConditionType.EQUALS, widget_type_id)
		
		TestObject widget_graph_table = new TestObject('widget graph or table')
		widget_graph_table.addProperty('data-widget-type', ConditionType.EQUALS, widget_graph_type)
		
		WebUI.click(findTestObject('Object Repository/Dashboards/button_AddWidget'))
		WebUI.click(widget_type)
		WebUI.click(widget_graph_table)
		WebUI.setText(findTestObject('Object Repository/Dashboards/Create Widget/input_WidgetTitle'), widget_title)
		WebUI.click(findTestObject('Object Repository/Dashboards/Create Widget/button_AddWidget'))
	}
	
	public static verify_widget_loads(String widget_graph_type, String widget_title, String site) {
		
		TestObject dashboard_widget = new TestObject('dashboard widget')
		dashboard_widget.addProperty('data-widget-type', ConditionType.EQUALS, widget_graph_type)
		dashboard_widget.addProperty('text', ConditionType.CONTAINS, widget_title)
		dashboard_widget.addProperty('text', ConditionType.CONTAINS, site)
		
		if (WebUI.verifyElementPresent(dashboard_widget, 15) == true) {
			KeywordUtil.markPassed(widget_title + 'has loaded')
		}
		else {
			KeywordUtil.markFailed(widget_title + 'did not fully load')
		}
		
	}
}
