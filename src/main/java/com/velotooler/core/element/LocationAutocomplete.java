package com.velotooler.core.element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationAutocomplete extends Autocomplete {

    private static final String XPATH_PARAMETER = "//div[contains(text(), '%s')]/ancestor::li";

    public LocationAutocomplete(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void selectValue(WebDriver driver, JavascriptExecutor jse, String value) {
        selectValue(driver, jse, XPATH_PARAMETER, value);
    }
}
