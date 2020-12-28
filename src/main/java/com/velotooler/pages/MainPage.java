package com.velotooler.pages;

import com.velotooler.pages.sidemenu.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public abstract class MainPage extends AbstractPage {

    @FindBy(xpath = "//md-toast[@class='_md md-top md-left']")
    private WebElement toast;

    @FindBy(xpath = "//a[@data-sub-menu='profile']/parent::div")
    private WebElement profileMenu;

    @FindBy(xpath = "//span[contains(text(), 'Profile')]")
    private WebElement profile;

    @FindBy(xpath = "//span[contains(text(), 'Log Out')]")
    private WebElement logOut;

    @FindBy(xpath = "//span[contains(text(), 'Bicycles')]/ancestor::a[@class='ui-sidebar-btn']")
    private WebElement bicycles;


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage goToProfile() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(profileMenu));
        WebElement element = driver.findElement(By.xpath("//a[@data-sub-menu='profile']/parent::div"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        profileMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(profile));
        profile.click();
        return new ProfilePage(driver);
    }

    public HomePage logOut() {
        profileMenu.click();
        logOut.click();
        return new HomePage(driver);
    }

    public DashboardPage returnToDashboard() {
        waitUntilElementDisplayed(driver, bicycles);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", bicycles);
        return new DashboardPage(driver);
    }
}
