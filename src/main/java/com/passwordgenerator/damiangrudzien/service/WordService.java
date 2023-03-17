package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.exceptions.NotFoundException;
import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.model.dto.WordDto;
import com.passwordgenerator.damiangrudzien.repository.WordRepository;
import com.passwordgenerator.damiangrudzien.util.ToDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.passwordgenerator.damiangrudzien.util.NumberGenerator.getRandomNumbers;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class WordService {

	private WordRepository wordRepository;

	public WordDto findById(Long id) {
		Optional<Word> wordById = wordRepository.findById(id);
		if (wordById.isEmpty()) {
			throw new NotFoundException();
		}
		return wordById.map(ToDto::wordAsDto)
				.orElseThrow(NotFoundException::new);
	}

	public List<Word> findAll() {
		return wordRepository.findAll();
	}

	public String getRandomWord() {
		return this.getRandomPass(1L).get(0);
	}

	public List<String> getRandomPass(Long numberOfWords) {
		List<Long> generatedNumbers = getRandomNumbers(numberOfWords, this.wordRepository.count());

		List<String> passwords = new ArrayList<>();
		for (Long aLong : generatedNumbers) {
			passwords.add(this.findById(aLong).getWord());
		}

		return passwords;
	}
}
