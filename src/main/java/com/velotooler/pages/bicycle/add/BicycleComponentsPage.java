package com.velotooler.pages.bicycle.add;

import com.velotooler.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class BicycleComponentsPage extends MainPage {

    @FindBy(xpath = "//md-select[@ng-change='changeGroupset()']")
    private WebElement groupSetName;

    @FindBy(xpath = "//div[contains(text(), 'Shimano XTR')]/ancestor::md-option")
    private WebElement groupSetNameParameter;

    @FindBy(xpath = "//form[@name='addComponentForm']/descendant::span[text()='frame']")
    private WebElement frame;

    @FindBy(xpath = "//form[@name='addComponentForm']/descendant::button[@ng-click='submit(addComponentForm)']")
    private WebElement nextButton;

    private BicyclePhotosPage bicyclePhotosPage;

    public BicycleComponentsPage(WebDriver driver) {
        super(driver);
        bicyclePhotosPage = new BicyclePhotosPage(driver);
    }

    public BicyclePhotosPage addBicycleComponents() {
        waitUntilElementDisplayed(driver, groupSetName);
        jse.executeScript("arguments[0].click()", groupSetName);
        waitUntilElementDisplayed(driver, groupSetNameParameter);
        jse.executeScript("arguments[0].click()", groupSetNameParameter);
        waitUntilElementDisplayed(driver, frame);
        nextButton.click();
        return bicyclePhotosPage;
    }
}
