package framework;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class SeWebElement {
    public WebElement element;
    public SeWebDriver driver;

    public SeWebElement(WebElement element) {
        this.element = element;
    }

    public String getSelecedOption() {
        Select select = new Select(element);
        WebElement tmp = select.getFirstSelectedOption();
        return tmp.getText();
    }


    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    public String getText() {
        return element.getText();
    }

    public String getAttribute(String attribute) {
        return element.getAttribute(attribute);
    }


    public void sendKeys(CharSequence... key) {
        element.sendKeys(key);
    }


    @Deprecated
    public void type(CharSequence... key) {
        clear();
        sendKeys(key);
    }


    public String setValue(CharSequence... key) {
        try {

            element.clear();
            element.sendKeys(key);

        } catch (Exception err) {
            System.out.println(err.getMessage());

        }
        return null;
    }


    public void select(String label) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(label);
        } catch (Exception err) {
            System.out.println(err.getMessage());

        }
    }

    public String Strsetvalue(CharSequence... key) {
        try {


            element.clear();
            element.sendKeys(key);
        } catch (Exception err) {
            System.out.println(err.getMessage());

        }
        return null;
    }

    public void select(int index) {
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
        } catch (Exception err) {
            System.out.println(err.getMessage());

        }
    }


    public void click() {
        try {

            element.click();
        } catch (Exception err) {

            System.out.println(err.getMessage());

        }
    }

    public void clear() {
        element.clear();
    }


    public WebElement getWebElement() {
        return element;
    }

}
