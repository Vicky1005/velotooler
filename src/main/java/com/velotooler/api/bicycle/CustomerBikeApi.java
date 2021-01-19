package com.velotooler.api.bicycle;

import com.velotooler.api.bicycle.request.BikeRequest;
import com.velotooler.api.util.RequestSpec;
import com.velotooler.core.parser.JsonParser;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class CustomerBikeApi {

    private RequestSpecification requestSpecification = RequestSpec.getInstance();

    public void createBicycle(String sn) {
        BikeRequest bike = new JsonParser().get("customerBike", BikeRequest.class);
        bike.setSerialNumber(sn);
        RestAssured.given(requestSpecification)
                .body(bike)
                .post("/customer-bike")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
