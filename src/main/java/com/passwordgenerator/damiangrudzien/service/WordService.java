package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.exceptions.NotFoundException;
import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.model.dto.WordDto;
import com.passwordgenerator.damiangrudzien.repository.WordRepository;
import com.passwordgenerator.damiangrudzien.util.NumberGenerator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class WordService {

	private WordRepository wordRepository;
	private ModelMapper modelMapper;
	private NumberGenerator numberGenerator;

	public WordDto findById(Long id) {
		Optional<Word> wordById = wordRepository.findById(id);
		if (wordById.isEmpty()) {
			throw new NotFoundException();
		}
		return wordById.map(word -> modelMapper.map(word, WordDto.class))
				.orElseThrow(NotFoundException::new);
	}

	public List<Word> findAll() {
		return wordRepository.findAll();
	}

	public String getRandomWord() {
		Long numberOfWords = 1L;
		List<Long> generatedNumbers = numberGenerator.getRandomNumbers(numberOfWords, this.wordRepository.count());

		return this.findById(generatedNumbers.get(0)).getWord();
	}

	public List<String> getRandomWords(Long numberOfWords) {
		List<Long> generatedNumbers = numberGenerator.getRandomNumbers(numberOfWords, this.wordRepository.count());
		List<String> wordsFromDB = new ArrayList<>();
		for (Long number : generatedNumbers) {
			String wordFromDB = this.findById(number).getWord();
			wordsFromDB.add(wordFromDB);
		}
		return wordsFromDB;
	}
}
