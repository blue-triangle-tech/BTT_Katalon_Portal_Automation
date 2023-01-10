import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*


//***************************************************************************
// Script Name: Filter Window Element Validation - Revenue Analysis Page
//
// Script Overview: Opens a new browser window, then iterates page by page through the portal filter options to validate that the expected Filter Fields are present for the given user
//
// Change History: 
//  Date              User               Change Made
//  01/06/2023        Kevin Jackey       Initial Script Creation
//***************************************************************************


//***************** Object Definitions - Multi-Use ********************
KeywordLogger log = new KeywordLogger()
Navigation menu = new Navigation()
Common_Functions element_check = new Common_Functions()
def error_found = false
def object_timeout = 10
def test_object_path
def failure_handling = FailureHandling.CONTINUE_ON_FAILURE
def element_name



//Create Datatable Link
TestData filter_validation = findTestData('Filter Validation Datatable')
int max_datatable_rows = filter_validation.getRowNumbers()


//Loop through Datatable 
for (int current_row = 1; current_row <= max_datatable_rows; current_row++) {
	log.logInfo ('On datatable row: ' + current_row.toString() + " of " + max_datatable_rows.toString() )
	
	//Initialize error_found variable
	error_found = false
	
	//Perform action based on value in "action" column
	def current_action = filter_validation.getValue("action", current_row)
	
	switch(current_action.toUpperCase()) {
		
		case ("CLICK"):
			//Click indicated element
			element_name = filter_validation.getValue("object_name", current_row)
			test_object_path = filter_validation.getValue("object_repo_link_path", current_row)
			
			TestObject saved_filters_button = findTestObject(test_object_path)
			WebUI.click(saved_filters_button)
		
		case ("COMMENT"):
			//Make comment in log for text in addtl_info_1 column
			log.logInfo (filter_validation.getValue("addtl_info_1", current_row))
		
		case ("LOGIN"):
			log.logInfo ('Performing Login action')
		
			//Perform user login using values from additional details columns 1 and 2
			def user_name = filter_validation.getValue("addtl_info_1", current_row)
			def user_password = filter_validation.getValue("addtl_info_2", current_row)
			
			Login userLogin = new Login()
			userLogin.login(user_name, user_password)
			break;
		
			case ("OPEN_FILTER"):
			//Open Filters Menu
			Filters filtersWindow = new Filters()
			filtersWindow.open_filters_window(object_timeout)
			break;
			
		case ("OPEN_PAGE"):
			log.logInfo('Performing Open Page action') 
			
			//Select desired page using values from page_menu_category, page_menu_type, and page_menu_page columns
			def page_category = filter_validation.getValue("page_menu_category", current_row)
			def page_type = filter_validation.getValue("page_menu_type", current_row)
			def page_page = filter_validation.getValue("page_menu_page", current_row)
			
			log.logInfo('Performing Page Open action for page_category: ' + page_category + ', page_type = ' + page_type + ', page_page = ' + page_page)
			
			//Account for possibility of page_type or page_page being empty
			if ((page_type == "") || (page_type == "null")) {
				page_type = null
			}
			
			if ((page_page == "") || (page_page == "null"))  {
				page_page = null
			}
			
			menu.select_menu_page(page_category, page_type, page_page)
			break;
			
		
		case ("SITE_SELECT"):
			//Select user site using value from additional details column 1
			def site_selected = filter_validation.getValue("addtl_info_1", current_row)
			log.logInfo('Performing Site Select action for site: ' + site_selected)

			menu.select_site(site_selected)
			break;
			
		case ("VALIDATE"):
			//Validate that the item referred to in the object_repo_link_path field exists
			element_name = filter_validation.getValue("object_name", current_row)
			test_object_path = filter_validation.getValue("object_repo_link_path", current_row)
			
			log.logInfo('Validating Object: ' + element_name)
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			//Throw error message if there was an issue finding the indicated object
			if (error_found) {
				log.logError ("Issue found in detecting the " + element_name + " object")
				KeywordUtil.markFailed ("Issue found in detecting the " + element_name + " object")
			}
			
			break;
		
		//If unrecognized action is found, throw error and end the script	
		default:
			KeywordUtil.markFailed ('Unrecognized action: ' + current_action.toUpperCase() + ' found on table row ' + current_row)
			current_row = (max_datatable_rows + 1)
			
	}
}