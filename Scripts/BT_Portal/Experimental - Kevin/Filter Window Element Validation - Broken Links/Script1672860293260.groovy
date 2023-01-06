import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*

//***************************************************************************
// Script Name: Filter Window Element Validation - Broken Links Page
//
// Script Overview: Opens a new browser window, then iterates page by page through the portal filter options to validate that the expected Filter Fields are present for the given user
//
// Change History: 
//  Date              User               Change Made
//  01/04/2023        Kevin Jackey       Initial Script Creation
//***************************************************************************


//***************** Object Definitions - Multi-Use ********************
KeywordLogger log = new KeywordLogger()
def current_page = "Broken Links"

///************************* Perform User Login ************************
//Determine if login has already occurred by checking for presence of User menu button
// If not found then Login to BT Portal'
	Login userLogin = new Login()
	userLogin.login(user_name, user_password)

///*********** Validate Broken Links Filter Fields ****************

//Navigate to the Broken Links page
Navigation menu = new Navigation()

///Only select Site if variable is populated
if (site_name > '') {
    menu.select_site(site_name)
}

///Open Broken Links Page and set the currentPage variable
menu.select_menu_page('business-analytics-li', 'Revenue Opportunity', 'Broken Links')


//Open Filters Menu
Filters filtersWindow = new Filters()
filtersWindow.open_filters_window(object_timeout)


//Validate Broken Links Filter Section

///General Buttons
filtersWindow.validate_filters_general_elements(current_page, object_timeout)

///Saved Filters Elements
filtersWindow.validate_filters_savedfilters_elements(current_page, object_timeout)

///Broken Links Time Period Section Elements
filtersWindow.validate_filters_timeperiod_elements(current_page, object_timeout)


/*

//Broken Links Marketing Elements
filtersWindow.validate_filters_marketing_elements(currentPage, object_timeout)

//Broken Links Network Infrastructure Elements
filtersWindow.validate_filters_networkinfrastructure_elements(currentPage, object_timeout)

//Broken Links Page Segmentation Elements
filtersWindow.validate_filters_pagesegmentation_elements(currentPage, object_timeout)

//Broken Links Geography Elements
filtersWindow.validate_filters_geography_elements(currentPage, object_timeout)

//Broken Links Advanced Elements
filtersWindow.validate_filters_advanced_elements(currentPage, object_timeout)

*/

















