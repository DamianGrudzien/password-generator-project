package com.passwordgenerator.damiangrudzien.util;

import com.passwordgenerator.damiangrudzien.exceptions.NotFoundException;
import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.repository.jpa.WordRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@NoArgsConstructor
@Slf4j
public class NumberGenerator {

	@Autowired
	private WordRepository wordRepository;

	private final SecureRandom RANDOM = new SecureRandom();


	public List<Long> getRandomNumbers(long amount, long maxValue) {
		Optional<Word> firstWord = wordRepository.getFirstWord();
		Word word = firstWord.orElseThrow(NotFoundException::new);
		Long startPosition = word.getId();
		log.info("Starting position is: " + startPosition);
		List<Long> randomNumbers = new ArrayList<>();
		for (long i = startPosition; i < amount + startPosition; i++) {
			long nextRandom = RANDOM.nextLong(startPosition, maxValue + startPosition);
			log.info("Next random: {}", nextRandom);
			randomNumbers.add(nextRandom);

		}
		return randomNumbers;
	}
}
