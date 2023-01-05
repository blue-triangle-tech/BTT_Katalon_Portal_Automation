package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


public class Filters {

	//***************** Object Definitions - Multi-Use ********************
	KeywordLogger log = new KeywordLogger()


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

	public void open_filters_window(int objectTimeout) {
		//Open Filters Element
		TestObject FilterButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/button_ToggleFilters')
		WebUI.click(FilterButton)

		//Ensure Filters section opened correctly using the Cancel button, stop script if step fails
		TestObject CancelButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/button_Cancel')
		WebUI.waitForElementPresent(CancelButton, objectTimeout, FailureHandling.STOP_ON_FAILURE)

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
	//                            objectTimeout - Contains the desired objectTimeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_advanced_elements (currentPage, int objectTimeout) {

		//Validate Beacon Type Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			log.logInfo("Validate Beacon Type Dropdown")
			TestObject beaconTypeDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Advanced/dropdown_BeaconType')
			WebUI.verifyElementPresent(beaconTypeDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			log.logInfo("Validate Beacon Type Clear Link")
			TestObject beaconTypeClearLink = findTestObject('Object Repository/Filters/Main Filter Window/Advanced/link_beaconType_Clear')
			WebUI.verifyElementPresent(beaconTypeClearLink, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		}


	}


	//***************************************************************************
	// Function Name: validate_filters_dashboardsheader_elements
	//
	// Function Overview: Validates the presence of all Dashboards page filter header window elements (Site dropdown, Time Lookback Offset Toggle, Days and Hours elector dropdowns )
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            objectTimeout - Contains the desired objectTimeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_dashboardsheader_elements (currentPage, int objectTimeout) {

		//Bypass if not on Dashboards page

		if (currentPage == "Dashboards") {
			log.logInfo("Validating Dashboards Page Header Elements")

			//Validate Site Dropdown
			log.logInfo("Validate Site dropdown")
			TestObject siteDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/dropdown_Site')
			WebUI.verifyElementPresent(siteDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			//Validate Time Lookback Offset radial
			log.logInfo("Validate Time Lookback Offset Radial")
			TestObject lookbackOffsetToggle = findTestObject('Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/toggle_LookbackOffset')
			WebUI.verifyElementPresent(lookbackOffsetToggle, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			//Validate and select Time Lookback Days Dropdown
			log.logInfo("Validate Time Lookback Days Dropdown")
			TestObject lookbackDaysDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/dropdown_revenueOffsetDays')
			WebUI.verifyElementPresent(lookbackDaysDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			//Validate and select Time Lookback Hours Dropdown
			log.logInfo("Validate Time Lookback Hours Dropdown")
			TestObject lookbackHoursDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/dropdown_revenueOffsetHours')
			WebUI.verifyElementPresent(lookbackHoursDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)


		}
	}

	//***************************************************************************
	// Function Name: validate_filters_general_elements
	//
	// Function Overview: Validates the presence of all General filters window elements (Cancel, Saved Filters, Apply Filters, Expand All, and Collapse All)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            objectTimeout - Contains the desired objectTimeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_general_elements(currentPage, int objectTimeout) {

		//Validate Cancel Button
		log.logInfo("Validate General Filter Cancel Button")
		TestObject cancelButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/button_Cancel')
		WebUI.waitForElementPresent(cancelButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		//Validate Saved Filters Button
		log.logInfo("Validate General Filter Saved Filters Button")
		TestObject savedFiltersButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters')
		WebUI.verifyElementPresent(savedFiltersButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		//Validate Apply Filters Button (Note that Dashboards uses a different identifier value for the Apply button)
		log.logInfo("Validate General Filter Apply Filters Button")
		TestObject applyFiltersButton = findTestObject('Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/button_Apply_Dashboard_Page')

		if (currentPage == "Dashboards") {
			applyFiltersButton = findTestObject('Object Repository/Filters/Main Filter Window/Page Specific/Dashboards/button_Apply_Dashboard_Page')
			log.logInfo("currentPage = Dashboards")
		} else {
			applyFiltersButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/button_ApplyFilters')
			log.logInfo("currentPage <> Dashboards")
		}

		WebUI.verifyElementPresent(applyFiltersButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		//Expand All
		log.logInfo("Validate General Filter Expand All Link")
		TestObject expandAll = findTestObject('Object Repository/Filters/Main Filter Window/General Items/link_ExpandAll')
		WebUI.verifyElementPresent(expandAll, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		//Collapse All
		log.logInfo("Validate General Filter Collapse All Link")
		TestObject collapseAll = findTestObject('Object Repository/Filters/Main Filter Window/General Items/link_CollapseAll')
		WebUI.verifyElementPresent(collapseAll, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

	}


	//***************************************************************************
	// Function Name: validate_filters_geography_elements
	//
	// Function Overview: Validates the presence of all expected Geography section filter window elements (Elements appearing based on current page)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            objectTimeout - Contains the desired objectTimeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_geography_elements (currentPage, int objectTimeout) {

		//Validate Country Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			log.logInfo("Validate Country Dropdown")
			TestObject countryDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Geography/dropdown_Country')
			WebUI.verifyElementPresent(countryDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			log.logInfo("Validate Country Clear Link")
			TestObject countryClearLink = findTestObject('Object Repository/Filters/Main Filter Window/Geography/link_Country_Clear')
			WebUI.verifyElementPresent(countryClearLink, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		}

		//Validate Region for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			log.logInfo("Validate Region Dropdown")
			TestObject regionDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Geography/dropdown_Region')
			WebUI.verifyElementPresent(regionDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			log.logInfo("Validate Region Clear Link")
			TestObject regionClearLink = findTestObject('Object Repository/Filters/Main Filter Window/Geography/link_RegionClear')
			WebUI.verifyElementPresent(regionClearLink, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		}


	}

	//***************************************************************************
	// Function Name: validate_filters_marketing_elements
	//
	// Function Overview: Validates the presence of all expected marketing section filter window elements (Elements appearing based on current page)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            objectTimeout - Contains the desired objectTimeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_marketing_elements (currentPage, int objectTimeout) {

		//Validate Campaign Name Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			log.logInfo("Validate Campaign Name Dropdown")
			TestObject campaignNameDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Marketing/dropdown_CampaignName')
			WebUI.verifyElementPresent(campaignNameDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			log.logInfo("Validate Campaign Name Clear Link")
			TestObject campaignNameClearLink = findTestObject('Object Repository/Filters/Main Filter Window/Marketing/link_CampaignName_Clear')
			WebUI.verifyElementPresent(campaignNameClearLink, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		}

		//Validate A/B Testing Segment Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			log.logInfo("Validate A/B Testing Segment Dropdown")
			TestObject abtestingSegmentDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Marketing/dropdown_ABTestingSegment')
			WebUI.verifyElementPresent(abtestingSegmentDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			log.logInfo("Validate A/B Testing Segment  Clear Link")
			TestObject abtestingClear = findTestObject('Object Repository/Filters/Main Filter Window/Marketing/link_ABTestingSegmentClear')
			WebUI.verifyElementPresent(abtestingClear, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		}


	}

	//***************************************************************************
	// Function Name: validate_filters_networkinfrastructure_elements
	//
	// Function Overview: Validates the presence of all expected network infrastructure section filter window elements (Elements appearing based on current page)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            objectTimeout - Contains the desired objectTimeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_networkinfrastructure_elements (currentPage, int objectTimeout) {

		//Validate Datacenter Location Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			log.logInfo("Validate Datacenter Location Dropdown")
			TestObject datacenterLocationNameDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Network Infrastructure/dropdown_DataCenterLocation')
			WebUI.verifyElementPresent(datacenterLocationNameDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			log.logInfo("Validate Datacenter Location Clear Link")
			TestObject datacenterLocationClearLink = findTestObject('Object Repository/Filters/Main Filter Window/Network Infrastructure/link_DataCenterLocation_Clear')
			WebUI.verifyElementPresent(datacenterLocationClearLink, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		}



	}

	//***************************************************************************
	// Function Name: validate_filters_pagesegmentation_elements
	//
	// Function Overview: Validates the presence of all expected Page Segmentation section filter window elements (Elements appearing based on current page)
	//
	// Function Input Variable(s):
	//                            currentPage - Contains the name of the current page, used for determining if a given field should be present within the current page filter settings
	//                            objectTimeout - Contains the desired objectTimeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_pagesegmentation_elements (currentPage, int objectTimeout) {

		//Validate Traffic Segment Dropdown Elements for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			log.logInfo("Validate Traffic Segment Dropdown")
			TestObject trafficSegmentDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Page Segmentation/dropdown_TrafficSegment')
			WebUI.verifyElementPresent(trafficSegmentDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			log.logInfo("Validate Traffic Segment Clear Link")
			TestObject trafficSegmentClearLink = findTestObject('Object Repository/Filters/Main Filter Window/Page Segmentation/link_TrafficSegment_Clear')
			WebUI.verifyElementPresent(trafficSegmentClearLink, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		}

		//Validate Content / Page Groups Elements for indicated page(s) [Dashboards, ]
		if (
		(currentPage == "Dashboards")

		) {
			log.logInfo("Validate Content / Page Groups Dropdown")
			TestObject contentPageGroupsDropdown = findTestObject('Object Repository/Filters/Main Filter Window/Page Segmentation/dropdown_PageGroup')
			WebUI.verifyElementPresent(contentPageGroupsDropdown, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

			log.logInfo("Validate Content / Page Groups Clear Link")
			TestObject contentPageGroupsClearLink = findTestObject('Object Repository/Filters/Main Filter Window/Page Segmentation/link_PageGroup_Clear')
			WebUI.verifyElementPresent(contentPageGroupsClearLink, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		}


	}

	//***************************************************************************
	// Function Name: validate_filters_savedfilters_elements
	//
	// Function Overview: Validates the presence of all Saved Filter window elements (My Filters and Shared Filters tabs; Create new saved filter, Reset, Close, and Save buttons)
	//
	// Function Input Variable(s):
	//                            objectTimeout - Contains the desired objectTimeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public void validate_filters_savedfilters_elements(int objectTimeout) {

		//Validate and select Saved Filters Button
		log.logInfo("Validate and Click General Filter Saved Filters Button")
		TestObject savedFiltersButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters')
		WebUI.verifyElementPresent(savedFiltersButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.click(savedFiltersButton)

		//Validate Create New Saved Filters
		log.logInfo("Validate Saved Filters Create New Saved Filters button")
		TestObject createNewSavedFiltersButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters_CreateNewSavedFilter')
		WebUI.verifyElementPresent(createNewSavedFiltersButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		//Validate My Filters Tab
		log.logInfo("Validate Saved Filters My Filters Tab")
		TestObject myFiltersTab = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/tab_SavedFilters_MyFilters')
		WebUI.verifyElementPresent(myFiltersTab, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		//Validate Shared Filters Tab
		log.logInfo("Validate Saved Filters Shared Filters Tab")
		TestObject sharedFiltersTab = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/tab_SavedFilters_SharedFilters')
		WebUI.verifyElementPresent(sharedFiltersTab, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		//Validate Reset Button
		log.logInfo("Validate Saved Filters Reset Button")
		TestObject resetButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters_Reset')
		WebUI.verifyElementPresent(resetButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		//Validate Save Button
		log.logInfo("Validate Saved Filters Save Button")
		TestObject saveButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters_Save')
		WebUI.verifyElementPresent(saveButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		//Validate and select Close Button
		log.logInfo("Validate and select Saved Filters Close Button")
		TestObject closeButton = findTestObject('Object Repository/Filters/Main Filter Window/General Items/SavedFilters/button_SavedFilters_Close')
		WebUI.verifyElementPresent(closeButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.click(closeButton)

	}

	//***************************************************************************
	 // Function Name: validate_filters_timeperiod_elements
	 //
	 // Function Overview: Validates the presence of all Time Period window elements (My Filters and Shared Filters tabs; Create new saved filter, Reset, Close, and Save buttons)
	 //
	 // Function Input Variable(s):
	 //                            objectTimeout - Contains the desired objectTimeout timeframe specified in the script [if zero then the user's default page load timeout will be used]
	 //
	 // Function Output Variable(s): None
	 //
	 // Function Return Value: None
	 //***************************************************************************
 
	 public void validate_filters_timeperiod_elements (int objectTimeout) {
 
		 //Validate and select Time Period segment
		 log.logInfo("Validate and Click Time Period Icon")
		 TestObject timePeriodIcon = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/icon_TimePeriod_DateSelector')
		 WebUI.verifyElementPresent(timePeriodIcon, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 WebUI.click(timePeriodIcon)
 
		//Validate Custom Date Range - Last 3 Hours
		 log.logInfo("Validate Last 3 Hours Button")
		 TestObject lastThreeHoursButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last3Hours')
		 WebUI.verifyElementPresent(lastThreeHoursButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 
		 //Validate Custom Date Range - Last 6 Hours
		 log.logInfo("Validate Last 6 Hours Button")
		 TestObject lastSixHoursButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last6Hours')
		 WebUI.verifyElementPresent(lastSixHoursButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 
		 //Validate Custom Date Range - Last 24 Hours
		 log.logInfo("Validate Last 24 Hours Button")
		 TestObject lastTwentyFourHoursButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last24Hours')
		 if ((WebUI.verifyElementPresent(lastTwentyFourHoursButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)) == false) {
			 log.logInfo("Error Logic -  Last 24 Hours Button")
			 KeywordUtil.markFailed ("Issue found in Last 24 Hours button, please check error log for details")
		 }
	 
		 
		 //Validate Custom Date Range - Last 3 Days
		 log.logInfo("Validate Last 3 Days Button")
		 TestObject lastThreeDaysButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last3Days')
		 WebUI.verifyElementPresent(lastThreeDaysButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 
		 //Validate Custom Date Range - Last 7 Days
		 log.logInfo("Validate Last 7 Days Button")
		 TestObject lastSevenDaysButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last7Days')
		 WebUI.verifyElementPresent(lastSevenDaysButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)

		 //Validate Custom Date Range - Last 14 Days
		 log.logInfo("Validate Last 3 Days Button")
		 TestObject lastFourteenDaysButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last14Days')
		 WebUI.verifyElementPresent(lastFourteenDaysButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 
		 //Validate Custom Date Range - Last 30 Days
		 log.logInfo("Validate Last 30 Days Button")
		 TestObject lastThirtyDaysButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Last30Days')
		 WebUI.verifyElementPresent(lastThirtyDaysButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 
		 //Validate Custom Date Range - Yesterday
		 log.logInfo("Validate Yesterday Button")
		 TestObject yesterdayButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Yesterday')
		 WebUI.verifyElementPresent(yesterdayButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 
		 //Validate Custom Date Range - Today
		 log.logInfo("Validate Today Button")
		 TestObject todayButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_Today')
		 WebUI.verifyElementPresent(todayButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 
		 //Validate Custom Date Range - This Month
		 log.logInfo("Validate This Month Button")
		 TestObject thisMonthButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_ThisMonth')
		 WebUI.verifyElementPresent(thisMonthButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 
		 //Validate Custom Date Range - Last Month
		 log.logInfo("Validate Last Month Button")
		 TestObject lastMonthButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_LastMonth')
		 WebUI.verifyElementPresent(lastMonthButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
		 
		 //Validate Custom Date Range - Custom Range
		 log.logInfo("Validate Custom Range Button")
		 TestObject customRangeButton = findTestObject('Object Repository/Filters/Main Filter Window/Time Period/DateSelector/button_DateSelector_CustomRange')
		 WebUI.verifyElementPresent(customRangeButton, objectTimeout,FailureHandling.CONTINUE_ON_FAILURE)
	
	 }


}
