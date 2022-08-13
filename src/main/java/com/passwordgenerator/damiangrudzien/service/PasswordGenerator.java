package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.ToDto;

import java.util.Random;

public class PasswordGenerator {
    public static String getRandomPass(WordService wordService) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        int firstRandom = random.nextInt(wordService.findAll().size());
        int secondRandom = random.nextInt(wordService.findAll().size());
        password.append(wordService.findById((long) firstRandom).map(ToDto::wordAsDto).orElseThrow(RuntimeException::new).getWord());
        password.append(wordService.findById((long) secondRandom).map(ToDto::wordAsDto).orElseThrow(RuntimeException::new).getWord());
        return password.toString();
    }
}
