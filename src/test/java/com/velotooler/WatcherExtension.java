package com.velotooler;

import com.velotooler.core.driver.DriverFactory;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WatcherExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback,
        BeforeEachCallback, AfterEachCallback {

    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        DriverFactory.init();
        driver = DriverFactory.get();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://pilot.velotooler.com/");
    }

    // call login

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        // after login
    }

    // do test

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        // before logout
        Method testMethod = extensionContext.getRequiredTestMethod();
        boolean testFailed = extensionContext.getExecutionException().isPresent();
        if (testFailed) {
            takeScreenshot(testMethod.getName());
            log.error("Test " + testMethod.getName() + " failed");
        } else {
            log.info("Test " + testMethod.getName() + " was successfully executed");
        }
    }

    // call logout

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        driver.close();
        driver = null;
    }

    @SneakyThrows
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenshot(String testName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) DriverFactory.get();
        File screenCapture = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            String fileName = String.format(".//target/screenshots/%s_failure_%s.png", testName, getCurrentTimeAsString());
            File file = new File(fileName);
            FileUtils.copyFile(screenCapture, file);
            log.info("Screenshot {} was saved", fileName);
        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
        return FileUtils.readFileToByteArray(screenCapture);
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

}
