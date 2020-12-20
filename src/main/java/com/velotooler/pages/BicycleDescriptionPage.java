package com.velotooler.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waiters.waitUntilElementClickable;

public class BicycleDescriptionPage extends AbstractPage {

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


    public BicycleDescriptionPage(WebDriver driver) {
        super(driver);
    }

    public BicycleComponentsPage addBicycleDescription() {
        waitUntilElementClickable(driver, wheelSize);
        wheelSize.click();
        waitUntilElementClickable(driver, wheelSizeParameter);
        wheelSizeParameter.click();
        waitUntilElementClickable(driver, frameMaterial);
        frameMaterial.click();
        waitUntilElementClickable(driver, frameMaterialParameter);
        frameMaterialParameter.click();
        waitUntilElementClickable(driver, frameSize);
        frameSize.click();
        waitUntilElementClickable(driver, frameSizeParameter);
        frameSizeParameter.click();
        selectButton.click();
        waitUntilElementClickable(driver, colorBrown);
        colorBrown.click();
        waitUntilElementClickable(driver, chooseButton);
        chooseButton.click();
        nextButton.click();
        return new BicycleComponentsPage(driver);
    }
}
