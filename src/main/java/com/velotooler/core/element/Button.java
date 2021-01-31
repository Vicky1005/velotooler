package com.velotooler.core.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class Button extends HtmlElement {

    public Button(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void clickWithWait(WebDriver driver) {
        waitUntilElementDisplayed(driver, getWrappedElement());
        getWrappedElement().click();
    }

    public void click() {
        getWrappedElement().click();
    }

}
