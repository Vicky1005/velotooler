package com.velotooler;

import com.velotooler.core.driver.BrowserType;
import com.velotooler.core.driver.DriverFactory;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.core.parser.Parser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.steps.LogIn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected WebDriver driver;
    protected DashboardPage dashboardPage;
    protected Parser parser = new JsonParser();

    @BeforeEach
    public void logIn() {
        driver = DriverFactory.init(BrowserType.CHROME);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://pilot.velotooler.com/");
        dashboardPage = new LogIn(driver).logIn();
    }

    @AfterEach
    public void LogOut() {
        dashboardPage.logOut();
        driver.close();
        driver.quit();
    }

}
