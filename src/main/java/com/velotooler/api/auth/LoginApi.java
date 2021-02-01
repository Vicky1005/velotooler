package com.velotooler.api.auth;

import com.velotooler.core.model.Auth;
import com.velotooler.core.service.UserCreator;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static com.velotooler.core.service.ReadProperties.getProperty;

@Slf4j
public class LoginApi {

    public String auth() {

        Auth auth = UserCreator.withCredentialsFromProperty();

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getProperty("api", "baseUri"))
                .setContentType("application/json")
                .build();

        ExtractableResponse<Response> response = RestAssured.given(requestSpecification)
                .when()
                .body(auth)
                .post(getProperty("api", "loginEndpoint"))
                .then()
                .extract();

        LoginResponse loginResponse = response.as(LoginResponse.class);
        log.info("Api login with token is successful with user " + auth.getUsername());
        return loginResponse.getToken();
    }

}
