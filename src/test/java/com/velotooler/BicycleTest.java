package com.velotooler;

import com.velotooler.core.parser.XMLParser;
import com.velotooler.pages.BicycleInfoPage;
import com.velotooler.steps.BicycleCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.velotooler.core.util.SNGenerator.generateSn;

public class BicycleTest extends BaseTest {

    private XMLParser xmlParser = new XMLParser();

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
        BicycleInfoPage bicycleInfoPage = new BicycleCreation(dashboardPage)
                .createBicycle(sn)
                .duplicateBicycle()
                .returnToDashboard()
                .goToBicycleInfoPage();
        String expectedBicycleName = xmlParser.parseBicycleData("brand") + " " + xmlParser.parseBicycleData("model");
        Assertions.assertEquals(expectedBicycleName, bicycleInfoPage.getBicycleName());
    }
}
