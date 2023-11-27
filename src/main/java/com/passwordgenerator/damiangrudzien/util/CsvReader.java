package com.passwordgenerator.damiangrudzien.util;

import com.passwordgenerator.damiangrudzien.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CsvReader {

	public List<Word> loadDataFromCsv(String csvFilePath) {
		List<Word> words = new ArrayList<>();
		log.info("Start loading data from csv");
		try (InputStream resourceAsStream = CsvReader.class.getClassLoader().getResourceAsStream(csvFilePath)){
			InputStreamReader iSR = new InputStreamReader(resourceAsStream);
			BufferedReader br = new BufferedReader(iSR);
			String line;
			String last = "";
			while ((line = br.readLine()) != null) {
				Word wordToSave = new Word();
				wordToSave.setWord(line);
				words.add(wordToSave);
				last = line;
			}
			log.info("The last word in repository: " + last);
			return words;
		} catch (IOException e) {
			log.info("Error while reading file: " + e.getMessage());
//			throw new RuntimeException(e);
			return List.of();
		} finally {
			log.info("Input stream closed successfully");
		}
	}
}
