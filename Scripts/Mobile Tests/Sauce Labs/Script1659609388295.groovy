import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import com.saucelabs.saucebindings.junit5.SauceBaseTest;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.jupiter.api.Test;

ChromeOptions browserOptions = new ChromeOptions();
browserOptions.setPlatformName("Windows 10");
browserOptions.setBrowserVersion("latest");
Map<String, Object> sauceOptions = new HashMap<>();
sauceOptions.put("build", "<your build id>");
sauceOptions.put("name", "<your test name>");
browserOptions.setCapability("sauce:options", sauceOptions);

URL url = new URL("https://giavannaBT:*****b0fc@ondemand.us-west-1.saucelabs.com:443/wd/hub");
RemoteWebDriver driver = new RemoteWebDriver(url, browserOptions);