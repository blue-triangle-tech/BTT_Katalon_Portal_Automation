import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*

//***************************************************************************
// Script Name: Filter Window Element Validation - RUM Web Browser Performance Overview Page
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
def current_page = "RUM Web Browser Performance Overview"

///************************* Perform User Login ************************
//Determine if login has already occurred by checking for presence of User menu button
// If not found then Login to BT Portal'
	Login userLogin = new Login()
	userLogin.login(user_name, user_password)

///*********** Validate RUM Web Browser Performance Overview Filter Fields ****************

//Navigate to the RUM Web Browser Performance Overview page
Navigation menu = new Navigation()

///Only select Site if variable is populated
if (site_name > '') {
    menu.select_site(site_name)
}

///Open RUM Web Browser Performance Overview Page and set the currentPage variable
menu.select_menu_page('real-user-monitoring-li', 'Web Browser' , 'Real User Performance Overview')

//Open Filters Menu
Filters filtersWindow = new Filters()
filtersWindow.open_filters_window(object_timeout)


//Validate RUM Web Browser Performance Overview Filter Sections

///General Buttons
filtersWindow.validate_filters_general_elements(current_page, object_timeout)

///Saved Filters Elements
filtersWindow.validate_filters_savedfilters_elements(current_page, object_timeout)

///RUM Web Browser Performance Overview Time Period Section Elements
filtersWindow.validate_filters_timeperiod_elements(current_page, object_timeout)

//RUM Web Browser Performance Overview Marketing Elements
filtersWindow.validate_filters_marketing_elements(current_page, object_timeout)

//RUM Web Browser Performance Overview Network Infrastructure Elements
filtersWindow.validate_filters_networkinfrastructure_elements(current_page, object_timeout)

//RUM Web Browser Performance Overview Page Segmentation Elements
filtersWindow.validate_filters_pagesegmentation_elements(current_page, object_timeout)

//RUM Web Browser Performance Overview Browsers and Devices Elements
filtersWindow.validate_filters_browsers_and_devices_elements(current_page, object_timeout)

//RUM Web Browser Performance Overview Geography  Elements
filtersWindow.validate_filters_geography_elements(current_page, object_timeout)

//RUM Web Browser Performance Overview Advanced Elements
filtersWindow.validate_filters_advanced_elements(current_page, object_timeout)
