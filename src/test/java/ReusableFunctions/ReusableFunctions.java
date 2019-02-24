package ReusableFunctions;


import PageObjects.EditComputer;
import PageObjects.HomePage;
import framework.SeWebDriver;
import framework.SeWebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ReusableFunctions {
    public SeWebDriver driver;
    public String syncTime = "//html";
    public String args = "//table[@class='computers zebra-striped']";

    public ReusableFunctions(SeWebDriver driver) {
        this.driver = driver;
    }

    public void setTextInTextbox(String labelName, String value) {

        try {
            driver.findElement("xpath", "//label[text()='" + labelName + "']/following-sibling::div/input").clear();
            driver.findElement("xpath", "//label[text()='" + labelName + "']/following-sibling::div/input").sendKeys(value);
        } catch (Exception e) {
            Assert.fail("Element not found with label : " + labelName);
        }
    }

    public void selectFromDropdown(String labelName, String value) {

        try {

            driver.selectByVisibleText(driver.findElement("xpath", "//label[text()='" + labelName + "']/following-sibling::div/select[@id='company']"), value);
        } catch (Exception e) {
            Assert.fail("Element not found with label : " + labelName);
        }
    }

    public void clickButton(String buttonName) {

        try {
            if (driver.findElements("xpath", "//input[@value='" + buttonName + "']").size() > 0) {
                driver.click(driver.findElement("xpath", "//input[@value='" + buttonName + "']"));
                Thread.sleep(1000);
            }
            else {
                driver.click(driver.findElement("xpath", "//a[text()='" + buttonName + "']"));
                Thread.sleep(1500);
            }
        } catch (Exception e) {
            Assert.fail("Element not found with label : " + buttonName);
        }
    }

    public String getTextIntable(String coloumnName, int row) throws InterruptedException {
        try {
            driver.waitForElementVisible("xpath", syncTime);
            int coloumHeader = getRowNo(coloumnName);
            driver.waitForElementVisible("xpath", args + "//tr[" + (row) + "]//td[" + coloumHeader + "]");
            if (driver.findElements("xpath", args + "//tr[" + (row) + "]//td[" + coloumHeader + "]/a").size() > 0) {
                return driver.findElement("xpath", args + "//tr[" + (row) + "]//td[" + coloumHeader + "]/a").getText();
            } else {
                return driver.findElement("xpath", args + "//tr[" + (row) + "]//td[" + coloumHeader + "]").getText();
            }
        } catch (Exception e) {
            Assert.fail("Data not fetched from table");
            return null;
        }
    }

    public void clickLinkIntable(String coloumnName, int row) throws InterruptedException {
        try {
            driver.waitForElementVisible("xpath", syncTime);
            int coloumHeader = getRowNo(coloumnName);
            driver.waitForElementVisible("xpath", args + "//tr[" + (row) + "]//td[" + coloumHeader + "]");
            if (driver.findElements("xpath", args + "//tr[" + (row) + "]//td[" + coloumHeader + "]/a").size() > 0) {
                driver.click(driver.findElement("xpath", args + "//tr[" + (row) + "]//td[" + coloumHeader + "]/a"));
            }
        } catch (Exception e) {
            Assert.fail("Data not fetched from table");
        }
    }

    public int getRowNo(String coloumnName) throws InterruptedException {
        String xpath = args + "/thead/tr/th";
        List<SeWebElement> list = driver.findElements("xpath", xpath);
        int row = 0;
        boolean flag = false;
        for (SeWebElement s : list) {
            if (s.getText().equalsIgnoreCase(coloumnName) || s.getAttribute("id").equalsIgnoreCase(coloumnName)) {
                row++;
                flag = true;
                break;
            }
            row++;
        }
        if (flag) {
            return row;
        } else
            return 0;
    }

    public boolean validateSuccessMessage(String computerName) {
        try {
            driver.waitForElementVisible("xpath", "//div[@class='alert-message warning']");
            String message = driver.findElement("xpath", "//div[@class='alert-message warning']").getText();
            if (message.contains("Computer " + computerName + " has been created"))
                return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateDeleteMessage() {
        try {
            String message = driver.findElement("xpath", "//div[@class='alert-message warning']").getText();
            if (message.contains("Computer has been deleted"))
                return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void fillComputerDetails(String computerName, String introducedDate, String discontinuedDate, String companyName) {

        setTextInTextbox("Computer name", computerName);
        setTextInTextbox("Introduced date", introducedDate);
        setTextInTextbox("Discontinued date", discontinuedDate);
        if (!companyName.isEmpty())
            selectFromDropdown("Company", companyName);
    }

    public void searchComputerName(String computerName) {

        driver.findElement("xpath", HomePage.textboxSearch).sendKeys(computerName);
        driver.click(driver.findElement("xpath", HomePage.buttonFilterByName));

        try {
            Thread.sleep(2500);
            driver.waitForPageLoads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void deleteComputerName(String computerName) {
        try {
            clickLinkIntable("Computer name", 1);
            driver.waitForElementVisible("xpath", EditComputer.headerText);
            clickButton("Delete this computer");
            validateDeleteMessage();
        } catch (Exception e) {
            Assert.fail("Unable to delete computer");
        }
    }

    public void updateComputerName(String computerName) {
        try {
            clickLinkIntable("Computer name", 1);
            driver.waitForElementVisible("xpath", EditComputer.headerText);
        } catch (Exception e) {
            Assert.fail("Unable to goto Edit computer Page");
        }
    }

    public boolean validateCreatedData(HashMap<String, String> hashMap) {
        try {
            SoftAssert softAssert = new SoftAssert();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
            softAssert.assertTrue(getTextIntable("Computer name", 1).equalsIgnoreCase(hashMap.get("computerName")), "Computer name should match");
            String introDate = getTextIntable("Introduced", 1);
            LocalDate introducedDate = LocalDate.parse(introDate, formatter);
            softAssert.assertTrue(introducedDate.toString().equalsIgnoreCase(hashMap.get("introducedDate")),"IntroducedDate should match");
            String discDate = getTextIntable("Discontinued", 1);
            LocalDate discontinuedDate = LocalDate.parse(discDate, formatter);
            softAssert.assertTrue(discontinuedDate.toString().equalsIgnoreCase(hashMap.get("discontinuedDate")),"DiscontinuedDate should match");
            softAssert.assertTrue(getTextIntable("Company", 1).equalsIgnoreCase(hashMap.get("companyName")), "company name should match");
            softAssert.assertAll();
            return true;
        } catch (Exception e) {
            Assert.fail("Data Validation Failed");
            return false;
        }
    }
}
