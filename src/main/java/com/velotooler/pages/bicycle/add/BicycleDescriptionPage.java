package com.velotooler.pages.bicycle.add;

import com.velotooler.pages.MainPage;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementClickable;

public class BicycleDescriptionPage extends MainPage {

    @FindBy(xpath = "//md-select[@name='wheelSize']")
    private WebElement wheelSize;

    @FindBy(xpath = "//div[contains(text(), '29\" / 622 mm / 700C')]/ancestor::md-option")
    private WebElement wheelSizeParameter;

    @FindBy(xpath = "//md-select[@name='frameMaterial']")
    private WebElement frameMaterial;

    @FindBy(xpath = "//div[contains(text(), 'Titanium')]/ancestor::md-option")
    private WebElement frameMaterialParameter;

    @FindBy(xpath = "//md-select[@name='frameSize']")
    private WebElement frameSize;

    @FindBy(xpath = "//div[contains(text(), 'XS')]/ancestor::md-option")
    private WebElement frameSizeParameter;

    @FindBy(xpath = "//button[@class='show-chosen-color  md-button']")
    private WebElement selectButton;

    @FindBy(xpath = "//div[@title='Brown']")
    private WebElement colorBrown;

    @FindBy(xpath = "//span[text()='Choose']/parent::button")
    private WebElement chooseButton;

    @FindBy(xpath = "//form[@name='addAdditionalDescBikeForm']/descendant::button[@type='submit']")
    private WebElement nextButton;

    private BicycleComponentsPage bicycleComponentsPage;

    public BicycleDescriptionPage(WebDriver driver) {
        super(driver);
        bicycleComponentsPage = new BicycleComponentsPage(driver);
    }

    @SneakyThrows
    public BicycleComponentsPage addBicycleDescription() {
        selectWheelSize().selectFrameMaterial().selectFrameSizeParameter();
        selectButton.click();
        waitUntilElementClickable(driver, colorBrown);
        colorBrown.click();
        waitUntilElementClickable(driver, chooseButton);
        chooseButton.click();
        Thread.sleep(1000);
        jse.executeScript("arguments[0].click()", nextButton);
        return bicycleComponentsPage;
    }

    private BicycleDescriptionPage selectWheelSize() {
        waitUntilElementClickable(driver, wheelSize);
        wheelSize.click();
        waitUntilElementClickable(driver, wheelSizeParameter);
        wheelSizeParameter.click();
        return this;
    }

    private BicycleDescriptionPage selectFrameMaterial() {
        waitUntilElementClickable(driver, frameMaterial);
        frameMaterial.click();
        waitUntilElementClickable(driver, frameMaterialParameter);
        frameMaterialParameter.click();
        return this;
    }

    private BicycleDescriptionPage selectFrameSizeParameter() {
        waitUntilElementClickable(driver, frameSize);
        frameSize.click();
        waitUntilElementClickable(driver, frameSizeParameter);
        frameSizeParameter.click();
        return this;
    }
}
