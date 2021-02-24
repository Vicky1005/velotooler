package com.velotooler.api.util;

import com.velotooler.api.auth.LoginApi;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.velotooler.core.service.ReadProperties.getProperty;

public class RequestSpec {

    private static RequestSpecification requestSpecification;

    private static RequestSpecification create() {

        String token = new LoginApi().auth();

        return new RequestSpecBuilder()
                .setBaseUri(getProperty("api", "baseUri"))
                .setAccept("application/json")
                .setContentType("application/json")
                .addFilter(new AllureRestAssured())
                .addHeader("x-token", token)
                .build();
    }

    public static RequestSpecification getInstance() {
        if (requestSpecification == null) {
            requestSpecification = create();
        }
        return requestSpecification;
    }
}
