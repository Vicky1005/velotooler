package com.velotooler.pages.bicycle.info;

import com.velotooler.core.element.Button;
import com.velotooler.core.element.Input;
import com.velotooler.pages.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementClickable;

@Slf4j
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
        new Button(duplicateBicycleButton).click();
        new Button(yesButton).click();
        log.info("Bicycle is duplicated");
        return this;
    }

    public String getSnNumber() {
        Input inputSerialNumber = new Input(sn);
        inputSerialNumber.highlight(driver, jse);
        return inputSerialNumber.getText();
    }
}
