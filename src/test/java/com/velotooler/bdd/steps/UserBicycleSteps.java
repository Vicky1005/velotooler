package com.velotooler.bdd.steps;

import com.velotooler.api.bicycle.request.BikeRequest;
import com.velotooler.core.driver.DriverFactory;
import com.velotooler.core.model.Auth;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.FilterPage;
import com.velotooler.pages.HomePage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import com.velotooler.steps.BicycleCreation;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

@Slf4j
public class UserBicycleSteps {

    private WebDriver driver = DriverFactory.get();

    @When("the user log in")
    public void logIn() {
        HomePage mainPage = new HomePage(driver);
        Auth auth = new JsonParser().get("logIn", Auth.class);
        mainPage.goToLoginPage().logIn(auth.getUsername(), auth.getPassword());
        log.info("Login is successful with user " + auth.getUsername());
    }

    @When("the user create bicycle with serial number {string}")
    public void createBicycle(String sn) {
        DashboardPage dashboardPage = new DashboardPage(driver);
        new BicycleCreation(dashboardPage).createBicycle(sn);
    }

    @When("the user duplicate bicycle with serial number {string}")
    public void duplicateBicycle(String sn) {
        DashboardPage dashboardPage = new DashboardPage(driver);
        new BicycleCreation(dashboardPage).createBicycle(sn);
        new BicycleInfoPage(driver)
                .duplicateBicycle();
    }

    @When("the user filter bicycle with serial number {string}")
    public void filterBicycle(String sn) {
        DashboardPage dashboardPage = new DashboardPage(driver);
        new BicycleCreation(dashboardPage).createBicycle(sn).returnToDashboard();
        new DashboardPage(driver)
                .goToFilterPage()
                .setSerialNumber(sn);
    }

    @When("^the user delete bicycle with serial number (\\S+)$")
    public void deleteBicycle(String sn) {
        DashboardPage dashboardPage = new DashboardPage(driver);
        new BicycleCreation(dashboardPage).createBicycle(sn).returnToDashboard().deleteBicycleBySn(sn.replaceAll("\"", ""));
    }

    @When("the user delete all bicycles")
    public void deleteAllBicycles() {
        new HomePage(driver).returnToDashboard().deleteAllBicycles();
    }

    @When("the user sets status filter with serial number {string}")
    public void deleteAllBicycles(String sn) {
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.get());
        new BicycleCreation(dashboardPage).createBicycle(sn).returnToDashboard()
                .goToBicycleInfoPage()
                .duplicateBicycle()
                .returnToDashboard()
                .goToFilterPage()
                .setStatusFilter();
    }

    @Then("bicycle with serial number {string} was created")
    public void verifyBicycleSn(String expectedSn) {
        BicycleInfoPage bicycleInfoPage = new BicycleInfoPage(driver);
        String actualSn = bicycleInfoPage.getSnNumber();
        Assertions.assertEquals(expectedSn, actualSn);
    }

    @Then("bicycle was duplicated")
    public void verifyBicycleIsDuplicated() {
        BikeRequest bicycleApiRequest = new JsonParser().get("customerBike", BikeRequest.class);
        BicycleInfoPage bicycleInfoPage = new BicycleInfoPage(driver);
        String bicycleName = bicycleInfoPage.getBicycleName();
        Assertions.assertEquals(bicycleApiRequest.getMake() + " " + bicycleApiRequest.getModel(), bicycleName);
    }

    @Then("bicycle was filtered by serial number {string}")
    public void verifyBicycleIsFiltered(String sn) {
        Assertions.assertTrue(new FilterPage(driver).isFilterAppliedBySerialNumber(sn));
    }

    @Then("bicycle with serial number {string} was deleted")
    public void verifyBicycleIsDeleted(String sn) {
        Assertions.assertTrue(new DashboardPage(driver).isParticularBicycleExist(sn));
    }

    @Then("all bicycles were deleted")
    public void verifyAllBicyclesWereDeleted() {
        Assertions.assertFalse(new DashboardPage(driver).lastBicycleIsDisplayed());
    }

    @Then("bicycle with serial number {string} has status In Use")
    public void verifyAllBicyclesWereDeleted(String sn) {
        Assertions.assertTrue(new FilterPage(driver).isFilterAppliedBySerialNumber(sn));
    }
}
