package com.passwordgenerator.damiangrudzien.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {

	private static final Random RANDOM = new Random();

	private NumberGenerator() {
		throw new IllegalCallerException();
	}

	public static List<Long> getRandomNumbers(long amount, long maxValue) {
		List<Long> randomNumbers = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			randomNumbers.add(RANDOM.nextLong(maxValue));
		}
		return randomNumbers;
	}
}
