package com.velotooler.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.velotooler.core.util.Waiters.waitUntilElementDisplayed;

public class DashboardPage extends AbstractPage {

    @FindBy(xpath = "//a[@data-sub-menu='profile']/parent::div")
    private WebElement profileMenu;

    @FindBy(xpath = "//span[contains(text(), 'Profile')]")
    private WebElement profile;

    @FindBy(xpath = "//span[contains(text(), 'Log Out')]")
    private WebElement logOut;

    @FindBy(xpath = "//div[@fab-href='/add-bike']")
    private WebElement addBikeButton;

    @FindBy(xpath = "//div[@id='bike-list-item-0']")
    private WebElement lastBicycle;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage goToProfile() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(profileMenu));
        WebElement ele = driver.findElement(By.xpath("//a[@data-sub-menu='profile']/parent::div"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
        profileMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(profile));
        profile.click();
        return new ProfilePage(driver);
    }

    public AddBikePage goToAddBikePage() {
        waitUntilElementDisplayed(driver, addBikeButton);
        addBikeButton.click();
        return new AddBikePage(driver);
    }

    public MainPage logOut() {
        profileMenu.click();
        logOut.click();
        return new MainPage(driver);
    }

    public BicycleInfoPage goToBicycleInfoPage() {
        waitUntilElementDisplayed(driver, lastBicycle);
        lastBicycle.click();
        return new BicycleInfoPage(driver);
    }
}
