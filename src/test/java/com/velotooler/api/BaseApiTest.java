package com.velotooler.api;

import com.velotooler.api.util.RequestSpec;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

@Slf4j
public abstract class BaseApiTest {

    protected static RequestSpecification requestSpecification;

    @BeforeAll
    public static void setUp() {
        requestSpecification = RequestSpec.getInstance();
    }

    @BeforeEach
    public void startTest(TestInfo testInfo) {
        log.info(String.format("test started: %s ", testInfo.getTestMethod()));
    }


}
