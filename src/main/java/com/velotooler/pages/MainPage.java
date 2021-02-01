package com.velotooler.pages;

import com.velotooler.core.element.ClickableElement;
import com.velotooler.pages.servicebooking.CreateRequestPage;
import com.velotooler.pages.servicebooking.ServiceBookingsPage;
import com.velotooler.pages.sidemenu.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//a[contains(text(), 'Book bicycle service')]")
    private WebElement bookBicycleService;

    @FindBy(xpath = "//div[@class='ui-sidebar-element-container']//span[contains(text(), 'Service Bookings')]")
    private WebElement serviceBookings;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage goToProfile() {
        new ClickableElement(profileMenu).selectByMoving(action);
        new ClickableElement(profile).clickWithWaitDisplayed(driver);
        return new ProfilePage(driver);
    }

    public HomePage logOut() {
        action.moveToElement(profileMenu).build().perform();
        jse.executeScript("arguments[0].click()", profileMenu);
        jse.executeScript("arguments[0].click()", profile);
        jse.executeScript("arguments[0].click()", logOut);
        return new HomePage(driver);
    }

    public DashboardPage returnToDashboard() {
        driver.navigate().refresh();
        new ClickableElement(bicycles).clickWithJS(jse);
        return new DashboardPage(driver);
    }

    public CreateRequestPage goToCreateRequestPage() {
        new ClickableElement(bookBicycleService).click();
        return new CreateRequestPage(driver);
    }

    public ServiceBookingsPage goToServiceBookingsPage() {
        new ClickableElement(serviceBookings).selectByMoving(action);
        return new ServiceBookingsPage(driver);
    }
}
