package com.velotooler.bdd;

import com.velotooler.core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CucumberHook {

    private WebDriver driver;

    @Before
    public void setupDriver() {
        DriverFactory.init();
        driver = DriverFactory.get();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://pilot.velotooler.com/");
    }

    @After
    public void quitDriver() {
        driver.close();
        driver = null;
    }
}
