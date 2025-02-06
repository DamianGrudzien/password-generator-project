package com.passwordgenerator.damiangrudzien.util;

import lombok.extern.slf4j.Slf4j;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NumberGenerator {

	private static final SecureRandom RANDOM = new SecureRandom();

	private NumberGenerator() throws IllegalAccessException {
		throw new IllegalAccessException();
	}

	public static List<Long> getRandomNumbers(long amount, long maxValue, long minValue) {
		log.info("Starting position is: " + minValue);
		List<Long> randomNumbers = new ArrayList<>();
		for (long i = minValue; i < amount + minValue; i++) {
			long nextRandom = RANDOM.nextLong(minValue, maxValue + minValue);
			log.info("Next random: {}", nextRandom);
			randomNumbers.add(nextRandom);

		}
		return randomNumbers;
	}
}
