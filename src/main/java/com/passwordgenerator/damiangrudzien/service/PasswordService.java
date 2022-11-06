package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.model.PasswordDTO;
import com.passwordgenerator.damiangrudzien.model.PasswordProperties;
import com.passwordgenerator.damiangrudzien.util.Chars;
import com.passwordgenerator.damiangrudzien.util.WordsGenerator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PasswordService {
    WordService wordService;
    WordsGenerator wordsGenerator;

    public PasswordService(
            WordService wordService,
            WordsGenerator wordsGenerator) {
        this.wordService = wordService;
        this.wordsGenerator = wordsGenerator;
    }

    public PasswordDTO getPassword(PasswordProperties pp) {
        List<String> randomWords = wordsGenerator.getRandomPass(pp.getWordAmount());
        Boolean isUpperFirst = pp.getUpperFirst();

        if (Boolean.TRUE.equals(isUpperFirst)){
            randomWords = randomWords.stream()
                    .map(word -> word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase())
                    .collect(Collectors.toList());
        }

        Map<String, String> charsToReplace = Chars.getCharToReplace();
        String password;

        if (pp.getCharAmount() != 0 && !charsToReplace.isEmpty()) {
            password = Arrays.stream(randomWords.toString().split(""))
                  .map(letter -> charsToReplace.getOrDefault(letter, letter))
                  .collect(Collectors.joining());
        } else {
            password = String.join("", randomWords);
        }

        return new PasswordDTO(password);
    }
}
