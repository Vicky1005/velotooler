package com.velotooler.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverProvider implements WebdriverProvider {

    public WebDriver createWebdriver() {
        return new FirefoxDriver();
    }
}
