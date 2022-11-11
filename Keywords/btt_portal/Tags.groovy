package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class Tags {

	public static edit_site(String site_id, TestObject field, String field_text) {
		WebUI.navigateToUrl('https://portal.bluetriangle.com/btportal/web/index.php?r=sites/update&id=' + site_id)
		WebUI.setText(field, field_text)
		if (WebUI.verifyElementNotPresent(findTestObject('Object Repository/Tag Testing/Sites/OK Button'), 5)) {
			WebUI.click(findTestObject('Object Repository/Tag Testing/Sites/Save'))
		}
		else {
			WebUI.click(findTestObject('Object Repository/Tag Testing/Sites/OK Button'))
			WebUI.click(findTestObject('Object Repository/Tag Testing/Sites/Save'))
		}
	}

	public static edit_page_name(String page_id, String site_id, TestObject field, String field_text) {

		WebUI.navigateToUrl('https://portal.bluetriangle.com/btportal/web/index.php?r=page-name/update&id=' + page_id +'&sid=' + site_id)
		WebUI.setText(field, field_text)
		WebUI.click(findTestObject('Object Repository/Tag Testing/Page Naming/Save'))
	}

	public static edit_spa_page_name(String spa_page_id, String site_id, TestObject field, String field_text) {
		WebUI.navigateToUrl('https://portal.bluetriangle.com/btportal/web/index.php?r=vt-page-name/update&sid='+ site_id +'&id=' + spa_page_id)
		WebUI.setText(field, field_text)
		WebUI.click(findTestObject('Object Repository/Tag Testing/SPA Page Naming/Save'))
	}

	public static edit_mutation_observer(String site_id, TestObject field, String field_text) {

		WebUI.navigateToUrl('https://portal.bluetriangle.com/btportal/web/index.php?r=mutation-observer/index&sid=' + site_id + '#')
		WebUI.click(findTestObject('Object Repository/Tag Testing/Mutation Observer/Edit Page Name'))
		WebUI.setText(field, field_text)
		WebUI.click(findTestObject('Object Repository/Tag Testing/Mutation Observer/Save'))
	}
	public static check_tag(String site, String field_text) {

		WebDriver driver = DriverFactory.getWebDriver()
		JavascriptExecutor js = ((driver) as JavascriptExecutor)
		js.executeScript("window.open();")

		WebUI.switchToWindowIndex(1)
		WebUI.navigateToUrl('https://' + site + '.btttag.com/btt.js')


		int x = 0
		while (x<=180) {

			String tag_text = WebUI.getText(findTestObject('Object Repository/Tag Testing/Sites/Tag Page'))

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
