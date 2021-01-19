package com.velotooler.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected Actions action;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

}
