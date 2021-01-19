package com.velotooler.pages.servicebooking;

import com.velotooler.pages.MainPage;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class RequestPage extends MainPage {

    @FindBy(xpath = "//div[ @data-ng-if='showRequest.request.requestCost > 0']/h1")
    private WebElement serviceName;

    public RequestPage(WebDriver driver) {
        super(driver);
    }

    public String getServiceName() {
        return serviceName.getText();
    }

    @SneakyThrows
    public String getRequestId() {
        String[] urlParts;
        String delimeter = "/";
        urlParts = getUrl().split(delimeter);
        return urlParts[urlParts.length - 1].split("\\?")[0];
    }

    private String getUrl() {
        return driver.getCurrentUrl();
    }

    public RequestPage waitLoadingInfo() {
        waitUntilElementDisplayed(driver, serviceName);
        return this;
    }
}
