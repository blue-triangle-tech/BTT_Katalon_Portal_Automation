import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*

//***************************************************************************
// Script Name: Filter Window Element Validation - Brand Calculator Page
//
// Script Overview: Opens a new browser window, then iterates page by page through the portal filter options to validate that the expected Filter Fields are present for the given user
//
// Change History: 
//  Date              User               Change Made
//  01/10/2023        Kevin Jackey       Initial Script Creation
//***************************************************************************


//***************** Object Definitions - Multi-Use ********************
KeywordLogger log = new KeywordLogger()
Common_Functions element_check = new Common_Functions()
def error_found = false
def test_object_path
def failure_handling
def element_name
def current_page = "Brand Calculator"

///************************* Perform User Login ************************
//Determine if login has already occurred by checking for presence of User menu button
// If not found then Login to BT Portal'
	Login userLogin = new Login()
	userLogin.login(user_name, user_password)

///*********** Validate Brand Calculator Filter Fields ****************

//Navigate to the Brand Calculator page
Navigation menu = new Navigation()

///Only select Site if variable is populated
if (site_name > '') {
    menu.select_site(site_name)
}

///Open Brand Calculator Page and set the currentPage variable
menu.select_menu_page('business-analytics-li', null, 'Brand Calculator')

//Close "No Brand Information" Info modal if it appears
///Check for OK button on dialog modal
element_name = "Brand Calculator Modal popup"
test_object_path = ('Object Repository/Brand Analysis/button_NoBrandAccessOK')
error_found = element_check.validate_object_exists(element_name, test_object_path, 15 , failure_handling, error_found)

///If found, close the modal by clicking the "OK" buton
if (error_found) {
} else {
	log.logInfo ('Brand Calculator Modal popup found')
	TestObject close_button = findTestObject('Object Repository/Brand Analysis/button_NoBrandAccessOK')
	WebUI.click(close_button)
}

//Open Filters Menu
Filters filtersWindow = new Filters()
filtersWindow.open_filters_window(object_timeout)


//Validate Brand Calculator Filter Sections

///General Buttons
filtersWindow.validate_filters_general_elements(current_page, object_timeout)

///Saved Filters Elements
filtersWindow.validate_filters_savedfilters_elements(current_page, object_timeout)

///Brand Calculator Time Period Section Elements
filtersWindow.validate_filters_timeperiod_elements(current_page, object_timeout)

//Brand Calculator Marketing Elements
filtersWindow.validate_filters_marketing_elements(current_page, object_timeout)

//Brand Calculator Network Infrastructure Elements
filtersWindow.validate_filters_networkinfrastructure_elements(current_page, object_timeout)

//Brand Calculator Page Segmentation Elements
filtersWindow.validate_filters_pagesegmentation_elements(current_page, object_timeout)

//Brand Calculator Browsers and Devices Elements
filtersWindow.validate_filters_browsers_and_devices_elements(current_page, object_timeout)

//Brand Calculator Geography  Elements
filtersWindow.validate_filters_geography_elements(current_page, object_timeout)

//Brand Calculator Advanced Elements
filtersWindow.validate_filters_advanced_elements(current_page, object_timeout)




