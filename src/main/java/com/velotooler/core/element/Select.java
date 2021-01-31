package com.velotooler.core.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class Select extends HtmlElement {

    private final static String XPATH_PARAMETER = "//div[contains(text(), '%s')]/ancestor::md-option";

    public Select(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void select(WebDriver driver, String value) {
        waitUntilElementDisplayed(driver, getWrappedElement());
        getWrappedElement().click();
        driver.findElement(By.xpath(String.format(XPATH_PARAMETER, value))).click();
    }
}
