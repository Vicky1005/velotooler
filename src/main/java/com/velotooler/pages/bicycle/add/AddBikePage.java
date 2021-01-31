package com.velotooler.pages.bicycle.add;

import com.velotooler.core.element.*;
import com.velotooler.pages.MainPage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

@Slf4j
public class AddBikePage extends MainPage {

    private final static String LOCATION_ITEM_AUTOCOMPLETE_XPATH = "//div[contains(text(), '%s')]/ancestor::li";

    @FindBy(xpath = "//input[@name='sn']")
    private WebElement sn;

    @FindBy(xpath = "//md-select[@name='type']")
    private WebElement typeContainer;

    @FindBy(xpath = "//input[@name='make']")
    private WebElement brand;

    @FindBy(xpath = "//input[@ng-model='bike.model']")
    private WebElement model;

    @FindBy(xpath = "//input[@name='year']")
    private WebElement releaseYear;

    @FindBy(xpath = "//input[@name='location']")
    private WebElement bicycleLocation;

    @FindBy(xpath = "//form[@name='addBikeForm']/descendant::button[@type='submit']")
    private WebElement nextButton;

    @FindBy(xpath = "//i[@class='location-autocomplete__favorite__icon fa fa-star-o'] | //i[@class='location-autocomplete__favorite__icon fa fa-star']")
    private WebElement saveLocationButton;

    private BicycleDescriptionPage bicycleDescriptionPage;

    public AddBikePage(WebDriver driver) {
        super(driver);
        this.bicycleDescriptionPage = new BicycleDescriptionPage(driver);
    }

    @SneakyThrows
    public BicycleDescriptionPage addBike(String sn, String brand, String model, String releaseYear, String bicycleLocation) {
        addSn(sn).selectType().addBrand(brand);
        new Input(this.model).sendKeysUpperCase(action, model);
        log.debug("Set model: " + model);
        new Input(this.releaseYear).sendKeys(releaseYear);
        log.debug("Set release year: " + releaseYear);
        addLocation(bicycleLocation);
        log.debug("Set location: " + bicycleLocation);
        Thread.sleep(1000);
        new Button(nextButton).click();
        return bicycleDescriptionPage;
    }

    private AddBikePage addSn(String sn) {
        new Input(this.sn).sendKeys(sn);
        log.debug("Set serial number: " + sn);
        return this;
    }

    private AddBikePage selectType() {
        new Select(typeContainer).select(driver, "Mountain");
        log.debug("Select type: Mountain");
        return this;
    }

    private AddBikePage addBrand(String brand) {
        Autocomplete brandAutocomplete = new Autocomplete(this.brand);
        brandAutocomplete.sendKeys(brand);
        brandAutocomplete.selectValue(driver, jse, "Valley Cycles");
        log.debug("Set brand: " + brand);
        return this;
    }

    private AddBikePage addLocation(String bicycleLocation) {
        LocationAutocomplete locationAutocomplete = new LocationAutocomplete(this.bicycleLocation);
        locationAutocomplete.sendKeys(bicycleLocation);
        locationAutocomplete.selectValue(driver, jse, bicycleLocation);
        waitUntilElementDisplayed(driver, saveLocationButton);
        return this;
    }
}
