package com.velotooler.bdd.steps;

import com.velotooler.core.driver.DriverFactory;
import com.velotooler.core.model.Bicycle;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.servicebooking.RequestPage;
import com.velotooler.steps.BicycleCreation;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class UserServiceRequestSteps {
    private static String requestId;

    @When("the user create service request for bicycle {string}")
    public void createRequest(String sn) {
        Bicycle bicycle = new JsonParser().get("bicycle", Bicycle.class);
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.get());
        new BicycleCreation(dashboardPage).createBicycle(sn).returnToDashboard();
        requestId = new DashboardPage(DriverFactory.get())
                .goToCreateRequestPage()
                .createRequest(bicycle.getLocation(), sn)
                .waitLoadingInfo()
                .getRequestId();
    }

    @Then("service request was created")
    public void verifyServiceRequestIsCreated() {
        RequestPage requestPage = new RequestPage(DriverFactory.get());
        String requestServiceName = requestPage.getServiceName().split("-")[0].toUpperCase().trim();
        String serviceRequestName = requestPage.goToServiceBookingsPage().getServiceRequestName(requestId).trim();
        Assertions.assertEquals(requestServiceName, serviceRequestName);
    }
}
