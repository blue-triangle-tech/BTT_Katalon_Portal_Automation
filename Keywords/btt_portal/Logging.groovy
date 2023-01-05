package btt_portal

import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.openqa.selenium.WebDriver
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogEntry

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class Logging {
	
	//***************************************************************************
	 // Function Name: get_console_errors
	 //
	 // Function Overview: Gets console errors of the error level defined in the input variable
	 //		Gets browser logs
	 //		Gets the page title
	 //		Adds the page title to a list as the first item
	 //		Goes through each log entry 
	 // 	Checks if the error level matches the input variable
	 //		If the error level matches it adds the message to the console_errors list
	 //
	 // Function Input Variable(s): error_level (String, required) - accepts "SEVERE" or "WARNING"
	 //
	 // Function Output Variable(s): None
	 //
	 // Function Return Value: List console_errors
	//***************************************************************************

	public static get_console_errors(String error_level) {

		WebDriver driver = DriverFactory.getWebDriver()

		LogEntries logs = driver.manage().logs().get("browser")

		TestObject page_title = new TestObject("page title")
		page_title.addProperty("id", ConditionType.EQUALS, 'page-title')

		String page_name = WebUI.getText(page_title)

		List console_errors = [page_name]

		for (LogEntry entry : logs) {

			String error = entry.getLevel()
			if (error == error_level) {
				String error_message = entry.getMessage()
				console_errors.add(error_message)
			}
		}
		println console_errors

		return console_errors
	}


//not complete
	public static write_console_errors() {

		String filename = RunConfiguration.getProjectDir()+ '/Data Files/ConsoleErrorsReport.xls'
		//creating an instance of HSSFWorkbook class
		HSSFWorkbook workbook = new HSSFWorkbook();
		//invoking creatSheet() method and passing the name of the sheet to be created
		HSSFSheet sheet = workbook.createSheet("page_title");
	}

	//not complete
	void addGlobalVariable(String name, def value) {
		GroovyShell shell1 = new GroovyShell()
		MetaClass mc = shell1.evaluate("internal.GlobalVariable").metaClass
		String getterName = "get" + name.capitalize()
		mc.'static'."$getterName" = { -> return value }
		mc.'static'."$name" = value
	}
}
