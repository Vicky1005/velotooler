package com.velotooler.core.driver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static WebDriver init(DriverType browserType) {
        switch (browserType) {
            case FIREFOX:
                return new FireFoxDriverProvider().createWebDriver();
            case CHROME_GRID:
                return new ChromeRemoteGridProvider().createWebDriver();
            case FIREFOX_GRID:
                return new FireFoxRemoteGridProvider().createWebDriver();
            default:
                return new ChromeDriverProvider().createWebDriver();
        }
    }
}
