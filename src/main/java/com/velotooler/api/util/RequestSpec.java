package com.velotooler.api.util;

import com.velotooler.api.auth.LoginApi;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    public final static String BASE_URL = "https://pilot.velotooler.com/api";

    private static RequestSpecification requestSpecification;

    private static RequestSpecification create() {

        String token = new LoginApi().auth();

        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setAccept("application/json")
                .setContentType("application/json")
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
