package com.passwordgenerator.damiangrudzien.util;

import com.passwordgenerator.damiangrudzien.service.WordService;

import java.util.ArrayList;
import java.util.List;

public class WordsGenerator {

    private static WordService wordServiceLocal;

    public static List<String> getRandomPass(WordService wordService, Integer words) {
        wordServiceLocal = wordService;
        List<Integer> listOfNumbers = NumberGenerator.makeRandomNumbers(words, wordServiceLocal.findAll()
                                                                                               .size());

        return getRandomWords(listOfNumbers);
    }

    private static List<String> getRandomWords(List<Integer> integers) {
        List<String> passwords = new ArrayList<>();
        for (Integer integer : integers) {
            passwords.add(wordServiceLocal
                    .findById((long) integer)
                    .map(ToDto::wordAsDto)
                    .orElseThrow(RuntimeException::new)
                    .getWord());
        }
        return passwords;
    }

}
