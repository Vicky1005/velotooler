package com.velotooler.steps;

import com.velotooler.core.parser.XMLParser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.MainPage;
import org.openqa.selenium.WebDriver;

public class LogIn {

    private final WebDriver driver;
    private MainPage mainPage;
    private XMLParser xmlParser = new XMLParser();

    public LogIn(WebDriver driver) {
        this.driver = driver;
        this.mainPage = new MainPage(driver);
    }

    public DashboardPage logIn() {
        mainPage.goToLoginPage()
                .logIn(xmlParser.parseLogin("email"), xmlParser.parseLogin("password"));
        return new DashboardPage(driver);
    }
}
