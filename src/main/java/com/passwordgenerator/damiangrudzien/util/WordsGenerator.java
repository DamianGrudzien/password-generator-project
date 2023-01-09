package com.passwordgenerator.damiangrudzien.util;

import com.passwordgenerator.damiangrudzien.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.passwordgenerator.damiangrudzien.util.NumberGenerator.getRandomNumbers;

@Component
public class WordsGenerator {
    WordService wordService;

    private WordsGenerator() {
        throw new IllegalCallerException();
    }

    @Autowired
    public WordsGenerator(WordService wordService) {
        this.wordService = wordService;
    }

    public List<String> getRandomPass(Integer numberOfWords) {

        List<Integer> generatedNumbers = getRandomNumbers(numberOfWords,
                                                          wordService.findAll()
                                                                     .size());

        List<String> passwords = new ArrayList<>();
        for (Integer integer : generatedNumbers) {
            passwords.add(wordService
                                  .findById((long) integer)
                                  .getWord());
        }

        return passwords;
    }
}
