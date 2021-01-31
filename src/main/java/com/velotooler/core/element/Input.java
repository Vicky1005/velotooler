package com.velotooler.core.element;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;


public class Input extends HtmlElement {

    public Input(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void sendKeys(WebDriver driver, String value) {
        waitUntilElementDisplayed(driver, getWrappedElement());
        getWrappedElement().sendKeys(value);
        getWrappedElement().click();
    }

    public void sendKeysUpperCase(Actions action, String value) {
        getWrappedElement().click();
        action.keyDown(Keys.SHIFT).sendKeys(value).keyUp(Keys.SHIFT).build().perform();
        action.keyUp(getWrappedElement(), Keys.SHIFT);
    }
}
