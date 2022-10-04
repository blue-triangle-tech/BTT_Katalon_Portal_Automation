import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

import com.kms.katalon.core.configuration.RunConfiguration

import btt_portal.*


Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

List sites = ['Demo eCommerce Global']

for (site in sites) {
Navigation menu = new Navigation()
menu.select_site(site)


menu.select_menu_page('executive-reports-li', null, 'Digital Experience Overview')
Digital_Experience_Overview deo = new Digital_Experience_Overview()
deo.verify_score_cards()
Logging console_errors = new Logging()
List errors = console_errors.get_console_errors('SEVERE')

menu.select_menu_page('executive-reports-li', null, 'Executive KPI Report')
Executive_Reports_KPI kpi = new Executive_Reports_KPI()
kpi.verify_KPI_report()
Logging console_errors2 = new Logging()
List errors2 = console_errors2.get_console_errors('SEVERE')

menu.select_menu_page('executive-reports-li', null, 'Executive CrUX Report')
Executive_Reports_Crux crux = new Executive_Reports_Crux()
crux.verify_crux_report()
Logging console_errors3 = new Logging()
List errors3 = console_errors3.get_console_errors('SEVERE')

}

