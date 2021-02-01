package com.velotooler.steps;

import com.velotooler.core.model.Auth;
import com.velotooler.core.service.UserCreator;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.HomePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class LogIn {

    private final WebDriver driver;
    private HomePage mainPage;

    public LogIn(WebDriver driver) {
        this.driver = driver;
        this.mainPage = new HomePage(driver);
    }

    public DashboardPage logIn() {
        Auth auth = UserCreator.withCredentialsFromProperty();
        mainPage.goToLoginPage().logIn(auth.getUsername(), auth.getPassword());
        log.info("Login is successful with user " + auth.getUsername());
        return new DashboardPage(driver);
    }
}
