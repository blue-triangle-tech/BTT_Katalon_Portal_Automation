package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import btt_portal.*



public class Common_Functions {

	//***************** Object Definitions - Multi-Use ********************
	KeywordLogger log = new KeywordLogger()

	//***************************************************************************
	// Function Name: open_filters_window
	//
	// Function Overview: Check to see if the indicated object exists, if not set an error flag
	//
	// Function Input Variable(s):
	//							element_name - text description of element being validated
	//							test_object  - reference to element object (set prior to function call)
	//							object_timeout - defined desired object wait timeout
	//							failure_handling - Outlines behavior if error is found (either FailureHandling.STOP_ON_FAILURE or FailureHandling.CONTINUE_ON_FAILURE
	//							error_found      - Flag for if an error is found [calling section should initialize this to zero at start]
	//
	// Function Output Variable(s): None
	//
	// Function Return Value: None
	//***************************************************************************

	public boolean validate_object_exists (element_name, test_object_path, object_timeout, failure_handling, error_found) {
		//log.logInfo("In Validation Fxn")
		//log.logInfo("Current Values: element_name - " + element_name + " test_object_path - " + test_object_path + " object_timeout - " + object_timeout + "failure_handling - " + failure_handling + " error_found - " + error_found)

		//Informational message
		log.logInfo("Validate " + element_name)

		//Define Test Object
		TestObject this_object = findTestObject(test_object_path)

		//Check to see if the indicated object exists, if not set an error flag
		if ((WebUI.verifyElementPresent(this_object, object_timeout, FailureHandling.CONTINUE_ON_FAILURE)) == false) {
			log.logInfo("Error Logic - " + element_name)
			log.logError("Unable to detect " + element_name)

			return true

		} else {
			//Do not overwrite a previously set true condition
			if (error_found) {
				return true
			} else {
				return false
			}
		}
	}
}
