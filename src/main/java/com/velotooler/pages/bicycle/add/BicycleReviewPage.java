package com.velotooler.pages.bicycle.add;

import com.velotooler.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementClickable;

public class BicycleReviewPage extends MainPage {

    @FindBy(xpath = "//form[@name='photosForm']/descendant::button[@type='submit']")
    private WebElement nextButton;

    @FindBy(xpath = "//span[contains(text(), 'Continue')]/parent::button")
    private WebElement continueButton;

    private BicycleAgreementPage bicycleAgreementPage;

    public BicycleReviewPage(WebDriver driver) {
        super(driver);
        bicycleAgreementPage = new BicycleAgreementPage(driver);
    }

    public BicycleAgreementPage goToBicycleAgreementPages() {
        waitUntilElementClickable(driver, continueButton);
        continueButton.click();
        return bicycleAgreementPage;
    }
}
