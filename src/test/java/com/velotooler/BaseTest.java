package com.velotooler;

import com.velotooler.core.driver.DriverFactory;
import com.velotooler.core.parser.JsonParser;
import com.velotooler.core.parser.Parser;
import com.velotooler.pages.DashboardPage;
import com.velotooler.steps.LogIn;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(WatcherExtension.class)
@Slf4j
public abstract class BaseTest {

    protected DashboardPage dashboardPage;
    protected Parser parser = new JsonParser();

    @BeforeEach
    public void logIn(TestInfo testInfo) {
        log.info(String.format("test started: %s ", testInfo.getTestMethod()));
        dashboardPage = new LogIn(DriverFactory.get()).logIn();
    }

    @AfterEach
    public void logOut(TestInfo testInfo) {
        log.info(String.format("test finished: %s ", testInfo.getTestMethod()));
        dashboardPage.logOut();
    }
}
