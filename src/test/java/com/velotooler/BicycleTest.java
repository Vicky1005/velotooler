package com.velotooler;

import com.velotooler.api.bicycle.CustomerBikeApi;
import com.velotooler.api.bicycle.request.BikeRequest;
import com.velotooler.core.driver.DriverFactory;
import com.velotooler.core.model.Bicycle;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.FilterPage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import com.velotooler.pages.servicebooking.RequestPage;
import com.velotooler.steps.BicycleCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.velotooler.core.util.SNGenerator.generateSn;

public class BicycleTest extends BaseTest {

    private CustomerBikeApi customerBikeApi = new CustomerBikeApi();
    private BikeRequest bicycleApiRequest = new JsonParser().get("customerBike", BikeRequest.class);
    private Bicycle bicycle = new JsonParser().get("bicycle", Bicycle.class);

    @Test
    @Tag("smoke")
    public void bicycleIsCreated() {
        String sn = generateSn();
        String createdSerialNumber = new BicycleCreation(dashboardPage)
                .createBicycle(sn)
                .getSnNumber();
        Assertions.assertEquals(sn, createdSerialNumber);
    }

    @Test
    @Tag("regression")
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
    public void bicycleIsDeleted() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        DashboardPage dashboard = new DashboardPage(DriverFactory.get())
                .deleteBicycleBySn(sn);
        Assertions.assertTrue(dashboard.isParticularBicycleExist(sn));
    }

    @Test
    @Tag("regression")
    public void deleteAllBicycles() {
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.get())
                .deleteAllBicycles();
        Assertions.assertFalse(dashboardPage.lastBicycleIsDisplayed());
    }

    @Test
    @Tag("regression")
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
    public void isFilteredBySn() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        FilterPage filterPage = new DashboardPage(DriverFactory.get())
                .goToFilterPage()
                .setSerialNumber(sn);
        Assertions.assertTrue(filterPage.isFilterAppliedBySerialNumber(sn));
    }

    @Test
    @Tag("smoke")
    public void serviceRequestIsCreated() {
        String sn = generateSn();
        customerBikeApi.createBicycle(sn);
        String requestId = new DashboardPage(DriverFactory.get())
                .goToCreateRequestPage()
                .createRequest(bicycle.getLocation(), sn)
                .waitLoadingInfo()
                .getRequestId();
        RequestPage requestPage = new RequestPage(DriverFactory.get());
        String requestServiceName = requestPage.getServiceName().split("-")[0].toUpperCase().trim();
        String serviceRequestName = requestPage.goToServiceBookingsPage().getServiceRequestName(requestId).trim();
        Assertions.assertEquals(requestServiceName, serviceRequestName);
    }
}

