package framework;

import org.openqa.selenium.remote.DesiredCapabilities;


public class SeCapabilities {

    public DesiredCapabilities caps = new DesiredCapabilities();

    public void setBrowserCapabilities(String browser) {

        if (browser.equalsIgnoreCase("Internet Explorer"))
            caps = DesiredCapabilities.internetExplorer();

        else if (browser.equalsIgnoreCase("Firefox"))
            caps = DesiredCapabilities.firefox();

        else if (browser.equalsIgnoreCase("Chrome"))
            caps = DesiredCapabilities.chrome();

        else if (browser.equalsIgnoreCase("Android"))
            caps = DesiredCapabilities.android();

            // Default condition
        else
            caps = DesiredCapabilities.firefox();

    }
}
