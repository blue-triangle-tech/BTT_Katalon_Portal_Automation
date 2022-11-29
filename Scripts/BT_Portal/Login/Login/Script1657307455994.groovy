import com.kms.katalon.core.webui.driver.DriverFactory

import btt_portal.*

Login user_login = new Login()
user_login.login('qa.bttcons4', System.getenv('BTT_Automation_Pass'))




