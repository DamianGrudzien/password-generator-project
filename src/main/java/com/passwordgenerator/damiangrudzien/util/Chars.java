package com.passwordgenerator.damiangrudzien.util;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.repository.init.ResourceReader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
@NoArgsConstructor
public class Chars {
	
	@Autowired
	ResourceLoader resourceLoader;

	private static final Logger log = LoggerFactory.getLogger(Chars.class.getName());
	@Value("${chars.properties.file-location}")
	private String configFilePath;

	public Map<String, String> getCharToReplace() {
		Map<String, String> charsToReplace = new HashMap<>();
		InputStream file = null;
		try {
			file = resourceLoader.getResource(configFilePath).getInputStream();
			Properties properties = new Properties();
			properties.load(file);

			properties.forEach((key, value) -> charsToReplace.putIfAbsent(key.toString(), value.toString()));
			return charsToReplace;
		} catch (IOException e) {
			log.info("Exception occured while reading config.properties file!");
			log.info(e.getMessage());
			return Map.of();
		}

	}
}
