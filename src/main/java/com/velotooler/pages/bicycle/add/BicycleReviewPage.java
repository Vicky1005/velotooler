package com.velotooler.pages.bicycle.add;

import com.velotooler.core.element.Button;
import com.velotooler.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        new Button(continueButton).clickWithWaitClickable(driver);
        return bicycleAgreementPage;
    }
}
