import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
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


//Determine if login has already occurred by checking for presence of User menu button
// If not found then Login to BT Portal'
TestObject userIcon = findTestObject('Object Repository/Common Portal Elements/button_UserMenu')

if (WebUI.verifyElementPresent(userIcon, objectTimeout) == false) {
	Login userLogin = new Login()
	userLogin.login(userName, userPassword)
}

///*********** Validate Dashboard Filter Fields ****************

//Navigate to the Dashboards page
Navigation menu = new Navigation()

///Only select Site if variable is populated
if (siteName > '') {
    menu.select_site(siteName)
}

///Open Dashboards Page and set the currentPage variable
menu.select_menu_page('dashboard-li', null, null)
currentPage = "Dashboards"


//Open Filters Menu
Filters filtersWindow = new Filters()
filtersWindow.open_filters_window(objectTimeout)


//Validate Dashboard Filter Section

///General Buttons
filtersWindow.validate_filters_general_elements(currentPage, objectTimeout)

///Saved Filters Elements
filtersWindow.validate_filters_savedfilters_elements(objectTimeout)


///Dashboards Header Section Elements
filtersWindow.validate_filters_dashboardsheader_elements(currentPage, objectTimeout)

//Dashboard Marketing Elements
filtersWindow.validate_filters_marketing_elements(currentPage, objectTimeout)

//Dashboard Network Infrastructure Elements
filtersWindow.validate_filters_networkinfrastructure_elements(currentPage, objectTimeout)

//Dashboard Page Segmentation Elements
filtersWindow.validate_filters_pagesegmentation_elements(currentPage, objectTimeout)

//Dashboard Geography Elements
filtersWindow.validate_filters_geography_elements(currentPage, objectTimeout)

//Dashboard Advanced Elements
filtersWindow.validate_filters_advanced_elements(currentPage, objectTimeout)



















