package com.velotooler;

import com.velotooler.api.bicycle.CustomerBikeApi;
import com.velotooler.api.bicycle.request.BikeRequest;
import com.velotooler.core.model.Bicycle;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.FilterPage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import com.velotooler.pages.servicebooking.RequestPage;
import com.velotooler.steps.BicycleCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.velotooler.core.util.SNGenerator.generateSn;

public class BicycleTest extends BaseTest {

    private CustomerBikeApi customerBikeApi = new CustomerBikeApi();
    private BikeRequest bicycleApiRequest = new JsonParser().get("customerBike", BikeRequest.class);
    private Bicycle bicycle = new JsonParser().get("bicycle", Bicycle.class);

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
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        BicycleInfoPage bicycleInfoPage = new DashboardPage(driver)
                .goToBicycleInfoPage()
                .duplicateBicycle();
        Assertions.assertEquals(bicycleApiRequest.getMake() + " " + bicycleApiRequest.getModel(), bicycleInfoPage.getBicycleName());
    }

    @Test
    public void bicycleIsDeleted() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        DashboardPage dashboard = new DashboardPage(driver)
                .deleteBicycleBySn(sn);
        Assertions.assertTrue(dashboard.isParticularBicycleExist(sn));
    }

    @Test
    public void deleteAllBicycles() {
        DashboardPage dashboardPage = new DashboardPage(driver)
                .deleteAllBicycles();
        Assertions.assertFalse(dashboardPage.lastBicycleIsDisplayed());
    }

    @Test
    public void statusIsInUse() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        FilterPage filterPage = new DashboardPage(driver)
                .goToBicycleInfoPage()
                .duplicateBicycle()
                .returnToDashboard()
                .goToFilterPage()
                .setStatusFilter();
        Assertions.assertTrue(filterPage.isStatusInUse());
    }

    @Test
    public void isFilteredBySn() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        FilterPage filterPage = new DashboardPage(driver)
                .goToFilterPage()
                .setSerialNumber(sn);
        Assertions.assertTrue(filterPage.isFilterAppliedBySerialNumber(sn));
    }

    @Test
    public void serviceRequestIsCreated() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        String requestId = new DashboardPage(driver)
                .goToCreateRequestPage()
                .createRequest(bicycle.getLocation(), sn)
                .waitLoadingInfo()
                .getRequestId();
        RequestPage requestPage = new RequestPage(driver);
        String requestServiceName = requestPage.getServiceName().split("-")[0].toUpperCase().trim();
        String serviceRequestName = requestPage.goToServiceBookingsPage().getServiceRequestName(requestId).trim();
        Assertions.assertEquals(requestServiceName, serviceRequestName);
    }
}

