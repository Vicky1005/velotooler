package com.velotooler.pages.bicycle.add;

import com.velotooler.core.element.Button;
import com.velotooler.core.element.Select;
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
        new Select(groupSetName).select(driver, jse, "Shimano XTR");
        waitUntilElementDisplayed(driver, frame);
        new Button(nextButton).click();
        return bicyclePhotosPage;
    }
}
