package com.velotooler.core.element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class Autocomplete extends HtmlElement {

    private static final String XPATH_PARAMETER = "//span[text()='%s']/ancestor::li";

    public Autocomplete(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void selectValue(WebDriver driver, JavascriptExecutor jse, String value) {
        selectValue(driver, jse, XPATH_PARAMETER, value);
    }

    protected void selectValue(WebDriver driver, JavascriptExecutor jse, String xpath, String value) {
        waitUntilElementDisplayed(driver, getWrappedElement());
        getWrappedElement().click();
        WebElement confirmElement = driver.findElement(By.xpath(String.format(xpath, value)));
        waitUntilElementDisplayed(driver, confirmElement);
        jse.executeScript("arguments[0].click()", confirmElement);
    }
}
