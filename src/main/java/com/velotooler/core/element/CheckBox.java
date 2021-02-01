package com.velotooler.core.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class CheckBox extends HtmlElement {

    public CheckBox(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void toggle() {
        getWrappedElement().click();
    }

    public void check(WebDriver driver) {
        if (!isChecked(driver)) {
            toggle();
        }
    }

    public void uncheck(WebDriver driver) {
        if (isChecked(driver)) {
            toggle();
        }
    }

    public boolean isChecked(WebDriver driver) {
        waitUntilElementDisplayed(driver, getWrappedElement());
        return getWrappedElement().isSelected();
    }
}
