package com.velotooler.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waiters.waitUntilElementClickable;

public class BicycleReviewPage extends AbstractPage {

    @FindBy(xpath = "//form[@name='photosForm']/descendant::button[@type='submit']")
    private WebElement nextButton;

    @FindBy(xpath = "//span[contains(text(), 'Continue')]/parent::button")
    private WebElement continueButton;

    public BicycleReviewPage(WebDriver driver) {
        super(driver);
    }

    public BicycleAgreementPage goToBicycleAgreementPages() {
        waitUntilElementClickable(driver, continueButton);
        continueButton.click();
        return new BicycleAgreementPage(driver);
    }
}
