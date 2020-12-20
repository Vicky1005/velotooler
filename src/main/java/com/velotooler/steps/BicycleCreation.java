package com.velotooler.steps;

import com.velotooler.core.parser.XMLParser;
import com.velotooler.pages.BicycleInfoPage;
import com.velotooler.pages.DashboardPage;

public class BicycleCreation {

    private DashboardPage dashboardPage;
    private XMLParser xmlParser;

    public BicycleCreation(DashboardPage dashboardPage) {
        this.dashboardPage = dashboardPage;
        this.xmlParser = new XMLParser();
    }

    public BicycleInfoPage createBicycle(String serialNumber) {
        return dashboardPage.goToAddBikePage()
                .addBike(serialNumber, xmlParser.parseBicycleData("brand"),
                        xmlParser.parseBicycleData("model"),
                        xmlParser.parseBicycleData("year"),
                        xmlParser.parseBicycleData("location"))
                .addBicycleDescription()
                .addBicycleComponents()
                .goToBicycleReviewPage()
                .goToBicycleAgreementPages()
                .agree();
    }
}
