import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.TestData

import btt_portal.*

TestData portal_pages = findTestData('Data Files/Synthetic and Real User Monitoring Pages')
//List logins = ['qa.vieweracct1', 'qa.PartCons1']

for (username in logins) {

Login user_login = new Login()
user_login.login(username, System.getenv('BTT_Automation_Pass'))

//List sites = ['Demo eCommerce Global','lululemon', 'GDC Test Site 1', 'Blue Triangle']

for (site in sites) {
Navigation menu_s = new Navigation()
menu_s.select_site(site)

Synthetic_Monitoring synth = new Synthetic_Monitoring()

String page_title ='Synthetic Real Browser'
String filename = RunConfiguration.getProjectDir()+ '/Data Files/' + page_title + '.xls'
HSSFWorkbook workbook = new HSSFWorkbook();
HSSFSheet sheet = workbook.createSheet("page_title");
HSSFRow header = sheet.createRow((short)0);
HSSFRow error_data = sheet.createRow((short)1);

int y = 0
for (x = 1; x <= 8; x++) {
	
    String synthetic_page = portal_pages.getValue('Page', x)
	header.createCell(x-1).setCellValue(synthetic_page);
    menu_s.select_menu_page('synthetic-monitoring-li', 'Real Browser', synthetic_page)
	synth.verify_real_browser_pages(x)
	Logging console_errors = new Logging()
	List errors = console_errors.get_console_errors('SEVERE')
	String error_string = errors.toListString()
	error_data.createCell(x-1).setCellValue(error_string);
	
	

}
FileOutputStream fileOut = new FileOutputStream(filename);
workbook.write(fileOut);
fileOut.close();
workbook.close()
}
}