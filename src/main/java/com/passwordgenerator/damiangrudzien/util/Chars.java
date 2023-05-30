package com.passwordgenerator.damiangrudzien.util;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
@NoArgsConstructor
public class Chars {

	private static final Logger log = LoggerFactory.getLogger(Chars.class.getName());
	@Value("${chars.properties.file-location}")
	private String configFilePath;

	public Map<String, String> getCharToReplace() {
		Map<String, String> charsToReplace = new HashMap<>();

		try (FileInputStream fis = new FileInputStream(configFilePath)) {
			Properties properties = new Properties();
			properties.load(fis);

			properties.forEach((key, value) -> charsToReplace.putIfAbsent(key.toString(), value.toString()));
			return charsToReplace;
		} catch (IOException e) {
			log.info("Exception!");
			log.info(e.getMessage());
			return Map.of();
		}

	}
}
