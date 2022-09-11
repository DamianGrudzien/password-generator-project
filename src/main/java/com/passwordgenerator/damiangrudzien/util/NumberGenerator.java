package com.passwordgenerator.damiangrudzien.util;

import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.service.WordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {

    public static List<Integer> makeRandomNumbers(int amount, int maxValue) {
        Random random = new Random();
        List<Integer> randomNumbers = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            randomNumbers.add(getRandom(random, maxValue));
        }
        return randomNumbers;
    }

    public static int getRandom(Random random, int maxValue) {
        return random.nextInt(maxValue);
    }
}
