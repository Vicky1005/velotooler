package com.velotooler.core.util;

public class SNGenerator {

    public static String generateSn() {
        return String.valueOf(System.currentTimeMillis());
    }
}
