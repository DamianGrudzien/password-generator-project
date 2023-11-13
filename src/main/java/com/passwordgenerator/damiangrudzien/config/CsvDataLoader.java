package com.passwordgenerator.damiangrudzien.config;

import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.repository.jpa.WordRepository;
import com.passwordgenerator.damiangrudzien.util.CsvReader;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CsvDataLoader implements ApplicationRunner {

	@Autowired
	private CsvReader csvReader;

	@Autowired
	private WordRepository wordRepository;

	@Value("${csv.file.path}")
	private String csvFilePath;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (wordRepository == null) {
			log.error("Word repository is null! Unable to obtain words!");
			return;
		} else if (StringUtils.isEmpty(csvFilePath)) {
			log.error("File Path is null or empty. Unable to read from csv file!");
			return;
		}

		long tableSize = wordRepository.count();
		if (tableSize > 1) {
			return;
		}
		log.info("Table size before: " + tableSize);
		log.info("Start to load words from file");
		List<Word> wordsToSave = csvReader.loadDataFromCsv(csvFilePath);
		log.info("Words loaded");
		List<Word> queryList = new ArrayList<>();
		long wordsSize = wordsToSave.size();
		int counter = 0;
		log.debug("Amount of word to save: " + wordsToSave.size());
		log.info("Saving words to DB.");
		for (int i = 0; ((wordsSize - i) != 0); i++) {
			Word word = wordsToSave.get(i);
			queryList.add(word);
			counter++;
			if (counter == 500) {
				logInformation(wordsToSave, queryList, i);
				saveWordsInDB(queryList);
				counter = 0;
				queryList = new ArrayList<>();
			} else if (((wordsToSave.size() - i) <= counter)) {
				saveWordsInDB(queryList);
				break;
			}
		}
		log.info("Table size after: " + wordRepository.count());
		log.info("Saving ended!");
	}

	private static void logInformation(List<Word> wordsToSave, List<Word> queryList, int i) {
		log.debug("About to save words to DB");
		log.debug("Words to save is: " + wordsToSave.size());
		log.debug("I value: " + i);
		log.debug("Difference is: " + (wordsToSave.size() - i));
		log.debug("QueryList size: " + queryList.size());
	}

	private void saveWordsInDB(List<Word> queryList) {
		try {
			wordRepository.saveAll(queryList);
			log.debug("Saved");
		} catch (Exception e) {
			log.error("Error: " + e.getMessage());
		}
	}
}