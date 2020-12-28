package com.velotooler.core.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.velotooler.core.exception.ReadingFromFileException;

import java.io.File;
import java.io.IOException;

public class JsonParser implements Parser {

    private ObjectMapper objectMapper;

    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public <T> T get(String path, Class<T> clazz) {
        File file = new File(String.format("src/main/resources/%s.json", path));
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new ReadingFromFileException();
        }
    }
}
