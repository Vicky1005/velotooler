package com.velotooler.core.driver;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class ChromeRemoteGridProvider implements WebDriverProvider {
    @SneakyThrows
    public WebDriver createWebDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        return new RemoteWebDriver(new URL(" http://192.168.100.23:4444/wd/hub"), chromeOptions);
    }
}
