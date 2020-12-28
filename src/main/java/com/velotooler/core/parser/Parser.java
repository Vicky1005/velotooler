package com.velotooler.core.parser;

public interface Parser {
    <T> T get(String path, Class<T> clazz);
}
