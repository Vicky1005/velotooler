package com.velotooler.core.driver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static WebDriver init(BrowserType browserType) {
        switch (browserType) {
            case FIREFOX:
                return new FireFoxDriverProvider().createWebdriver();
            default:
                return new ChromeDriverProvider().createWebdriver();
        }
    }
}
