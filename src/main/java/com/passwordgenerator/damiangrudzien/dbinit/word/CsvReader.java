package com.passwordgenerator.damiangrudzien.dbinit.word;

import com.passwordgenerator.damiangrudzien.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
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
		try (InputStream resourceAsStream = CsvReader.class.getClassLoader().getResourceAsStream(csvFilePath)) {
			if (resourceAsStream == null) {
				return new ArrayList<>();
			}
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
			log.debug("The last word in csv file: {}", last);
			return words;
		} catch (IOException e) {
			log.error("Error while reading file: {}", e.getMessage());
			return new ArrayList<>();
		} finally {
			log.debug("Input stream closed successfully");
		}
	}
}
