import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling

import btt_portal.*

//***************************************************************************
// Script Name: Filter Window Element Validation - Dashboards Page
//
// Script Overview: Opens a new browser window, then iterates page by page through the portal filter options to validate that the expected Filter Fields are present for the given user
//
// Change History: 
//  Date              User               Change Made
//  01/02/2023        Kevin Jackey       Initial Script Creation
//***************************************************************************

//***************** Object Definitions - Multi-Use ********************
KeywordLogger log = new KeywordLogger()
Common_Functions element_check = new Common_Functions()
def error_found = false
def test_object_path
def failure_handling = FailureHandling.CONTINUE_ON_FAILURE
def element_name
def current_page = "Dashboards"
 
///************************* Perform User Login ************************
//Determine if login has already occurred by checking for presence of User menu button
// If not found then Login to BT Portal'
	Login userLogin = new Login()
	userLogin.login(user_name, user_password)


///*********** Validate Dashboard Filter Fields ****************

//Navigate to the Dashboards page
Navigation menu = new Navigation()

///Only select Site if variable is populated
if (site_name > '') {
    menu.select_site(site_name)
}

///Open Dashboards Page and set the currentPage variable
menu.select_menu_page('dashboard-li', null, null)
currentPage = "Dashboards"


//Open Filters Menu
Filters filtersWindow = new Filters()
filtersWindow.open_filters_window(object_timeout)


//Validate Dashboard Filter Section

///General Buttons
filtersWindow.validate_filters_general_elements(current_page, object_timeout)

///Saved Filters Elements
filtersWindow.validate_filters_savedfilters_elements(current_page, object_timeout)


///Dashboards Header Section Elements
filtersWindow.validate_filters_dashboardsheader_elements(current_page, object_timeout)

//Dashboard Marketing Elements
filtersWindow.validate_filters_marketing_elements(current_page, object_timeout)

//Dashboard Network Infrastructure Elements
filtersWindow.validate_filters_networkinfrastructure_elements(current_page, object_timeout)

//Dashboard Page Segmentation Elements
filtersWindow.validate_filters_pagesegmentation_elements(current_page, object_timeout)

//Dashboard Geography Elements
filtersWindow.validate_filters_geography_elements(current_page, object_timeout)

//Dashboard Advanced Elements
filtersWindow.validate_filters_advanced_elements(current_page, object_timeout)



















