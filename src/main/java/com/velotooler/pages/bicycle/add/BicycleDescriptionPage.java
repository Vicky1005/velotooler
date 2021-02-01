package com.velotooler.pages.bicycle.add;

import com.velotooler.core.element.Button;
import com.velotooler.core.element.ClickableElement;
import com.velotooler.core.element.Select;
import com.velotooler.pages.MainPage;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BicycleDescriptionPage extends MainPage {

    @FindBy(xpath = "//md-select[@name='wheelSize']")
    private WebElement wheelSize;

    @FindBy(xpath = "//md-select[@name='frameMaterial']")
    private WebElement frameMaterial;

    @FindBy(xpath = "//md-select[@name='frameSize']")
    private WebElement frameSize;

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
        new Button(selectButton).click();
        new ClickableElement(colorBrown).clickWithWaitClickable(driver);
        new Button(chooseButton).clickWithWaitClickable(driver);
        Thread.sleep(1000);
        new Button(nextButton).clickWithJS(jse);
        return bicycleComponentsPage;
    }

    private BicycleDescriptionPage selectWheelSize() {
        new Select(wheelSize).select(driver, jse, "29\" / 622 mm / 700C");
        return this;
    }

    private BicycleDescriptionPage selectFrameMaterial() {
        new Select(frameMaterial).select(driver, jse, "Titanium");
        return this;
    }

    private BicycleDescriptionPage selectFrameSizeParameter() {
        new Select(frameSize).select(driver, jse, "XS");
        return this;
    }
}
