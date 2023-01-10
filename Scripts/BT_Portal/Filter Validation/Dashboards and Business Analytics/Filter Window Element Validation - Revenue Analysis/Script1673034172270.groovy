import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testobject.TestObject
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
def current_page = "Revenue Analysis"

///************************* Perform User Login ************************
//Determine if login has already occurred by checking for presence of User menu button
// If not found then Login to BT Portal'
	Login userLogin = new Login()
	userLogin.login(user_name, user_password)

///*********** Validate Revenue Analysis Filter Fields ****************

//Navigate to the Revenue Analysis page
Navigation menu = new Navigation()

///Only select Site if variable is populated
if (site_name > '') {
    menu.select_site(site_name)
}

///Open Revenue Analysis Page and set the currentPage variable
menu.select_menu_page('business-analytics-li', null, 'Revenue Analysis')


//Open Filters Menu
Filters filtersWindow = new Filters()
filtersWindow.open_filters_window(object_timeout)


//Validate Revenue Analysis Filter Sections

///General Buttons
filtersWindow.validate_filters_general_elements(current_page, object_timeout)

///Saved Filters Elements
filtersWindow.validate_filters_savedfilters_elements(current_page, object_timeout)

///Revenue Analysis Time Period Section Elements
filtersWindow.validate_filters_timeperiod_elements(current_page, object_timeout)

//Revenue Analysis Marketing Elements
filtersWindow.validate_filters_marketing_elements(current_page, object_timeout)

//Revenue Analysis Network Infrastructure Elements
filtersWindow.validate_filters_networkinfrastructure_elements(current_page, object_timeout)

//Revenue Analysis Page Segmentation Elements
filtersWindow.validate_filters_pagesegmentation_elements(current_page, object_timeout)

//Revenue Analysis Browsers and Devices Elements
filtersWindow.validate_filters_browsers_and_devices_elements(current_page, object_timeout)

//Revenue Analysis Display Options Elements
filtersWindow.validate_filters_geography_elements(current_page, object_timeout)

//Revenue Analysis Advanced Elements
filtersWindow.validate_filters_advanced_elements(current_page, object_timeout)





