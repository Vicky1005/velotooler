package com.velotooler.pages;

import com.velotooler.core.element.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementClickable;

public class HomePage extends MainPage {

    private static final String GOT_IT_BTN_XPATH = "//a[@class='cc_btn cc_btn_accept_all']";

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement logInButton;

    @FindBy(xpath = GOT_IT_BTN_XPATH)
    private WebElement gotItButton;

    private LoginPage loginPage;

    public HomePage(WebDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
    }

    public LoginPage goToLoginPage() {
        waitUntilElementClickable(driver, 30, logInButton);
        if (!driver.findElements(By.xpath(GOT_IT_BTN_XPATH)).isEmpty()) {
            new Button(gotItButton).clickWithWaitClickable(driver);
        }
        new Button(logInButton).click();
        return loginPage;
    }

}
