package com.velotooler.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverProvider implements WebDriverProvider {

    public WebDriver createWebDriver() {
        return new ChromeDriver();
    }
}
