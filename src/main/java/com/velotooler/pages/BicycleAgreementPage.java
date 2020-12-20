package com.velotooler.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waiters.waitUntilElementDisplayed;

public class BicycleAgreementPage extends AbstractPage{

    @FindBy(xpath = "//md-checkbox[@name='agreement']")
    private WebElement agreement;

    @FindBy(xpath = "//span[contains(text(), 'Create Bicycle')]/parent::button")
    private WebElement createBicycleButton;

    public BicycleAgreementPage(WebDriver driver) {
        super(driver);
    }

    public BicycleInfoPage agree() {
        waitUntilElementDisplayed(driver, agreement);
        agreement.click();
        createBicycleButton.click();
        return new BicycleInfoPage(driver);
    }
}
