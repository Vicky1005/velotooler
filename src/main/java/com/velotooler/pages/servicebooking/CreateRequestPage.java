package com.velotooler.pages.servicebooking;

import com.velotooler.core.element.*;
import com.velotooler.pages.MainPage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class CreateRequestPage extends MainPage {

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
        log.debug("Set address: " + address);
        new ClickableElement(wellnesCheckServiceType).clickWithWaitDisplayed(driver);
        log.debug("Set service type: Wellness Check");
        new Button(nextButton).click();
        new ClickableElement(mechanic).clickWithWaitDisplayed(driver);
        log.debug("Mechanic is chosen");
        new ClickableElement(date).clickWithWaitDisplayed(driver);
        log.debug("Date is chosen");
        new ClickableElement(time).click();
        log.debug("Time is chosen");
        new Button(nextButton).click();
        new Input(bike).sendKeys(sn);
        new ClickableElement(bikeBySn).click();
        log.debug("Bicycle with serial number " + sn + " is chosen");
        new Button(scheduleButton).click();
        log.info("Request for bicycle with serial number " + sn + "is created");
        return requestPage;
    }

    @SneakyThrows
    private CreateRequestPage addAddress(String address) {
        LocationAutocomplete locationAutocomplete = new LocationAutocomplete(this.address);
        locationAutocomplete.sendKeys(address);
        locationAutocomplete.selectValue(driver, jse, address);
        Thread.sleep(1000);
        new Button(nextButton).click();
        return this;
    }
}

