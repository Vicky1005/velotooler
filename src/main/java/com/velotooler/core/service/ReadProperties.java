package com.velotooler.core.service;

import com.velotooler.core.exception.ReadingFromFileException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.function.Consumer;

@Slf4j
public class ReadProperties {

    private static Properties properties = new Properties();

    public static String getProperty(String propertyFile, String propertyKey) {

        String propertyFilePath = String.format("src/main/resources/%s.properties", propertyFile);

        try {
            FileInputStream inStream = new FileInputStream(propertyFilePath);
            properties.load(inStream);
            inStream.close();
            return properties.getProperty(propertyKey);
        } catch (IOException e) {
            log.error("Exception occur while reading from file " + propertyFilePath + " Throw exception " + e.getMessage());
            throw new ReadingFromFileException(e);
        }
    }
}
