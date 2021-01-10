package com.velotooler.pages.sidemenu;

import com.velotooler.pages.MainPage;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class ProfilePage extends MainPage {

    @FindBy(xpath = "//input[@ng-model='profile.user.email']")
    private WebElement userEmail;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    public String getUserEmail() {
        waitUntilElementDisplayed(driver, 60, userEmail);
        Thread.sleep(1000);
        return userEmail.getAttribute("value");
    }
}
