package com.velotooler.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.velotooler.core.util.Waits.*;

public class FilterPage extends MainPage {

    @FindBy(xpath = "//md-select[@name='status']")
    private WebElement statusMenu;

    @FindBy(xpath = "//div[contains(text(), 'In Use')]/parent::md-option[@role='option']")
    private WebElement inUseOption;

    @FindBy(xpath = "//div[@class='pageable-list__item grid-list__item']/descendant::span[@class='used']")
    private List<WebElement> bicyclesByStatus;

    @FindBy(xpath = "//input[@name='sn']")
    private WebElement serialNumber;

    @FindBy(xpath = "//md-input-container[@class='md-input-has-value']/descendant::button")
    private WebElement applyButton;

    @FindBy(xpath = "//div[@id='bike-list-item-0']/descendant::span[@data-ng-bind-html='bike.serialNumber|notSpecified']")
    private WebElement lastBicycleSerialNumber;

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    public FilterPage setStatusFilter() {
        statusMenu.click();
        inUseOption.click();
        return this;
    }

    public boolean isStatusInUse() {
        waitUntilElementsDisplayed(driver, bicyclesByStatus);
        for (int i = 0; i < bicyclesByStatus.size(); i++) {
            if (!bicyclesByStatus.get(i).getText().contains("IN USE")) {
                return false;
            }
        }
        return true;
    }

    public FilterPage setSerialNumber(String sn) {
        String script = String.format("document.getElementsByName('sn')[0].setAttribute('value', '%s')", sn);
        jse.executeScript(script);
        serialNumber.sendKeys(" ");
        serialNumber.sendKeys(Keys.BACK_SPACE);
        serialNumber.sendKeys(Keys.ENTER);
        return this;
    }

    public boolean isFilterAppliedBySerialNumber(String sn) {
        waitUntilElementClickable(driver, lastBicycleSerialNumber);
        return lastBicycleSerialNumber.getText().contains(sn);
    }
}
