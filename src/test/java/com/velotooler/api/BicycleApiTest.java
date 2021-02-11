package com.velotooler.api;

import com.velotooler.api.bicycle.customerBike.CustomerBikeRequest;
import com.velotooler.api.bicycle.customerBike.CustomerBikeResponse;
import com.velotooler.api.profile.currentProfile.CurrentProfileResponse;
import com.velotooler.core.parser.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.velotooler.core.service.ReadProperties.getProperty;
import static com.velotooler.core.util.SNGenerator.generateSn;

public class BicycleApiTest extends BaseApiTest {

    @Test
    @Tag("api")
    public void bicycleIsCreated() {
        String sn = generateSn();
        CustomerBikeRequest bike = new JsonParser().get("customerBike", CustomerBikeRequest.class);
        bike.setSerialNumber(sn);
        CustomerBikeResponse response = RestAssured.given(requestSpecification)
                .body(bike)
                .post(getProperty("api", "customerBikeEndpoint"))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(CustomerBikeResponse.class);
        Assertions.assertEquals(sn, response.getSerialNumber());
    }

    @Test
    @Tag("api")
    public void userIsActivated() {
        CurrentProfileResponse response = RestAssured.given(requestSpecification)
                .get(getProperty("api", "currentProfileEndpoint"))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(CurrentProfileResponse.class);
        Assertions.assertTrue(response.isActivated());
    }

    @Test
    @Tag("api")
    public void checkResponseHeader() {
        Response response = RestAssured.given(requestSpecification)
                .get(getProperty("api", "currentProfileEndpoint"))
                .andReturn();
        String header = response.getHeader("Content-Type");
        Assertions.assertEquals("application/json", header);
    }

    @Test
    @Tag("api")
    public void checkStatusCode() {
        Response response = RestAssured.given(requestSpecification)
                .get(getProperty("api", "currentProfileEndpoint"))
                .andReturn();
        Assertions.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Tag("api")
    public void checkResponseBody() {
        CurrentProfileResponse response = RestAssured.given(requestSpecification)
                .get(getProperty("api", "currentProfileEndpoint"))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(CurrentProfileResponse.class);
        Assertions.assertEquals(response.getLocations().size(), 1);
    }
}
