package com.velotooler.pages.servicebooking;

import com.velotooler.pages.MainPage;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class CreateRequestPage extends MainPage {

    private final static String LOCATION_ITEM_AUTOCOMPLETE_XPATH = "//div[contains(text(), '%s')]/ancestor::li";

    @FindBy(xpath = "//input[@name='location']")
    private WebElement address;

    @FindBy(xpath = "//button[@class='md-primary md-raised md-big md-button']")
    private WebElement nextButton;

    @FindBy(xpath = "//span[contains(text(), 'Wellness Check')]/parent::button")
    private WebElement wellnesCheckServiceType;

    @FindBy(xpath = "//div[@href='mechanic-public/Aleksey.Domorenok']")
    private WebElement mechanic;

    @FindBy(xpath = "//div[@class='request-availability__date-container'][2]")
    private WebElement date;

    @FindBy(xpath = "//div[@data-ng-repeat='timeSlot in ctrl.currentlySelectedDate.morning.timeSlots'][1]")
    private WebElement time;

    @FindBy(xpath = "//input[@name='bike']")
    private WebElement bike;

    @FindBy(xpath = "//div[@class='bicycle-step__item-description']")
    private WebElement bikeBySn;

    @FindBy(xpath = "//span[contains(text(), 'Continue')]/parent::button[@aria-label='next']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[@class='create-request__summary-footer']/button[contains(text(), 'Schedule')]")
    private WebElement scheduleButton;

    private RequestPage requestPage;

    public CreateRequestPage(WebDriver driver) {
        super(driver);
        this.requestPage = new RequestPage(driver);
    }

    public RequestPage createRequest(String address, String sn) {
        addAddress(address);
        waitUntilElementDisplayed(driver, wellnesCheckServiceType);
        wellnesCheckServiceType.click();
        nextButton.click();
        mechanic.click();
        date.click();
        time.click();
        nextButton.click();
        bike.sendKeys(sn);
        bikeBySn.click();
        scheduleButton.click();
        return requestPage;
    }

    @SneakyThrows
    private CreateRequestPage addAddress(String address) {
        this.address.sendKeys(address);
        jse.executeScript("arguments[0].click()", this.address);
        WebElement addressItem = driver.findElement(By.xpath(String.format(LOCATION_ITEM_AUTOCOMPLETE_XPATH, address)));
        waitUntilElementDisplayed(driver, addressItem);
        jse.executeScript("arguments[0].click()", addressItem);
        Thread.sleep(1000);
        nextButton.click();
        return this;
    }

}

