package com.velotooler.steps;

import com.velotooler.core.model.Auth;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.core.parser.Parser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class LogIn {

    private final WebDriver driver;
    private HomePage mainPage;
    private Parser parser;

    public LogIn(WebDriver driver) {
        this.driver = driver;
        this.mainPage = new HomePage(driver);
        this.parser = new JsonParser();
    }

    public DashboardPage logIn() {
        Auth auth = parser.get("logIn", Auth.class);
        mainPage.goToLoginPage().logIn(auth.getUsername(), auth.getPassword());
        return new DashboardPage(driver);
    }
}
