package com.velotooler.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverProvider implements WebDriverProvider {
    public WebDriver createWebDriver() {
        return new FirefoxDriver();
    }
}
