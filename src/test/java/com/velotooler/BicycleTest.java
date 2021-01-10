package com.velotooler;

import com.velotooler.model.Bicycle;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.FilterPage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import com.velotooler.steps.BicycleCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.velotooler.core.util.SNGenerator.generateSn;

public class BicycleTest extends BaseTest {

    @Test
    public void bicycleIsCreated() {
        String sn = generateSn();
        String createdSerialNumber = new BicycleCreation(dashboardPage)
                .createBicycle(sn)
                .getSnNumber();
        Assertions.assertEquals(sn, createdSerialNumber);
    }

    @Test
    public void bicycleIsDuplicated() {
        Bicycle bicycle = parser.get("bicycle", Bicycle.class);
        String sn = generateSn();
        BicycleInfoPage bicycleInfoPage = new BicycleCreation(dashboardPage)
                .createBicycle(sn)
                .duplicateBicycle();
        String expectedBicycleName = bicycle.getBrand() + " " + bicycle.getModel().toUpperCase();
        Assertions.assertEquals(expectedBicycleName, bicycleInfoPage.getBicycleName());
    }

    @Test
    public void bicycleIsDeleted() {
        String sn = generateSn();
        DashboardPage dashboard = new BicycleCreation(dashboardPage)
                .createBicycle(sn).returnToDashboard().deleteBicycleBySn(sn);
        Assertions.assertTrue(dashboard.isParticularBicycleExist(sn));
    }

    @Test
    public void deleteAllBicycles() {
        DashboardPage dashboardPage = new DashboardPage(driver).deleteAllBicycles();
        Assertions.assertFalse(dashboardPage.lastBicycleIsDisplayed());
    }

    @Test
    public void statusIsInUse() {
        String sn = generateSn();
        FilterPage filterPage = new BicycleCreation(dashboardPage)
                .createBicycle(sn)
                .duplicateBicycle()
                .returnToDashboard()
                .goToFilterPage()
                .setStatusFilter();
        Assertions.assertTrue(filterPage.isStatusInUse());
    }

    @Test
    public void isFilteredBySn() {
        String sn = generateSn();
        FilterPage filterPage = new BicycleCreation(dashboardPage)
                .createBicycle(sn)
                .returnToDashboard()
                .goToFilterPage()
                .setSerialNumber(sn);
        Assertions.assertTrue(filterPage.isFilterAppliedBySerialNumber(sn));
    }
}

