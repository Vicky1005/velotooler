package com.velotooler.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waiters.waitUntilElementDisplayed;

public class AddBikePage extends AbstractPage{

    @FindBy(xpath = "//input[@name='sn']")
    private WebElement sn;

    @FindBy(xpath = "//md-select[@name='type']")
    private WebElement typeContainer;

    @FindBy(xpath = "//div[contains(text(), 'Mountain')]")
    private WebElement typeMountain;

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

    public AddBikePage(WebDriver driver) {
        super(driver);
    }

    public BicycleDescriptionPage addBike(String sn, String brand, String model, String releaseYear, String bicycleLocation) {
        waitUntilElementDisplayed(driver, this.sn);
        this.sn.sendKeys(sn);
        waitUntilElementDisplayed(driver, typeContainer);
        typeContainer.click();
        typeMountain.click();
        this.brand.sendKeys(brand);
        this.brand.click();
        waitUntilElementDisplayed(driver, brandConfirm);
        brandConfirm.click();
        this.model.sendKeys(model);
        this.releaseYear.sendKeys(releaseYear);
        this.bicycleLocation.sendKeys(bicycleLocation);
        this.bicycleLocation.click();
        WebElement locationItem = driver.findElement(By.xpath(String.format(locationItemAutocompleteXPath, bicycleLocation)));
        waitUntilElementDisplayed(driver, locationItem);
        locationItem.click();
        waitUntilElementDisplayed(driver, saveLocationButton);
        nextButton.click();
        return new BicycleDescriptionPage(driver);
    }
}
