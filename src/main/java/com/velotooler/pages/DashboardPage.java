package com.velotooler.pages;

import com.velotooler.pages.bicycle.add.AddBikePage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementClickable;
import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class DashboardPage extends MainPage {

    private static final String LAST_BICYCLE_XPATH = "//div[@id='bike-list-item-0']";

    @FindBy(xpath = "//div[@fab-href='/add-bike']")
    private WebElement addBikeButton;

    @FindBy(xpath = "//div[@id='bike-list-item-0']")
    private WebElement lastBicycle;

    @FindBy(xpath = "//div[@id='bike-list-item-0']//button[@class='md-icon-button grid-list__menu-button md-button']")
    private WebElement lastBicycleMenu;

    @FindBy(xpath = "//div[@class='_md md-open-menu-container md-whiteframe-z2 md-active md-clickable']/descendant::button[@ng-click = 'bla.changeStatus($event, bike)']")
    private WebElement changeStatus;

    @FindBy(xpath = "//span[contains(text(), 'Recycled/Retired')]/parent::button")
    private WebElement recycled;

    @FindBy(xpath = "//span[contains(text(), 'yes')]/parent::button[@class='md-raised md-primary md-big md-button-uppercase md-button md-ink-ripple']")
    private WebElement yesButton;

    @FindBy(xpath = "//md-toast[@class='_md md-top md-left']")
    private WebElement toast;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public AddBikePage goToAddBikePage() {
        waitUntilElementDisplayed(driver, addBikeButton);
        addBikeButton.click();
        return new AddBikePage(driver);
    }

    public BicycleInfoPage goToBicycleInfoPage() {
        waitUntilElementDisplayed(driver, 60, lastBicycle);
        lastBicycle.click();
        return new BicycleInfoPage(driver);
    }

    public DashboardPage deleteAllBicycles() {
        while (!driver.findElements(By.xpath(LAST_BICYCLE_XPATH)).isEmpty()) {
            deleteLastBicycle();
            waitUntilElementDisplayed(driver, 60, toast);
            driver.navigate().refresh();
        }
        return this;
    }

    public DashboardPage deleteLastBicycle() {
        waitUntilElementDisplayed(driver, lastBicycle);
        waitUntilElementClickable(driver, lastBicycleMenu);
        lastBicycleMenu.click();
        waitUntilElementClickable(driver, changeStatus);
        changeStatus.click();
        waitUntilElementClickable(driver, recycled);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", recycled);
        waitUntilElementClickable(driver, yesButton);
        yesButton.click();
        return this;
    }

    public boolean lastBicycleIsDisplayed() {
        return !driver.findElements(By.xpath(LAST_BICYCLE_XPATH)).isEmpty();
    }
}
