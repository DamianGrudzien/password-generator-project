package com.passwordgenerator.damiangrudzien.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Chars {
    static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    private Chars() {
        throw new IllegalCallerException();
    }

    public static Map<String, String> getCharToReplace() {
        Map<String, String> charsToReplace = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(fis);

            properties.forEach((key, value) -> charsToReplace.putIfAbsent(key.toString(), value.toString()));
            return charsToReplace;
        } catch (IOException e) {
            return Map.of();
        }

    }
}
