package com.velotooler.pages.bicycle.add;

import com.velotooler.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BicyclePhotosPage extends MainPage {

    @FindBy(xpath = "//form[@name='photosForm']/descendant::button[@type='submit']")
    private WebElement nextButton;

    private BicycleReviewPage bicycleReviewPage;

    public BicyclePhotosPage(WebDriver driver) {
        super(driver);
        bicycleReviewPage = new BicycleReviewPage(driver);
    }

    public BicycleReviewPage goToBicycleReviewPage() {
        nextButton.click();
        return bicycleReviewPage;
    }
}
