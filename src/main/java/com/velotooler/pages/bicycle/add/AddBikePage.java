package com.velotooler.pages.bicycle.add;

import com.velotooler.pages.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementClickable;
import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

@Slf4j
public class AddBikePage extends MainPage {

    private final static String LOCATION_ITEM_AUTOCOMPLETE_XPATH = "//div[contains(text(), '%s')]/ancestor::li";

    @FindBy(xpath = "//input[@name='sn']")
    private WebElement sn;

    @FindBy(xpath = "//md-select[@name='type']")
    private WebElement typeContainer;

    @FindBy(xpath = "//div[contains(text(), 'Mountain')]")
    private WebElement typeParameter;

    @FindBy(xpath = "//input[@name='make']")
    private WebElement brand;

    @FindBy(xpath = "//span[text()='Valley Cycles']/ancestor::li")
    private WebElement brandConfirm;

    @FindBy(xpath = "//input[@ng-model='bike.model']")
    private WebElement model;

    @FindBy(xpath = "//input[@name='year']")
    private WebElement releaseYear;

    @FindBy(xpath = "//input[@name='location']")
    private WebElement bicycleLocation;

    @FindBy(xpath = "//form[@name='addBikeForm']/descendant::button[@type='submit']")
    private WebElement nextButton;

    @FindBy(xpath = "//i[@class='location-autocomplete__favorite__icon fa fa-star-o']")
    private WebElement saveLocationButton;

    private BicycleDescriptionPage bicycleDescriptionPage;

    public AddBikePage(WebDriver driver) {
        super(driver);
        this.bicycleDescriptionPage = new BicycleDescriptionPage(driver);
    }

    public BicycleDescriptionPage addBike(String sn, String brand, String model, String releaseYear, String bicycleLocation) {
        addSn(sn).selectType().addBrand(brand);
        this.model.click();
        action.keyDown(Keys.SHIFT).sendKeys(model).keyUp(Keys.SHIFT).build().perform();
        action.keyUp(this.model, Keys.SHIFT);
        log.debug("Set model: " + model);
        this.releaseYear.sendKeys(releaseYear);
        log.debug("Set release year: " + releaseYear);
        addLocation(bicycleLocation);
        log.debug("Set location: " + bicycleLocation);
        nextButton.click();
        return bicycleDescriptionPage;
    }

    private AddBikePage addSn(String sn) {
        waitUntilElementDisplayed(driver, this.sn);
        this.sn.sendKeys(sn);
        log.debug("Set serial number: " + sn);
        return this;
    }

    private AddBikePage selectType() {
        waitUntilElementDisplayed(driver, typeContainer);
        typeContainer.click();
        typeParameter.click();
        log.debug("Select type: Mountain");
        return this;
    }

    private AddBikePage addBrand(String brand) {
        this.brand.sendKeys(brand);
        this.brand.click();
        waitUntilElementClickable(driver, brandConfirm);
        jse.executeScript("arguments[0].click()", brandConfirm);
        log.debug("Set brand: " + brand);
        return this;
    }

    private AddBikePage addLocation(String bicycleLocation) {
        this.bicycleLocation.sendKeys(bicycleLocation);
        jse.executeScript("arguments[0].click()", this.bicycleLocation);
        WebElement locationItem = driver.findElement(By.xpath(String.format(LOCATION_ITEM_AUTOCOMPLETE_XPATH, bicycleLocation)));
        waitUntilElementDisplayed(driver, locationItem);
        jse.executeScript("arguments[0].click()", locationItem);
        waitUntilElementDisplayed(driver, saveLocationButton);
        return this;
    }
}
