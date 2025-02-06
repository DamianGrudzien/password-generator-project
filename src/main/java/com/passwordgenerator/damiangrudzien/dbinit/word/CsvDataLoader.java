package com.passwordgenerator.damiangrudzien.dbinit.word;

import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.repository.jpa.WordRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CsvDataLoader {

	private static final int BATCH_SIZE = 10000;
	private CsvReader csvReader;
	private final WordRepository wordRepository;
	@Value("${csv.file.path}")
	private String csvFilePath;

	public CsvDataLoader(CsvReader csvReader, WordRepository wordRepository) {
		this.csvReader = csvReader;
		this.wordRepository = wordRepository;
	}

	@PostConstruct
	public void run() {

		List<Word> wordsToSave = csvReader.loadDataFromCsv(csvFilePath);
		log.info("Words loaded");
		long count = wordRepository.count();
		if (wordsToSave.size() <= count) {
			log.info("Skipping save to db.");
			return;
		}
		List<Word> queryList = new ArrayList<>();
		long wordsSize = wordsToSave.size();
		int counter = 0;
		log.info("About to save words to DB. Amount of words: {}", wordsSize);
		for (int i = 0; ((wordsSize - i) != 0); i++) {
			Word word = wordsToSave.get(i);
			queryList.add(word);
			counter++;
			if (counter == BATCH_SIZE) {
				saveWordsInDB(queryList);
				counter = 0;
				queryList = new ArrayList<>();
				log.info("Saving Words to DB");
			} else if (((wordsToSave.size() - i) <= counter)) {
				log.info("Saving Last Words to DB");
				saveWordsInDB(queryList);
				break;
			}
		}
		log.info("Saving ended!");
	}

	private void saveWordsInDB(List<Word> queryList) {
		try {
			wordRepository.saveAll(queryList);
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}
	}
}