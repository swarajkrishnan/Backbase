package TestInitialization;


import framework.SeWebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Properties;


public class TestInitializationScript {

    public static String userdirectory = System.getProperty("user.dir");
    public static String gEnvUrl;
    public static String gBrowserType;
    public static SeWebDriver driver;
    public static String gSyncTime;


    @BeforeTest
    public void lauchBrowser() throws Exception {
        try {
            autoTestManagerInitialization();
            driver = new SeWebDriver(gEnvUrl, gBrowserType, gSyncTime);
            driver.waitForPageLoads();
            if (checkIfPageLoaded()) {
                throw new SkipException("Due to Environment down");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean checkIfPageLoaded() {
        boolean isPageNotLoaded = driver.getTitle().startsWith("Message:") || driver.getTitle().contains("This page");
        if (isPageNotLoaded) {
            System.out.println("Environment is Down, Please try again later");
            return true;
        } else {
            return false;
        }

    }


    @AfterMethod(alwaysRun = true)
    public void afterClass() throws Exception {

        driver.to(gEnvUrl);
        driver.waitForPageLoads();
    }

    @AfterTest
    public void closingBrowser() throws Exception {
        driver.quit();

    }

    public void autoTestManagerInitialization() {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            prop.load(this.getClass().getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gEnvUrl = prop.getProperty("ui_endpoint");
        gBrowserType = prop.getProperty("browserType");
        gSyncTime = "20";
    }

}