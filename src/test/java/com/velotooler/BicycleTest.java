package com.velotooler;

import com.velotooler.api.bicycle.CustomerBikeApi;
import com.velotooler.api.bicycle.customerBike.CustomerBikeRequest;
import com.velotooler.core.driver.DriverFactory;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.FilterPage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import com.velotooler.steps.BicycleCreation;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.velotooler.core.util.SNGenerator.generateSn;

public class BicycleTest extends BaseTest {

    private CustomerBikeApi customerBikeApi = new CustomerBikeApi();
    private CustomerBikeRequest bicycleApiRequest = new JsonParser().get("customerBike", CustomerBikeRequest.class);

    @Test
    @Tag("smoke")
    @Epic("IAMAPPS-2222")
    @Story("IAMAPPS-3463")
    @DisplayName("Bicycle is created")
    @Severity(SeverityLevel.NORMAL)
    public void bicycleIsCreated() {
        String sn = generateSn();
        String createdSerialNumber = new BicycleCreation(dashboardPage)
                .createBicycle(sn)
                .getSnNumber();
        Assertions.assertEquals(sn, createdSerialNumber);
    }

    @Test
    @Tag("regression")
    @Epic("IAMAPPS-2222")
    @Story("IAMAPPS-3296")
    @DisplayName("Bicycle is duplicated")
    @Severity(SeverityLevel.NORMAL)
    public void bicycleIsDuplicated() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        BicycleInfoPage bicycleInfoPage = new DashboardPage(DriverFactory.get())
                .goToBicycleInfoPage()
                .duplicateBicycle();
        Assertions.assertEquals(bicycleApiRequest.getMake() + " " + bicycleApiRequest.getModel(),
                bicycleInfoPage.getBicycleName());
    }

    @Test
    @Tag("regression")
    @Epic("IAMAPPS-2222")
    @Story("IAMAPPS-3296")
    @DisplayName("Bicycle is deleted")
    @Severity(SeverityLevel.MINOR)
    public void bicycleIsDeleted() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        DashboardPage dashboard = new DashboardPage(DriverFactory.get())
                .deleteBicycleBySn(sn);
        Assertions.assertTrue(dashboard.isParticularBicycleExist(sn));
    }

    @Test
    @Tag("regression")
    @Epic("IAMAPPS-2222")
    @Story("IAMAPPS-3463")
    @DisplayName("Delete all bicycles")
    @Severity(SeverityLevel.NORMAL)
    public void deleteAllBicycles() {
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.get())
                .deleteAllBicycles();
        Assertions.assertFalse(dashboardPage.lastBicycleIsDisplayed());
    }

    @Test
    @Tag("regression")
    @Epic("IAMAPPS-2222")
    @Story("IAMAPPS-3463")
    @DisplayName("Check status is IN USE")
    @Severity(SeverityLevel.TRIVIAL)
    public void statusIsInUse() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        FilterPage filterPage = new DashboardPage(DriverFactory.get())
                .goToBicycleInfoPage()
                .duplicateBicycle()
                .returnToDashboard()
                .goToFilterPage()
                .setStatusFilter();
        Assertions.assertTrue(filterPage.isStatusInUse());
    }

    @Test
    @Tag("regression")
    @Epic("IAMAPPS-2222")
    @Story("IAMAPPS-3296")
    @DisplayName("Bicycle is filtered by serial number")
    @Severity(SeverityLevel.TRIVIAL)
    public void isFilteredBySn() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        FilterPage filterPage = new DashboardPage(DriverFactory.get())
                .goToFilterPage()
                .setSerialNumber(sn);
        Assertions.assertTrue(filterPage.isFilterAppliedBySerialNumber(sn));
    }
}

