package com.velotooler.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class DriverFactory {

    private static WebDriver driver;

    public static void init() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            log.warn("Browser is not specified. It's necessary to set browser property");
            browser = "no_browser";
        }

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FireFoxDriverProvider().createWebDriver();
                break;
            case "firefoxRemote":
                WebDriverManager.firefoxdriver().setup();
                driver = new FireFoxRemoteGridProvider().createWebDriver();
                break;
            case "chromeRemote":
                WebDriverManager.firefoxdriver().setup();
                driver = new ChromeRemoteGridProvider().createWebDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriverProvider().createWebDriver();
        }
    }

    public static WebDriver get() {
        if (driver == null) {
            init();
        }

        return driver;
    }
}

