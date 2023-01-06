package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*


public class Filters {

	//***************** Variable / Object Definitions - Multi-Use ********************
	KeywordLogger log = new KeywordLogger()
	Common_Functions element_check = new Common_Functions()
	def error_found = false
	def test_object_path
	def failure_handling
	def element_name


	//***************** Function Library - General Use ********************

	//***************************************************************************
	// Function Name: open_filters_window
	//
	// Function Overview: Opens the main filters section window
	//
	// Function Input Variable(s): None
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void open_filters_window(int object_timeout) {
		//Open Filters Element
		TestObject FilterButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/button_ToggleFilters')
		WebUI.click(FilterButton)

		//Ensure Filters section opened correctly using the Cancel button, stop script if step fails
		TestObject CancelButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/button_Cancel')
		WebUI.waitForElementPresent(CancelButton, object_timeout, FailureHandling.STOP_ON_FAILURE)

	}

	//***************************************************************************
	// Function Name: set_predefined_time_period
	//
	// Function Overview: Selects the Top Level Time Period button to open the Filters Menu -> Date Range Selector,
	//                    then populates and applies the desired date range based on script inputs using the standard (non-custom) date ranges
	//
	// Function Input Variable(s):
	//                    data_range_key - Date Range to be selected, format is to be one of the predefined time periods (ex: "Last 3 Hours", "Yesterday", etc.)
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void set_predefined_time_period(String data_range_key) {

		//Create Initial Test Object Definitions
		TestObject time_period = findTestObject('Object Repository/Filters/Page Header Shortcuts/Time Period/Time Period Selector')
		TestObject apply_filters = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Apply')

		//Select Page Header Time Period Element
		WebUI.waitForElementClickable(time_period, 30)
		WebUI.click(time_period)

		//Select desired timeframe for filtering
		TestObject time_range = new TestObject("time range")
		time_range.addProperty("data-range-key", ConditionType.EQUALS, data_range_key)
		WebUI.click(time_range)

		//Apply timeframe filter
		WebUI.click(apply_filters)
	}


	//***************************************************************************
	// Function Name: lookback_period
	//
	// Function Overview: Selects and sets the current page Lookback period filter
	//
	// Function Input Variable(s):
	//                    lookback_period - Lookback timeframe to be selected, format is to be one of the predefined time periods (ex: "Last 7 Days", etc.)
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void set_lookback_period(String lookback_period) {

		//Create Initial Test Object Definitions
		TestObject lookback = findTestObject('Object Repository/Page_Synthetic Performance Overview/button_Lookback')

		//Select the lookback object and apply desired lookback timeframe
		WebUI.click(lookback)
		TestObject select_lookback = new TestObject("lookback")
		select_lookback.addProperty("data-value", ConditionType.EQUALS, lookback_period)

		//Apply timeframe filter
		WebUI.click(select_lookback)
	}


	//***************** Function Library - Validation Functions ********************

	//***************************************************************************
	// Function Name: validate_filters_advanced_elements
	//
	// Function Overview: Validates the presence of all expected Advanced section filter window elements (Elements appearing based on current page)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            object_timeout - Contains the desired object_timeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_advanced_elements (currentPage, object_timeout) {

		//Initialize error tracking variable
		error_found = false

		//Validate Beacon Type Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			element_name = "Beacon Type Dropdown"
			test_object_path = ('Object Repository/Filters/Main Filter Window/Advanced/dropdown_BeaconType')
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			element_name = "Beacon Type Clear Link"
			test_object_path = ('Object Repository/Filters/Main Filter Window/Advanced/link_beaconType_Clear')
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			//Perform Error Check Validation
			if (error_found) {
				KeywordUtil.markFailed ("Issue found in validate_filters_advanced_elements section, please check error log for details")
			}
		}

	}


	//***************************************************************************
	// Function Name: validate_filters_dashboardsheader_elements
	//
	// Function Overview: Validates the presence of all Dashboards page filter header window elements (Site dropdown, Time Lookback Offset Toggle, Days and Hours elector dropdowns )
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            object_timeout - Contains the desired object_timeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_dashboardsheader_elements (current_page, object_timeout) {

		//Initialize error tracking variable
		error_found = false

		//Bypass if not on Dashboards page

		if (current_page == "Dashboards") {
			element_name = "Validating Dashboards Page Header Elements"

			//Validate Site Dropdown
			element_name = "Site dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/dropdown_Site'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			//Validate Time Lookback Offset radial
			element_name = "Time Lookback Offset Radial"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/toggle_LookbackOffset'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			//Validate and select Time Lookback Days Dropdown
			element_name = "Time Lookback Days Dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/dropdown_revenueOffsetDays'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			//Validate and select Time Lookback Hours Dropdown
			element_name = "Time Lookback Hours Dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/dropdown_revenueOffsetHours'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			//Perform Error Check Validation
			if (error_found) {
				KeywordUtil.markFailed ("Issue found in validate_filters_dashboardsheader_elements section, please check error log for details")
			}

		}
	}

	//***************************************************************************
	// Function Name: validate_filters_general_elements
	//
	// Function Overview: Validates the presence of all General filters window elements (Cancel, Saved Filters, Apply Filters, Expand All, and Collapse All)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            object_timeout - Contains the desired object_timeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_general_elements(current_page, object_timeout) {

		//Initialize error tracking variable
		error_found = false

		//Validate Cancel Button
		element_name = "General Filter Cancel Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/button_Cancel'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Saved Filters Button
		element_name = "General Filter Saved Filters Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Apply Filters Button (Note that Dashboards uses a different identifier value for the Apply button)
		element_name = "General Filter Apply Filters Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/button_Apply_Dashboard_Page'

		if (current_page == "Dashboards") {
			test_object_path = 'Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/button_Apply_Dashboard_Page'
			element_name = "currentPage = Dashboards"
		} else {
			test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/button_ApplyFilters'
			element_name = "currentPage <> Dashboards"
		}

		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Expand All
		element_name = "General Filter Expand All Link"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/link_ExpandAll'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Collapse All
		element_name = "General Filter Collapse All Link"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/link_CollapseAll'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Perform Error Check Validation
		if (error_found) {
			KeywordUtil.markFailed ("Issue found in validate_filters_general_elements section, please check error log for details")
		}
	}


	//***************************************************************************
	// Function Name: validate_filters_geography_elements
	//
	// Function Overview: Validates the presence of all expected Geography section filter window elements (Elements appearing based on current page)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            object_timeout - Contains the desired object_timeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_geography_elements (current_page, object_timeout) {

		//Initialize error tracking variable
		error_found = false

		//Validate Country Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(current_page == "Dashboards")

		) {
			element_name = "Country Dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Geography/dropdown_Country'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			element_name = "Country Clear Link"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Geography/link_Country_Clear'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)
		}

		//Validate Region for indicated page(s) [Dashboards, ]
		if (
		(current_page == "Dashboards")

		) {
			element_name = "Region Dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Geography/dropdown_Region'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			element_name = "Region Clear Link"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Geography/link_RegionClear'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)
		}

		//Perform Error Check Validation
		if (error_found) {
			KeywordUtil.markFailed ("Issue found in validate_filters_geography_elements section, please check error log for details")
		}
	}

	//***************************************************************************
	// Function Name: validate_filters_marketing_elements
	//
	// Function Overview: Validates the presence of all expected marketing section filter window elements (Elements appearing based on current page)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            object_timeout - Contains the desired object_timeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_marketing_elements (current_page, object_timeout) {

		//Initialize error tracking variable
		error_found = false

		//Validate Campaign Name Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(current_page == "Dashboards")

		) {
			element_name = "Campaign Name Dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Marketing/dropdown_CampaignName'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			element_name = "Campaign Name Clear Link"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Marketing/link_CampaignName_Clear'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)
		}

		//Validate A/B Testing Segment Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(current_page == "Dashboards")

		) {
			element_name = "A/B Testing Segment Dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Marketing/dropdown_ABTestingSegment'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			element_name = "A/B Testing Segment  Clear Link"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Marketing/link_ABTestingSegmentClear'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)
		}

		//Perform Error Check Validation
		if (error_found) {
			KeywordUtil.markFailed ("Issue found in validate_filters_marketing_elements section, please check error log for details")
		}
	}

	//***************************************************************************
	// Function Name: validate_filters_networkinfrastructure_elements
	//
	// Function Overview: Validates the presence of all expected network infrastructure section filter window elements (Elements appearing based on current page)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            object_timeout - Contains the desired object_timeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_networkinfrastructure_elements (currentPage, object_timeout) {

		//Initialize error tracking variable
		error_found = false

		//Validate Datacenter Location Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			element_name = "Datacenter Location Dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Network Infrastructure/dropdown_DataCenterLocation'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			element_name = "Datacenter Location Clear Link"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Network Infrastructure/link_DataCenterLocation_Clear'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)
		}

		//Perform Error Check Validation
		if (error_found) {
			KeywordUtil.markFailed ("Issue found in validate_filters_networkinfrastructure_elements section, please check error log for details")
		}

	}

	//***************************************************************************
	// Function Name: validate_filters_pagesegmentation_elements
	//
	// Function Overview: Validates the presence of all expected Page Segmentation section filter window elements (Elements appearing based on current page)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            object_timeout - Contains the desired object_timeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_pagesegmentation_elements (current_page, object_timeout) {

		//Initialize error tracking variable
		error_found = false

		//Validate Traffic Segment Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(current_page == "Dashboards")

		) {
			element_name = "Traffic Segment Dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Page Segmentation/dropdown_TrafficSegment'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			element_name = "Traffic Segment Clear Link"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Page Segmentation/link_TrafficSegment_Clear'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)
		}

		//Validate Content / Page Groups Elements for indicated page(s) [Dashboards, ]
		if (
		(current_page == "Dashboards")

		) {
			element_name = "Content / Page Groups Dropdown"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Page Segmentation/dropdown_PageGroup'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

			element_name = "Content / Page Groups Clear Link"
			test_object_path = 'Object Repository/Filters/Main Filter Window/Page Segmentation/link_PageGroup_Clear'
			error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)
		}

		//Perform Error Check Validation
		if (error_found) {
			KeywordUtil.markFailed ("Issue found in validate_filters_pagesegmentation_elements section, please check error log for details")
		}
	}

	//***************************************************************************
	// Function Name: validate_filters_savedfilters_elements
	//
	// Function Overview: Validates the presence of all Saved Filter window elements (My Filters and Shared Filters tabs; Create new saved filter, Reset, Close, and Save buttons)
	//
	// Function Input Variable(s):
	//                            object_timeout - Contains the desired object_timeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_savedfilters_elements(current_page, object_timeout) {

		//Initialize error tracking variable
		error_found = false

		//Validate and select Saved Filters Button
		element_name = "and Click General Filter Saved Filters Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		// Click Saved Filters Button
		TestObject saved_filters_button = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters')
		WebUI.click(saved_filters_button)

		//Validate Create New Saved Filters
		element_name = "Saved Filters Create New Saved Filters button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters_CreateNewSavedFilter'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate My Filters Tab
		element_name = "Saved Filters My Filters Tab"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/SavedFilters/tab_SavedFilters_MyFilters'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Shared Filters Tab
		element_name = "Saved Filters Shared Filters Tab"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/SavedFilters/tab_SavedFilters_SharedFilters'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Reset Button
		element_name = "Saved Filters Reset Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters_Reset'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Save Button
		element_name = "Saved Filters Save Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters_Save'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate and select Close Button
		element_name = "and select Saved Filters Close Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters_Close'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		// Click Close Button
		TestObject close_button = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters_Close')
		WebUI.click(close_button)

		//Perform Error Check Validation
		if (error_found) {
			KeywordUtil.markFailed ("Issue found in validate_filters_savedfilters_elements section, please check error log for details")
		}

	}

	//***************************************************************************
	// Function Name: validate_filters_timeperiod_elements
	//
	// Function Overview: Validates the presence of all Time Period window elements (My Filters and Shared Filters tabs; Create new saved filter, Reset, Close, and Save buttons)
	//
	// Function Input Variable(s):
	//                            object_timeout - Contains the desired object_timeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_timeperiod_elements (current_page, object_timeout) {

		//Initialize error tracking variable
		error_found = false


		//Validate and select Time Period segment
		element_name = "Time Period Icon"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/icon_TimePeriod_DateSelector'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		// Click Time Period Button
		TestObject time_period_icon = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/icon_TimePeriod_DateSelector')
		WebUI.click(time_period_icon)

		//Validate Custom Date Range - Last 3 Hours
		element_name = "Last 3 Hours Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last3Hours'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Last 6 Hours
		element_name = "Last 6 Hours Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last6Hours'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Last 24 Hours
		element_name = "Last 24 Hours Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last24Hours'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Last 3 Days
		element_name = "Last 3 Days Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last3Days'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Last 7 Days
		element_name = "Last 7 Days Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last7Days'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Last 14 Days
		element_name = "Last 3 Days Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last14Days'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Last 30 Days
		element_name = "Last 30 Days Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last30Days'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Yesterday
		element_name = "Yesterday Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Yesterday'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Today
		element_name = "Today Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Today'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - This Month
		element_name = "This Month Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_ThisMonth'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Last Month
		element_name = "Last Month Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_LastMonth'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Validate Custom Date Range - Custom Range
		element_name = "Custom Range Button"
		test_object_path = 'Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_CustomRange'
		error_found = element_check.validate_object_exists(element_name, test_object_path, object_timeout, failure_handling, error_found)

		//Perform Error Check Validation
		if (error_found) {
			KeywordUtil.markFailed ("Issue found in validate_filters_timeperiod_elements section, please check error log for details")
		}
	}


}
