package framework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class SeDriverFactory {

    public WebDriver getDriver() {
        return this.getDriver("firefox");
    }

    public WebDriver getNewRemoteDriver(String hubUrl, Capabilities capabilities) {
        RemoteWebDriver driver;
        try {
            driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Hub URL does not seem to be working: " + hubUrl, ex);
        }
        return driver;
    }
    //@SuppressWarnings("static-access")
    public WebDriver getDriver(String browserType) {
        WebDriver driver = null;
        File file = null;

        if (browserType.equalsIgnoreCase("firefox") || browserType.equalsIgnoreCase("ff")) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } else if (browserType.equalsIgnoreCase("chrome")) {
            file = new File(System.getProperty("user.dir") + "/Resources/chromedriver");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            DesiredCapabilities Capability = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            options.addArguments("--start-maximized");
            Capability.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new ChromeDriver(Capability);
            driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        } else if (browserType.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }
        return driver;
    }


}
