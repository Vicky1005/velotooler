package com.velotooler.pages.bicycle.add;

import com.velotooler.pages.MainPage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class BicycleAgreementPage extends MainPage {

    @FindBy(xpath = "//md-checkbox[@name='agreement']")
    private WebElement agreement;

    @FindBy(xpath = "//span[contains(text(), 'Create Bicycle')]/parent::button")
    private WebElement createBicycleButton;

    private BicycleInfoPage bicycleInfoPage;

    public BicycleAgreementPage(WebDriver driver) {
        super(driver);
        bicycleInfoPage = new BicycleInfoPage(driver);
    }

    public BicycleInfoPage agree() {
        waitUntilElementDisplayed(driver, agreement);
        agreement.click();
        createBicycleButton.click();
        return bicycleInfoPage;
    }
}
