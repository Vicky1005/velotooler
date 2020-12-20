package com.velotooler.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import static com.velotooler.core.util.Waiters.waitUntilElementDisplayed;

public class ProfilePage extends AbstractPage {

    @FindBy(xpath = "//input[@ng-model='profile.user.email']")
    private WebElement userEmail;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getUserEmail() {
        waitUntilElementDisplayed(driver, 60, userEmail);
        return userEmail.getAttribute("value");
    }
}
