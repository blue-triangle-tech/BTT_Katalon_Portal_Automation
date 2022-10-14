import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase
import com.kms.katalon.core.configuration.RunConfiguration

import btt_portal.Login
import internal.GlobalVariable

/**
 * Some methods below are samples for using SetUp/TearDown in a test suite.
 */

/**
 * Setup test suite environment.
 */



@SetUp(skipped = false) // Please change skipped to be false to activate this method.
def setUp() {
	

	Login user_login = new Login()
	user_login.login('qa.PartCons1', System.getenv('BTT_Automation_Pass'))
}

/**
 * Clean test suites environment.
 */
@TearDown(skipped = false) // Please change skipped to be false to activate this method.
def tearDown() {
	
	String page_title ='Executive Reports'
	String filename = RunConfiguration.getProjectDir()+ '/Data Files/' + page_title + '.xls'
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet sheet = workbook.createSheet("page_title");
	HSSFRow header = sheet.createRow((short)0);
	HSSFRow error_data = sheet.createRow((short)1);
	List kpi_errors = GlobalVariable.kpi
	List crux_errors = GlobalVariable.crux
	String kpi_error_string = kpi_errors.toListString()
	String crux_error_string = crux_errors.toListString()
	header.createCell(0).setCellValue(kpi_error_string);
	header.createCell(1).setCellValue(crux_error_string);
	FileOutputStream fileOut = new FileOutputStream(filename);
	workbook.write(fileOut);
	fileOut.close();
	workbook.close()
	
}

/**
 * Run before each test case starts.
 */
@SetupTestCase(skipped = false) // Please change skipped to be false to activate this method.
def setupTestCase() {
	// Put your code here.
}

/**
 * Run after each test case ends.
 */
@TearDownTestCase(skipped = false) // Please change skipped to be false to activate this method.
def tearDownTestCase() {
	// Put your code here.
}

/**
 * References:
 * Groovy tutorial page: http://docs.groovy-lang.org/next/html/documentation/
 */