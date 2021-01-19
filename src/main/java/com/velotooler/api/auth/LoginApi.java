package com.velotooler.api.auth;

import com.velotooler.core.parser.JsonParser;
import com.velotooler.core.model.Auth;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginApi {

    public String auth() {
        Auth auth = new JsonParser().get("logIn", Auth.class);

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://pilot.velotooler.com/api")
                .setContentType("application/json")
                .build();

        ExtractableResponse<Response> response = RestAssured.given(requestSpecification)
                .when()
                .body(auth)
                .post("/login")
                .then()
                .extract();

        LoginResponse loginResponse = response.as(LoginResponse.class);
        return loginResponse.getToken();
    }

}
