package framework;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import TestInitialization.TestInitializationScript;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeWebDriver {
    {
        System.setProperty("atu.reporter.config", "Resources/Dell.properties");
    }

    public WebDriver driver;
    protected SeDriverFactory browser;
    protected String webUrl;
    private long defTimeOut = 600;
    public String syncTime;
    public static String stepDesc;
    public String busy = "//html[not(@class='siebui-busy')]";

    public SeWebDriver(WebDriver driver) {
        this.driver = driver;
        this.webUrl = driver.getCurrentUrl();
    }

    public SeWebDriver() {
        this("firefox");
    }

    public SeWebDriver(String browserType) {
        browser = new SeDriverFactory();
        driver = browser.getDriver(browserType);

    }

    public boolean clickByJSexecutor(SeWebElement element) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            jsExecutor.executeScript("arguments[0].focus;", element.getWebElement());
            jsExecutor.executeScript("arguments[0].click();", element.getWebElement());

        } catch (Exception err) {
            System.out.println(err.getMessage());

        }
        return false;
    }


    public SeWebDriver(String webUrl, String browserType, String syncTime) {

        this(browserType);
        this.launch(webUrl);
        this.syncTime = syncTime;
    }

    public String getURL() {
        return this.webUrl;
    }


    public SeWebElement findElement(String findByMechanism, String locator) {
        SeWebElement retElement = null;
        String display = null;
        String label = null;


        try {
            retElement = new SeWebElement(driver.findElement(FindBy.seByMechanism(findByMechanism, locator)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", retElement.getWebElement());
            String colour[] = {"red", "yellow", "blue", "green"};
            for (String a : colour) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1.5px solid " + a + "'", retElement.getWebElement());
            }
            return retElement;
        } catch (Exception err) {
            System.out.println(err.getMessage());


        }
        return null;
    }


    public List<SeWebElement> findElements(String findByMechanism, String locator) {

        List<WebElement> webElements = null;
        webElements = driver.findElements(FindBy.seByMechanism(findByMechanism,
                locator));

        return toSeWebElements(webElements);

    }


    public List<SeWebElement> toSeWebElements(List<WebElement> webElements) {
        List<SeWebElement> seWebElements = new ArrayList<>();
        for (WebElement item : webElements) {
            seWebElements.add(new SeWebElement(item));
        }
        return seWebElements;
    }


    public WebDriver.Options manage() {
        return driver.manage();
    }

    public void launch(String webURL) {
        this.webUrl = webURL;
        driver.get(webURL);
        if (!TestInitializationScript.gBrowserType.equalsIgnoreCase("chrome"))
            driver.manage().window().maximize();
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public String getTitle() {
        return driver.getTitle();
    }


    public void back() {
        getWebDriver().navigate().back();

    }

    public void forward() {
        getWebDriver().navigate().forward();
    }

    public void refresh() {
        getWebDriver().navigate().refresh();

    }

    public void to(String url) {
        getWebDriver().navigate().to(url);
    }

    public void to(URL url) {
        getWebDriver().navigate().to(url);
    }

    // wrapping-up WebDriver.Options


    public Alert alert() {
        try {
            return getWebDriver().switchTo().alert();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present");
        }
        throw new NullPointerException("No alert present");
    }


    public void click(SeWebElement element) {
        element.click();
    }


    public void waitForPageLoads() throws InterruptedException {

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State       : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
        Thread.sleep(2000);
    }

    public void waitForPageToLoad() throws Exception {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript(
                        "return document.readyState").equals("complete");
            }
        };
        Wait<WebDriver> wait = new WebDriverWait(driver, defTimeOut);
        try {
            wait.until(expectation);
        } catch (Exception error) {
            throw new Exception("Failed while loading page", error);
        }
    }

    public void waitForElementVisible(String findBy, String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(syncTime));
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy.seByMechanism(findBy, locator)));
        } catch (TimeoutException e) {


        }
    }

    public void notElementVisible(String findBy, String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(syncTime));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy
                .seByMechanism(findBy, locator)));
    }


    public void selectByValue(SeWebElement element, String valueToSelect) {
        new Select(getWebElement(element)).selectByValue(valueToSelect);
    }

    public void selectByIndex(SeWebElement element, int index) {
        new Select(getWebElement(element)).selectByIndex(index);
    }

    public void selectByVisibleText(SeWebElement element, String visibleText) {
        new Select(getWebElement(element)).selectByVisibleText(visibleText);
    }

    public String getSelectedValueFromDropdown(SeWebElement element) {
        return new Select(getWebElement(element)).getFirstSelectedOption().getText();

    }

    public WebElement getWebElement(SeWebElement webElement) {
        return webElement.getWebElement();
    }

}
