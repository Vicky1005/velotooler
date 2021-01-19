package com.velotooler.core.driver;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class FireFoxRemoteGridProvider implements WebDriverProvider {
    @SneakyThrows
    public WebDriver createWebDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        return new RemoteWebDriver(new URL(" http://192.168.100.23:4444/wd/hub"), firefoxOptions);
    }
}
