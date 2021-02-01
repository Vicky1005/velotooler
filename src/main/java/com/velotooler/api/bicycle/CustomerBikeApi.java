package com.velotooler.api.bicycle;

import com.velotooler.api.bicycle.request.BikeRequest;
import com.velotooler.api.util.RequestSpec;
import com.velotooler.core.parser.JsonParser;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import static com.velotooler.core.service.ReadProperties.getProperty;

@Slf4j
public class CustomerBikeApi {

    private RequestSpecification requestSpecification = RequestSpec.getInstance();

    public void createBicycle(String sn) {
        BikeRequest bike = new JsonParser().get("customerBike", BikeRequest.class);
        bike.setSerialNumber(sn);
        RestAssured.given(requestSpecification)
                .body(bike)
                .post(getProperty("api", "customerBikeEndpoint"))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
        log.info("Bicycle with serial number " + sn + " via api is created");
    }
}
