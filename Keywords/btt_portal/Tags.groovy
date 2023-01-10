package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class Tags {
	
	//***************************************************************************
	 // Function Name: edit_site
	 //
	 // Function Overview: Updates a site field specified in the input variables with the specified text
	 //		Navigates to the url to update a site (plugs in the site id from the input variables)
	 //		Sets text to update the specified field
	 //		Saves the update
	 //
	 // Function Input Variable(s): 
	 //		site_id (String, required) - The id of the site you are using
	 //		field (TestObject, required) - The field you want to update
	 //		field_text (String, required) - The text of the field you want to update
	 //
	 // Function Output Variable(s): None
	 //
	 // Function Return Value: None
	 //***************************************************************************

	public static edit_site(String site_id, TestObject field, String field_text) {
		WebUI.navigateToUrl('https://portal.bluetriangle.com/btportal/web/index.php?r=sites/update&id=' + site_id)
		WebUI.setText(field, field_text)
		if (WebUI.verifyElementNotPresent(findTestObject('Object Repository/Tag Testing/Settings_Sites/button_OK'), 5)) {
			WebUI.click(findTestObject('Object Repository/Tag Testing/Settings_Sites/Create Site/button_Save'))
		}
		else {
			WebUI.click(findTestObject('Object Repository/Tag Testing/Settings_Sites/button_OK'))
			WebUI.click(findTestObject('Object Repository/Tag Testing/Settings_Sites/Create Site/button_Save'))
		}
	}
	
	//***************************************************************************
	 // Function Name: edit_page_name
	 //
	 // Function Overview: Updates a page name field specified in the input variables with the specified text
	 //		Navigates to the url to update a site (plugs in the site id and page id from the input variables)
	 //		Sets text to update the specified field
	 //		Saves the update
	 //
	 // Function Input Variable(s):
	 //		page_id (String, required) - The id of the page you want to update
	 //		site_id (String, required) - The id of the site you are using
	 //		field (TestObject, required) - The field you want to update
	 //		field_text (String, required) - The text of the field you want to update
	 //
	 // Function Output Variable(s): None
	 //
	 // Function Return Value: None
	 //***************************************************************************

	public static edit_page_name(String page_id, String site_id, TestObject field, String field_text) {

		WebUI.navigateToUrl('https://portal.bluetriangle.com/btportal/web/index.php?r=page-name/update&id=' + page_id +'&sid=' + site_id)
		WebUI.setText(field, field_text)
		WebUI.click(findTestObject('Object Repository/Tag Testing/Settings_Page Naming/button_Save'))
	}
	
	//***************************************************************************
	 // Function Name: edit_spa_page_name
	 //
	 // Function Overview: Updates an spa page name field specified in the input variables with the specified text
	 //		Navigates to the url to update a site (plugs in the site id and spa page id from the input variables)
	 //		Sets text to update the specified field
	 //		Saves the update
	 //
	 // Function Input Variable(s):
	 //		spa_page_id (String, required) - The id of the spa page you want to update
	 //		site_id (String, required) - The id of the site you are using
	 //		field (TestObject, required) - The field you want to update
	 //		field_text (String, required) - The text of the field you want to update
	 //
	 // Function Output Variable(s): None
	 //
	 // Function Return Value: None
	 //***************************************************************************

	public static edit_spa_page_name(String spa_page_id, String site_id, TestObject field, String field_text) {
		WebUI.navigateToUrl('https://portal.bluetriangle.com/btportal/web/index.php?r=vt-page-name/update&sid='+ site_id +'&id=' + spa_page_id)
		WebUI.setText(field, field_text)
		WebUI.click(findTestObject('Object Repository/Tag Testing/Settings_SPA Page Naming/Create SPA Page Name/button_Save'))
	}
	
	//***************************************************************************
	 // Function Name: edit_mutation_observer
	 //
	 // Function Overview: Updates a mutation observer field specified in the input variables with the specified text
	 //		Navigates to the url to update a site (plugs in the site id and page id from the input variables)
	 //		Sets text to update the specified field
	 //		Saves the update
	 //
	 // Function Input Variable(s):
	 //		site_id (String, required) - The id of the site you are using
	 //		field (TestObject, required) - The field you want to update
	 //		field_text (String, required) - The text of the field you want to update
	 //
	 // Function Output Variable(s): None
	 //
	 // Function Return Value: None
	 //***************************************************************************

	public static edit_mutation_observer(String site_id, TestObject field, String field_text) {

		WebUI.navigateToUrl('https://portal.bluetriangle.com/btportal/web/index.php?r=mutation-observer/index&sid=' + site_id + '#')
		WebUI.click(findTestObject('Object Repository/Tag Testing/Settings_Mutation Observer/button_EditPageName'))
		WebUI.setText(field, field_text)
		WebUI.click(findTestObject('Object Repository/Tag Testing/Settings_Mutation Observer/button_Save'))
	}
	
	//***************************************************************************
	 // Function Name: check_tag
	 //
	 // Function Overview: Opens the BTT Tag url for the specified site and looks for the updated text for up to 3 minutes
	 //		Navigates to the btt tag url by plugging in the site from the input variables
	 //		Checks while the time is less than 180 seconds
	 //		Looks for the text specified in the input variable
	 //		Delays for 10 seconds, refreshes, and adds 10 to x until the text is found
	 //
	 // Function Input Variable(s):
	 //		site (String, required) - The id of the page you want to update
	 //		field_text (String, required) - The id of the site you are using
	 //
	 // Function Output Variable(s): None
	 //
	 // Function Return Value: None
	 //***************************************************************************
	public static check_tag(String site, String field_text) {

		WebDriver driver = DriverFactory.getWebDriver()
		JavascriptExecutor js = ((driver) as JavascriptExecutor)
		js.executeScript("window.open();")

		WebUI.switchToWindowIndex(1)
		WebUI.navigateToUrl('https://' + site + '.btttag.com/btt.js')


		int x = 0
		while (x<=180) {

			String tag_text = WebUI.getText(findTestObject('Object Repository/Tag Testing/Settings_Sites/obj_TabPage'))

			if (tag_text.contains(field_text)) {
				KeywordUtil.logInfo('The tag text was found in approximately ' + x + ' seconds')
				WebUI.closeBrowser()
				return x
			}
			else {
				WebUI.delay(10)
				WebUI.refresh()
				x = x + 10
				if (x>=190) {
					KeywordUtil.markFailed('The tag text has not been found after 3 minutes')
					WebUI.closeBrowser()
				}
			}
		}
	}
}
