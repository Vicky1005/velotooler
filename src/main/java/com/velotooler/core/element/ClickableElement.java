package com.velotooler.core.element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.velotooler.core.util.Waits.waitUntilElementClickable;
import static com.velotooler.core.util.Waits.waitUntilElementDisplayed;

public class ClickableElement  extends HtmlElement{

    public ClickableElement(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void clickWithWaitDisplayed(WebDriver driver) {
        waitUntilElementDisplayed(driver, getWrappedElement());
        getWrappedElement().click();
    }
    public void clickWithWaitClickable(WebDriver driver) {
        waitUntilElementClickable(driver, getWrappedElement());
        getWrappedElement().click();
    }

    public void click() {
        getWrappedElement().click();
    }

    public void clickWithJS(JavascriptExecutor jse) {
        jse.executeScript("arguments[0].click()", getWrappedElement());
    }
}
