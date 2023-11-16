package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.exceptions.NotFoundException;
import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.repository.jpa.WordRepository;
import com.passwordgenerator.damiangrudzien.util.NumberGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class WordService {

	private WordRepository wordRepository;
	private ModelMapper modelMapper;
	private NumberGenerator numberGenerator;
	private CacheManager cacheManager;

//	@Cacheable(
//			value = "Word",
//			key = "#id",
//			unless = "#Word == null"
//	)
	@Cacheable(key = "#id", value = "WORD")
	public Word findById(Long id) {
		log.info("Find by Id started.");
		Optional<Word> wordById = wordRepository.findById(id);

		return wordById.orElseThrow(NotFoundException::new);
	}

	@Cacheable(
			value = "Word"
//			unless = "#result == null"
	)
	public List<Word> findAll() {
		return wordRepository.findAll();
	}

	public String getRandomWord() {
		Long numberOfWords = 1L;
		long sumOfWords = this.wordRepository.count();
		log.info("All words in db: " + sumOfWords);
		List<Long> generatedNumbers = numberGenerator.getRandomNumbers(numberOfWords, sumOfWords);

		return this.findById(generatedNumbers.get(0)).getWord();
	}


	public List<String> getRandomWords(Long numberOfWords) {
		long sumOfWords = this.wordRepository.count();
		log.info("All words in db: " + sumOfWords);
		List<Long> generatedNumbers = numberGenerator.getRandomNumbers(numberOfWords, sumOfWords);
		List<String> wordsFromDB = new ArrayList<>();
		for (Long number : generatedNumbers) {
			String wordFromDB = this.findById(number).getWord();
			wordsFromDB.add(wordFromDB);
		}
		return wordsFromDB;
	}

	public void flushCache() {
		for (String cacheName : cacheManager.getCacheNames()) {
			try {
				cacheManager.getCache(cacheName).clear();
				log.info("Flushing cache with name: " + cacheName);
			} catch (NullPointerException e) {
				log.info("Error: " + e.getMessage());
			}
		}
	}
}
