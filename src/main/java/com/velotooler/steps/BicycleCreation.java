package com.velotooler.steps;

import com.velotooler.core.model.Bicycle;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.core.parser.Parser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BicycleCreation {

    private DashboardPage dashboardPage;
    private Parser parser;

    public BicycleCreation(DashboardPage dashboardPage) {
        this.dashboardPage = dashboardPage;
        this.parser = new JsonParser();
    }

    public BicycleInfoPage createBicycle(String serialNumber) {
        Bicycle bicycle = parser.get("bicycle", Bicycle.class);
        BicycleInfoPage bicycleInfoPage = dashboardPage.goToAddBikePage()
                .addBike(serialNumber, bicycle.getBrand(),
                        bicycle.getModel(),
                        bicycle.getYear(),
                        bicycle.getLocation())
                .addBicycleDescription()
                .addBicycleComponents()
                .goToBicycleReviewPage()
                .goToBicycleAgreementPages()
                .agree();

        log.info("Bicycle with serial number " + serialNumber + " is created");
        return bicycleInfoPage;
    }
}
