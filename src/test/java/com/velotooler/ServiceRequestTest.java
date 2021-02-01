package com.velotooler;

import com.velotooler.api.bicycle.CustomerBikeApi;
import com.velotooler.core.driver.DriverFactory;
import com.velotooler.core.model.Bicycle;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.servicebooking.RequestPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.velotooler.core.util.SNGenerator.generateSn;

public class ServiceRequestTest extends BaseTest {

    private CustomerBikeApi customerBikeApi = new CustomerBikeApi();
    private Bicycle bicycle = new JsonParser().get("bicycle", Bicycle.class);

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
