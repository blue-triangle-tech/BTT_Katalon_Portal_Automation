import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import btt_portal.*
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogEntry


Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site(site)
menu.select_menu_page('executive-reports-li', null, 'Executive CrUX Report')

Executive_Reports_Crux crux = new Executive_Reports_Crux()
crux.verify_crux_report()

Logging console_errors = new Logging()
List errors = console_errors.get_console_errors('SEVERE')
console_errors.addGlobalVariable('crux', errors)





