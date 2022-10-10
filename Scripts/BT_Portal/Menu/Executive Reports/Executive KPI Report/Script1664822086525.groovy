import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*

Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

Navigation menu = new Navigation()
menu.select_site(site)
menu.select_menu_page('executive-reports-li', null, 'Executive KPI Report')

Executive_Reports_KPI kpi = new Executive_Reports_KPI()
kpi.verify_KPI_report()

Logging console_errors = new Logging()
List errors = console_errors.get_console_errors('SEVERE')
console_errors.addGlobalVariable('kpi', errors)


