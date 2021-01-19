package com.velotooler.pages.servicebooking;

import com.velotooler.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class ServiceBookingsPage extends MainPage {

    private final static String SERVICE_REQUEST_XPATH = "//a[@href='request/%s']//div[@class='rg__col rg__col--task']/div";

    @FindBy(xpath = "//div[@class='requests']")
    private WebElement requests;

    public ServiceBookingsPage(WebDriver driver) {
        super(driver);
    }

    public String getServiceRequestName(String requestId) {
        waitUntilElementDisplayed(driver, requests);
        WebElement serviceRequest = driver.findElement(By.xpath(String.format(SERVICE_REQUEST_XPATH, requestId)));
        waitUntilElementDisplayed(driver, serviceRequest);
        return serviceRequest.getText();
    }
}
