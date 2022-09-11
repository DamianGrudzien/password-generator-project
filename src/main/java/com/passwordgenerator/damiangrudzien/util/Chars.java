package com.passwordgenerator.damiangrudzien.util;

import com.passwordgenerator.damiangrudzien.model.Decorator.PasswordDecorator;
import com.passwordgenerator.damiangrudzien.model.implementation.PasswordChar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Chars {
    static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    private static final Logger logger = LoggerFactory.getLogger(PasswordChar.class);

    public static Map<String, String> fillChars() {
        Map<String, String> charsToReplace = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            Properties properties = new Properties();
            properties.load(fis);

            properties.forEach((key, value) -> charsToReplace.putIfAbsent(key.toString(), value.toString()));
            return charsToReplace;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return Map.of();
        }

    }
}
