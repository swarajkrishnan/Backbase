package TestCases;

import PageObjects.AddNewComputer;
import PageObjects.HomePage;
import TestInitialization.TestInitializationScript;
import org.testng.Assert;
import org.testng.annotations.Test;
import ReusableFunctions.ReusableFunctions;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;


public class VerifyComputerTestCases extends TestInitializationScript {

    {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Resources/geckodriver");
    }

    {
        System.setProperty("atu.reporter.config", System.getProperty("user.dir") + "/Resources/atu.properties");
    }


    @Test(groups = {"regression"}, priority = 0)
    public void VerifyHomePageValidations() throws Exception {

        ReusableFunctions reusableFunctions = new ReusableFunctions(driver);
        SoftAssert softAssert = new SoftAssert();
        driver.waitForElementVisible("xpath", HomePage.headerLink);
        softAssert.assertTrue(driver.findElement("xpath", HomePage.buttonAddNewComputer).isDisplayed(), "Add a new computer button is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.buttonFilterByName).isDisplayed(), "Filter by name button is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.textboxSearch).isDisplayed(), "Search box for computer name is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.tableHeaderComputerName).isDisplayed(), "Table header Computer name is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.tableHeaderIntroduced).isDisplayed(), "Table header Introduced is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.tableHeaderDiscontinued).isDisplayed(), "Table header Discontinued is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.tableHeaderCompany).isDisplayed(), "Table header Company is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.buttonNext).isDisplayed(), "Next button is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.buttonPrevious).isDisplayed(), "Previous button is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.displayText).isDisplayed(), "Display Text is displayed");
        softAssert.assertTrue(driver.findElement("xpath", HomePage.headerText).isDisplayed(), "Header Text is displayed");
        softAssert.assertAll();

    }

    @Test(groups = {"regression"}, enabled = true, priority = 1)
    public void VerifyAddNewComputerPageValidations() throws Exception {

        ReusableFunctions reusableFunctions = new ReusableFunctions(driver);
        SoftAssert softAssert = new SoftAssert();
        driver.waitForElementVisible("xpath", HomePage.headerLink);
        reusableFunctions.clickButton("Add a new computer");
        driver.waitForElementVisible("xpath", AddNewComputer.headerText);
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.headerText).isDisplayed(), "Add a computer header is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.labelComputerName).isDisplayed(), "Computer name label is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.labelIntroducedDate).isDisplayed(), "Introduced Date label is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.labelDiscontinuedDate).isDisplayed(), "Discontinued Date label is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.labelCompany).isDisplayed(), "Company label is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.textboxComputerName).isDisplayed(), "Computer name textbox is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.textboxIntroducedDate).isDisplayed(), "Introduced date textbox is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.textboxDiscontinuedDate).isDisplayed(), "Discontinued Date textbox is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.dropdwnCompany).isDisplayed(), "Company dropdown is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.btnCreateComputer).isDisplayed(), "Button create this computer is displayed");
        softAssert.assertTrue(driver.findElement("xpath", AddNewComputer.btnCancel).isDisplayed(), "Button cancel is displayed");
        softAssert.assertAll();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "addNewComputer", groups = {"regression"}, enabled = true, priority = 2)
    public void VerifyAddDelComputer(HashMap<String, String> hashMap) throws Exception {

        ReusableFunctions reusableFunctions = new ReusableFunctions(driver);
        SoftAssert softAssert = new SoftAssert();
        driver.waitForElementVisible("xpath", HomePage.headerLink);
        reusableFunctions.clickButton("Add a new computer");
        driver.waitForElementVisible("xpath", AddNewComputer.headerText);
        reusableFunctions.fillComputerDetails(hashMap.get("computerName"), hashMap.get("introducedDate"), hashMap.get("discontinuedDate"), hashMap.get("companyName"));
        reusableFunctions.clickButton("Create this computer");
        softAssert.assertTrue(reusableFunctions.validateSuccessMessage(hashMap.get("computerName")), "New computer has been created");
        reusableFunctions.searchComputerName(hashMap.get("computerName"));
        softAssert.assertTrue(reusableFunctions.validateCreatedData(hashMap), "All details are correct");
        reusableFunctions.deleteComputerName(hashMap.get("computerName"));
        softAssert.assertAll();
    }


    @Test(groups = {"regression"}, enabled = true, priority = 3)
    public void VerifyAddNewComputerWithoutName() throws Exception {

        ReusableFunctions reusableFunctions = new ReusableFunctions(driver);
        SoftAssert softAssert = new SoftAssert();
        driver.waitForElementVisible("xpath", HomePage.headerLink);
        reusableFunctions.clickButton("Add a new computer");
        driver.waitForElementVisible("xpath", AddNewComputer.headerText);
        reusableFunctions.fillComputerDetails("", "2008-04-25", "2012-04-22", "IBM");
        reusableFunctions.clickButton("Create this computer");
        Assert.assertTrue(driver.findElement("xpath", AddNewComputer.msgErrorComputerName).isDisplayed(), "User should remain in the same page and wrong field or mandatory field should be highlited in red");
        softAssert.assertAll();
    }


    @Test(groups = {"regression"}, enabled = true, priority = 4)
    public void VerifyAddNewComputerWithoutIntroduceddate() throws Exception {


        ReusableFunctions reusableFunctions = new ReusableFunctions(driver);
        SoftAssert softAssert = new SoftAssert();
        driver.waitForElementVisible("xpath", HomePage.headerLink);
        reusableFunctions.clickButton("Add a new computer");
        driver.waitForElementVisible("xpath", AddNewComputer.headerText);
        reusableFunctions.fillComputerDetails("ComputerWithoutIntroducedDate", "", "2012-04-22", "IBM");
        reusableFunctions.clickButton("Create this computer");
        softAssert.assertTrue(reusableFunctions.validateSuccessMessage("ComputerWithoutIntroducedDate"), "New computer has been created");
        reusableFunctions.searchComputerName("ComputerWithoutIntroducedDate");
        reusableFunctions.deleteComputerName("ComputerWithoutIntroducedDate");
        softAssert.assertAll();
    }

    @Test(groups = {"regression"}, enabled = true, priority = 5)
    public void VerifyAddNewComputerWithoutDiscontinueddate() throws Exception {

        ReusableFunctions reusableFunctions = new ReusableFunctions(driver);
        SoftAssert softAssert = new SoftAssert();
        driver.waitForElementVisible("xpath", HomePage.headerLink);
        reusableFunctions.clickButton("Add a new computer");
        driver.waitForElementVisible("xpath", AddNewComputer.headerText);
        reusableFunctions.fillComputerDetails("ComputerWithoutDiscontinuedDate", "2012-04-22", "", "IBM");
        reusableFunctions.clickButton("Create this computer");
        softAssert.assertTrue(reusableFunctions.validateSuccessMessage("ComputerWithoutDiscontinuedDate"), "New computer has been created");
        reusableFunctions.searchComputerName("ComputerWithoutDiscontinuedDate");
        reusableFunctions.deleteComputerName("ComputerWithoutDiscontinuedDate");
        softAssert.assertAll();


    }

    @Test(groups = {"regression"}, enabled = true, priority = 6)
    public void VerifyAddNewComputerWithoutCompany() throws Exception {

        ReusableFunctions reusableFunctions = new ReusableFunctions(driver);
        SoftAssert softAssert = new SoftAssert();
        driver.waitForElementVisible("xpath", HomePage.headerLink);
        reusableFunctions.clickButton("Add a new computer");
        driver.waitForElementVisible("xpath", AddNewComputer.headerText);
        reusableFunctions.fillComputerDetails("ComputerWithoutCompany", "2012-04-22", "2012-04-22", "");
        reusableFunctions.clickButton("Create this computer");
        softAssert.assertTrue(reusableFunctions.validateSuccessMessage("ComputerWithoutCompany"), "New computer has been created");
        reusableFunctions.searchComputerName("ComputerWithoutCompany");
        reusableFunctions.deleteComputerName("ComputerWithoutCompany");
        softAssert.assertAll();


    }

    @Test(groups = {"regression"}, enabled = true, priority = 7)
    public void VerifyUpdateCreatedComputerWithoutCompany() throws Exception {

        ReusableFunctions reusableFunctions = new ReusableFunctions(driver);
        SoftAssert softAssert = new SoftAssert();
        driver.waitForElementVisible("xpath", HomePage.headerLink);
        reusableFunctions.clickButton("Add a new computer");
        driver.waitForElementVisible("xpath", AddNewComputer.headerText);
        reusableFunctions.fillComputerDetails("UpdateComputer", "2008-04-25", "2012-04-22", "IBM");
        reusableFunctions.clickButton("Create this computer");
        softAssert.assertTrue(reusableFunctions.validateSuccessMessage("UpdateComputer"), "New computer has been created");
        reusableFunctions.searchComputerName("UpdateComputer");
        reusableFunctions.updateComputerName("UpdateComputer");
        reusableFunctions.fillComputerDetails("", "2008-04-25", "2012-04-22", "IBM");
        reusableFunctions.clickButton("Save this computer");
        Assert.assertTrue(driver.findElement("xpath", AddNewComputer.msgErrorComputerName).isDisplayed(), "User should remain in the same page and wrong field or mandatory field should be highlited in red");
        reusableFunctions.clickButton("Cancel");
        driver.waitForElementVisible("xpath", HomePage.headerLink);
        reusableFunctions.searchComputerName("UpdateComputer");
        reusableFunctions.deleteComputerName("UpdateComputer");
        softAssert.assertAll();

    }
}
