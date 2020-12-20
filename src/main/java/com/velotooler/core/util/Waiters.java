package com.velotooler.core.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    private final static long DEFAULT_TIME = 30;

    public static WebElement waitUntilElementDisplayed(WebDriver driver, long time, WebElement element) {
        return new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilElementDisplayed(WebDriver driver, WebElement element) {
        return waitUntilElementDisplayed(driver, DEFAULT_TIME, element);
    }

    public static WebElement waitUntilElementClickable(WebDriver driver, long time, WebElement element) {
        return new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitUntilElementClickable(WebDriver driver, WebElement element) {
        return waitUntilElementClickable(driver, DEFAULT_TIME, element);
    }
}