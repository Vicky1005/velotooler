package com.velotooler.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BicyclePhotosPage extends AbstractPage {

    @FindBy(xpath = "//form[@name='photosForm']/descendant::button[@type='submit']")
    private WebElement nextButton;

    public BicyclePhotosPage(WebDriver driver) {
        super(driver);
    }

    public BicycleReviewPage goToBicycleReviewPage() {
        nextButton.click();
        return new BicycleReviewPage(driver);
    }
}
