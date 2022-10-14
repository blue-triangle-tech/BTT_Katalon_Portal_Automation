import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startApplication('bs://564aebc0d563f26492d532acfae9e77025979db4', true)

GlobalVariable.Bounce++

Mobile.tap(findTestObject('Object Repository/Mobile Objects/android.widget.ImageView'), 0)

GlobalVariable.Bounce--

GlobalVariable.Exit++

GlobalVariable.Products ++

Mobile.scrollToText('Add')

Mobile.tap(findTestObject('Object Repository/Mobile Objects/android.widget.Button - Add to cart'), 0)

GlobalVariable.AddToCart ++

Mobile.tap(findTestObject('Object Repository/Mobile Objects/android.widget.RelativeLayout'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile Objects/android.widget.Button - Proceed To Checkout'), 0)

GlobalVariable.GoToCheckout ++

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText'), 'standard_user', 0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText (1)'), 'secret_sauce', 0)

Mobile.tap(findTestObject('Object Repository/Mobile Objects/android.widget.Button - Login'), 0)

GlobalVariable.Login ++

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - Rebecca Winter'), 'Testing Automation',
	0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - Mandorley 112'), '123 Address Rd',
	0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - Entrance 1'), 'Room 123', 0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - Truro'), 'Cincinnati', 0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - Cornwall'), 'Ohio', 0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - 89750'), '12345', 0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - United Kingdom'), 'United States',
	0)

Mobile.tap(findTestObject('Object Repository/Mobile Objects/android.widget.Button - To Payment'), 0)

GlobalVariable.Payment ++

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - Rebecca Winter (1)'), 'Testing Automation',
	0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - 3258 1256 7568 7891'), '1234567890123456',
	0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - 0325'), '01/25', 0)

Mobile.setText(findTestObject('Object Repository/Mobile Objects/android.widget.EditText - 123'), '345', 0)

Mobile.tap(findTestObject('Object Repository/Mobile Objects/android.widget.Button - Review Order'), 0)

GlobalVariable.ReviewOrder ++

Mobile.tap(findTestObject('Object Repository/Mobile Objects/android.widget.Button - Place Order'), 0)

GlobalVariable.PlaceOrder ++

Mobile.tap(findTestObject('Object Repository/Mobile Objects/android.widget.Button - Continue Shopping'), 0)

GlobalVariable.Exit--

GlobalVariable.OrderSuccess ++

Mobile.closeApplication()