package com.velotooler.core.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.velotooler.core.exception.ReadingFromFileException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonParser implements Parser {

    private ObjectMapper objectMapper;

    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T get(String path, Class<T> clazz) {
        File file = new File(String.format("src/main/resources/%s.json", path));
        try {

            T result = objectMapper.readValue(file, clazz);
            log.trace("File " + path + "has parsed. Result: " + result.toString());
            return result;
        } catch (IOException e) {
            log.error("File " + path + " can not be parsed. Throw exception " + e.getMessage());
            throw new ReadingFromFileException(e);
        }
    }
}
