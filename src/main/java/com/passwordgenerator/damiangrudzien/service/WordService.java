package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.exceptions.NotFoundException;
import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.repository.jpa.WordRepository;
import com.passwordgenerator.damiangrudzien.util.NumberGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

	public Word findById(Long id) {
		log.info("Find by Id started.");
		Optional<Word> wordById = wordRepository.findById(id);

		return wordById.orElseThrow(NotFoundException::new);
	}

	public List<Word> findAll() {
		return wordRepository.findAll();
	}

	public String getRandomWord() {
		long numberOfWords = 1L;
		List<Long> generatedNumbers = getRandomIdsOfWords(numberOfWords);
		return this.findById(generatedNumbers.get(0)).getWord();
	}

	public List<String> getRandomWords(Long numberOfWords) {
		List<Long> generatedNumbers = getRandomIdsOfWords(numberOfWords);
		List<String> wordsFromDB = new ArrayList<>();
		for (Long number : generatedNumbers) {
			String wordFromDB = this.findById(number).getWord();
			wordsFromDB.add(wordFromDB);
		}
		return wordsFromDB;
	}

	private List<Long> getRandomIdsOfWords(Long numberOfWords) {
		long sumOfWords = this.wordRepository.count();
		log.info("All words in db: " + sumOfWords);
		Optional<Word> firstWord = wordRepository.getFirstWord();
		Long firstWordId = firstWord.orElseThrow(NotFoundException::new).getId();
		return NumberGenerator.getRandomNumbers(numberOfWords, sumOfWords, firstWordId);
	}

}
