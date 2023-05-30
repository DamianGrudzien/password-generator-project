package com.passwordgenerator.damiangrudzien.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class NumberGenerator {

	private final SecureRandom RANDOM = new SecureRandom();


	public List<Long> getRandomNumbers(long amount, long maxValue) {
		List<Long> randomNumbers = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			randomNumbers.add(RANDOM.nextLong(maxValue));
		}
		return randomNumbers;
	}
}
