package com.velotooler.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waiters.waitUntilElementDisplayed;

public class BicycleComponentsPage extends AbstractPage{

    @FindBy(xpath = "//md-select[@ng-change='changeGroupset()']")
    private WebElement groupSetName;

    @FindBy(xpath = "//div[contains(text(), 'Shimano XTR')]/ancestor::md-option")
    private WebElement groupSetNameParameter;

    @FindBy(xpath = "//form[@name='addComponentForm']/descendant::span[text()='frame']")
    private WebElement frame;

    @FindBy(xpath = "//form[@name='addComponentForm']/descendant::button[@ng-click='submit(addComponentForm)']")
    private WebElement nextButton;

    public BicycleComponentsPage(WebDriver driver) {
        super(driver);
    }

    public BicyclePhotosPage addBicycleComponents() {
        waitUntilElementDisplayed(driver, groupSetName);
        groupSetName.click();
        waitUntilElementDisplayed(driver, groupSetNameParameter);
        groupSetNameParameter.click();
        waitUntilElementDisplayed(driver, frame);
        nextButton.click();
        return new BicyclePhotosPage(driver);
    }
}
