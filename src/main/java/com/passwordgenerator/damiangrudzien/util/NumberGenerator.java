package com.passwordgenerator.damiangrudzien.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {
    private static final Random RANDOM = new Random();

    private NumberGenerator() {
        throw new IllegalCallerException();
    }

    public static List<Integer> makeRandomNumbers(int amount, int maxValue) {

        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            randomNumbers.add(getRandom(RANDOM, maxValue));
        }
        return randomNumbers;
    }

    public static int getRandom(Random random, int maxValue) {
        return random.nextInt(maxValue);
    }
}
