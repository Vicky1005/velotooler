package com.velotooler.pages.bicycle.add;

import com.velotooler.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class AddBikePage extends MainPage {

    @FindBy(xpath = "//input[@name='sn']")
    private WebElement sn;

    @FindBy(xpath = "//md-select[@name='type']")
    private WebElement typeContainer;

    @FindBy(xpath = "//div[contains(text(), 'Mountain')]")
    private WebElement typeParameter;

    @FindBy(xpath = "//input[@name='make']")
    private WebElement brand;

    @FindBy(xpath = "//span[text()='Valley Cycles']/parent::md-autocomplete-parent-scope")
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

    private String locationItemAutocompleteXPath = "//div[contains(text(), '%s')]/ancestor::li";

    private BicycleDescriptionPage bicycleDescriptionPage;

    public AddBikePage(WebDriver driver) {
        super(driver);
        this.bicycleDescriptionPage = new BicycleDescriptionPage(driver);
    }

    public BicycleDescriptionPage addBike(String sn, String brand, String model, String releaseYear, String bicycleLocation) {
        addSn(sn).selectType().addBrand(brand);
        this.model.sendKeys(model);
        this.releaseYear.sendKeys(releaseYear);
        addLocation(bicycleLocation);
        nextButton.click();
        return bicycleDescriptionPage;
    }

    private AddBikePage addSn(String sn) {
        waitUntilElementDisplayed(driver, this.sn);
        this.sn.sendKeys(sn);
        return this;
    }

    private AddBikePage selectType() {
        waitUntilElementDisplayed(driver, typeContainer);
        typeContainer.click();
        typeParameter.click();
        return this;
    }

    private AddBikePage addBrand(String brand) {
        this.brand.sendKeys(brand);
        this.brand.click();
        waitUntilElementDisplayed(driver, brandConfirm);
        brandConfirm.click();
        return this;
    }

    private AddBikePage addLocation(String bicycleLocation) {
        this.bicycleLocation.sendKeys(bicycleLocation);
        this.bicycleLocation.click();
        WebElement locationItem = driver.findElement(By.xpath(String.format(locationItemAutocompleteXPath, bicycleLocation)));
        waitUntilElementDisplayed(driver, locationItem);
        locationItem.click();
        waitUntilElementDisplayed(driver, saveLocationButton);
        return this;
    }
}
