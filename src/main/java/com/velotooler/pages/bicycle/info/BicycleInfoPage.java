package com.velotooler.pages.bicycle.info;

import com.velotooler.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementClickable;
import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class BicycleInfoPage extends MainPage {

    @FindBy(xpath = "//div[@class='form-center']/descendant::h1")
    private WebElement bicycleName;

    @FindBy(xpath = "//button[@data-ng-click='duplicateBike($event)']")
    private WebElement duplicateBicycleButton;

    @FindBy(xpath = "//form[@name='confirmationDialogForm']/descendant::button[@type='submit']")
    private WebElement yesButton;

    @FindBy(xpath = "//label[@data-ng-bind-html='bike.serialNumber | notSpecified']")
    private WebElement sn;

    public BicycleInfoPage(WebDriver driver) {
        super(driver);
    }

    public String getBicycleName() {
        waitUntilElementClickable(driver, 30, bicycleName);
        return bicycleName.getText();
    }

    public BicycleInfoPage duplicateBicycle() {
        duplicateBicycleButton.click();
        yesButton.click();
        return this;
    }

    public String getSnNumber() {
        waitUntilElementDisplayed(driver, sn);
        return sn.getText();
    }
}
