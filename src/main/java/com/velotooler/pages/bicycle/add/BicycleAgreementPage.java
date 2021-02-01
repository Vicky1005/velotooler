package com.velotooler.pages.bicycle.add;

import com.velotooler.core.element.Button;
import com.velotooler.core.element.CheckBox;
import com.velotooler.pages.MainPage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        new CheckBox(agreement).check(driver);
        new Button(createBicycleButton).clickWithWaitDisplayed(driver);
        return bicycleInfoPage;
    }
}
