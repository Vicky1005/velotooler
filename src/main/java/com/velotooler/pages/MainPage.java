package com.velotooler.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waiters.waitUntilElementClickable;

public class MainPage extends AbstractPage {

    private static final String GOT_IT_BTN_XPATH = "//a[@class='cc_btn cc_btn_accept_all']";

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement logInButton;

    @FindBy(xpath = GOT_IT_BTN_XPATH)
    private WebElement gotItButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage() {
        waitUntilElementClickable(driver, 30, logInButton);
        if (!driver.findElements(By.xpath(GOT_IT_BTN_XPATH)).isEmpty()) {
            waitUntilElementClickable(driver, 30, gotItButton);
            gotItButton.click();
        }
        logInButton.click();
        return new LoginPage(driver);
    }

}
