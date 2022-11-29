package btt_portal

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class Digital_Experience_Overview {

	//opens each score card and verifies that it contains data in the form of a table
	//counts table rows and validates that data is present by ensuring more rows are present than headers
	public static verify_score_cards() {


		for (int x = 0; x<=4; x++) {
			TestObject score_card = new TestObject("score cards")
			score_card.addProperty("xpath", ConditionType.EQUALS, '//*[@id="chartID_'+x+'"]')

			TestObject show_detail = new TestObject("show detail")
			show_detail.addProperty("xpath", ConditionType.EQUALS, '//*[@id="chartID_'+x+'-drilldown-toggle"]')

			TestObject score_drilldown_table = new TestObject("score drilldown table")
			score_drilldown_table.addProperty("xpath", ConditionType.EQUALS, '//*[@id="score-drilldown-table"]')

			WebUI.verifyElementPresent(score_card, 10)
			WebUI.click(show_detail)

			WebUI.waitForElementNotPresent(findTestObject('Object Repository/Common Portal Elements/div_loading-wrapper'), 15)

			WebDriver driver = DriverFactory.getWebDriver()
			WebElement Table = driver.findElement(By.xpath('//*[@id="score-drilldown-table"]'))

			List<WebElement> rows_table = Table.findElements(By.tagName('tr'))

			int rows_count = rows_table.size()
			if (rows_count <= 2) {

				KeywordUtil.markWarning('Score card ' +x+ ' has no table data')
			}
		}
	}
}
