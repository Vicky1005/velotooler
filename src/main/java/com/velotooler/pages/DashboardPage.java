package com.velotooler.pages;

import com.velotooler.core.element.Button;
import com.velotooler.core.element.ClickableElement;
import com.velotooler.pages.bicycle.add.AddBikePage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

@Slf4j
public class DashboardPage extends MainPage {

    private static final String LAST_BICYCLE_XPATH = "//div[@id='bike-list-item-0']";
    private static final String BICYCLE_BY_SN_XPATH = "//span[contains(text(), '%s')]/ancestor::div[@class='pageable-list__item grid-list__item']";
    private static final String BICYCLE_MENU_BY_SN_XPATH = "//span[contains(text(), '%s')]/ancestor::div[@class='pageable-list__item grid-list__item']/descendant::button[@class='md-icon-button grid-list__menu-button md-button']";

    @FindBy(xpath = "//div[@fab-href='/add-bike']")
    private WebElement addBikeButton;

    @FindBy(xpath = LAST_BICYCLE_XPATH)
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

    @FindBy(xpath = "//button[@class='md-button md-raised pageable-list__filter-button']")
    private WebElement filter;

    private FilterPage filterPage;
    private BicycleInfoPage bicycleInfoPage;
    private AddBikePage addBikePage;

    public DashboardPage(WebDriver driver) {
        super(driver);
        filterPage = new FilterPage(driver);
        bicycleInfoPage = new BicycleInfoPage(driver);
        addBikePage = new AddBikePage(driver);
    }

    public AddBikePage goToAddBikePage() {
        new Button(addBikeButton).clickWithWaitDisplayed(driver);
        return addBikePage;
    }

    public BicycleInfoPage goToBicycleInfoPage() {
        new Button(lastBicycle).clickWithWaitDisplayed(driver);
        return bicycleInfoPage;
    }

    public DashboardPage deleteAllBicycles() {
        while (!driver.findElements(By.xpath(LAST_BICYCLE_XPATH)).isEmpty()) {
            deleteLastBicycle();
            waitUntilElementDisplayed(driver, 60, toast);
            driver.navigate().refresh();
        }
        log.info("All bicycles are deleted");
        return this;
    }

    public DashboardPage deleteLastBicycle() {
        waitUntilElementDisplayed(driver, lastBicycle);
        new ClickableElement(lastBicycleMenu).clickWithWaitClickable(driver);
        return recycle();
    }

    public DashboardPage deleteBicycleBySn(String sn) {
        WebElement menuOfParticularBicycle = driver.findElement(By.xpath(String.format(BICYCLE_MENU_BY_SN_XPATH, sn)));
        new ClickableElement(menuOfParticularBicycle).clickWithWaitDisplayed(driver);
        log.info("Bicycle with " + sn + " is deleted");
        return recycle();
    }

    public boolean lastBicycleIsDisplayed() {
        return !driver.findElements(By.xpath(LAST_BICYCLE_XPATH)).isEmpty();
    }

    public boolean isParticularBicycleExist(String sn) {
        return !driver.findElements(By.xpath(String.format(BICYCLE_BY_SN_XPATH, sn))).isEmpty();
    }

    public FilterPage goToFilterPage() {
        new ClickableElement(filter).clickWithJS(jse);
        return filterPage;
    }

    private DashboardPage recycle() {
        new ClickableElement(changeStatus).clickWithWaitClickable(driver);
        new ClickableElement(recycled).clickWithJS(jse);
        new Button(yesButton).clickWithWaitClickable(driver);
        return this;
    }
}
