package com.velotooler.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends MainPage {

    @FindBy(xpath = "//h1[contains(text(), 'Login to velotooler')]")
    private WebElement logInToVelotoolerHeader;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement logInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage logIn(String userEmail, String userPassword) {
        email.sendKeys(userEmail);
        password.sendKeys(userPassword);
        logInButton.click();
        return new DashboardPage(driver);
    }
}
