package com.velotooler.steps;

import com.velotooler.core.model.Bicycle;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.core.parser.Parser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.pages.bicycle.info.BicycleInfoPage;

public class BicycleCreation {

    private DashboardPage dashboardPage;
    private Parser parser;

    public BicycleCreation(DashboardPage dashboardPage) {
        this.dashboardPage = dashboardPage;
        this.parser = new JsonParser();
    }

    public BicycleInfoPage createBicycle(String serialNumber) {
        Bicycle bicycle = parser.get("bicycle", Bicycle.class);
        return dashboardPage.goToAddBikePage()
                .addBike(serialNumber, bicycle.getBrand(),
                        bicycle.getModel(),
                        bicycle.getYear(),
                        bicycle.getLocation())
                .addBicycleDescription()
                .addBicycleComponents()
                .goToBicycleReviewPage()
                .goToBicycleAgreementPages()
                .agree();
    }
}
