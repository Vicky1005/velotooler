package com.velotooler.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waiters.waitUntilElementClickable;
import static com.velotooler.core.util.Waiters.waitUntilElementDisplayed;

public class BicycleInfoPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='form-center']/descendant::h1")
    private WebElement bicycleName;

    @FindBy(xpath = "//button[@data-ng-click='duplicateBike($event)']")
    private WebElement duplicateBicycleButton;

    @FindBy(xpath = "//form[@name='confirmationDialogForm']/descendant::button[@type='submit']")
    private WebElement yesButton;

    @FindBy(xpath = "//label[@data-ng-bind-html='bike.serialNumber | notSpecified']")
    private WebElement sn;

    @FindBy(xpath = "//span[contains(text(), 'Bicycles')]/ancestor::a[@class='ui-sidebar-btn']")
    private WebElement bicycles;

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

    public DashboardPage returnToDashboard() {
        waitUntilElementClickable(driver, bicycles);
        bicycles.click();
        return new DashboardPage(driver);
    }
}
