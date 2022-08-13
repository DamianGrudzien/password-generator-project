package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.ToDto;

import java.util.*;

public class PasswordGenerator {

    private static WordService wordServiceLocal;

    public static String getRandomPass(WordService wordService) {
        wordServiceLocal = wordService;
        return getRandomWord(makeRandomNumbers(2));
    }

    private static String getRandomWord(List<Integer> integers) {
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < integers.size(); i++){
            password.append(wordServiceLocal
                    .findById((long) integers.get(i))
                    .map(ToDto::wordAsDto)
                    .orElseThrow(RuntimeException::new)
                    .getWord());
        }
        return password.toString();
    }

    private static List<Integer> makeRandomNumbers(int amount) {
        Random random = new Random();
        List<Integer> randomNumbers = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            randomNumbers.add(getRandom(random));
        }
        return randomNumbers;
    }

    private static int getRandom( Random random) {
        return random.nextInt(wordServiceLocal.findAll().size());
    }
}
