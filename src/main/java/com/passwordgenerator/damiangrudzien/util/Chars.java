package com.passwordgenerator.damiangrudzien.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Chars {

	private static final Logger log = LoggerFactory.getLogger(Chars.class.getName());
	@Value("${chars.properties.file-location}")
	private static String configFilePath;

	private Chars() throws IllegalAccessException {
		throw new IllegalAccessException();
	}

	public static Map<String, String> getCharToReplace(ResourceLoader resourceLoader) {
		Map<String, String> charsToReplace = new HashMap<>();
		InputStream file = null;
		log.info("Config file path: {}",configFilePath);
		try {
			file = resourceLoader.getResource(configFilePath).getInputStream();
			Properties properties = new Properties();
			properties.load(file);

			properties.forEach((key, value) -> charsToReplace.putIfAbsent(key.toString(), value.toString()));
			return charsToReplace;
		} catch (IOException e) {
			log.info("Exception occurred while reading config.properties file!");
			log.info(e.getMessage());
			return Map.of();
		}

	}
}
