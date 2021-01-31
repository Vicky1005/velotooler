package com.velotooler.core.element;

import org.openqa.selenium.WebElement;

public class CheckBox extends HtmlElement {

    public CheckBox(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void toggle() {
        getWrappedElement().click();
    }

    public void check() {
        if (!isChecked()) {
            toggle();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            toggle();
        }
    }

    public boolean isChecked() {
        return getWrappedElement().isSelected();
    }
}
