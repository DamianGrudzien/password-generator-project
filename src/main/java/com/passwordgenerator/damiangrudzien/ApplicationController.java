package com.passwordgenerator.damiangrudzien;

import com.passwordgenerator.damiangrudzien.DTO.WordDto;
import com.passwordgenerator.damiangrudzien.model.implementation.PasswordChar;
import com.passwordgenerator.damiangrudzien.model.implementation.PasswordImpl;
import com.passwordgenerator.damiangrudzien.service.WordService;
import com.passwordgenerator.damiangrudzien.util.ToDto;
import com.passwordgenerator.damiangrudzien.util.WordsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    @Autowired
    WordService wordService;

    @GetMapping("/")
    @ResponseBody
    public String getRoot() {
        return "root";
    }

    @GetMapping("/passwords/{id}")
    public WordDto getPassword(@PathVariable("id") Long id) {
        return wordService.findById(id)
                          .map(ToDto::wordAsDto)
                          .orElseThrow(RuntimeException::new);
    }

    @GetMapping("/passwords/randomWord")
    public String getRandomWord() {
        PasswordImpl password = PasswordImpl.builder()
                                            .charAmount(0)
                                            .wordAmount(1)
                                            .upperFirst(true)
                                            .build();

        password.setWords(WordsGenerator.getRandomPass(wordService, password.getWordAmount()));

        return (new PasswordChar(password)).toString();
    }

    @GetMapping("/passwords/random/{words}")
    public String getRandomWords(@PathVariable("words") Integer words) {
        PasswordImpl password = PasswordImpl.builder()
                                            .charAmount(4)
                                            .wordAmount(words)
                                            .upperFirst(true)
                                            .build();

        password.setWords(WordsGenerator.getRandomPass(wordService, password.getWordAmount()));
        PasswordChar alteredPassword = new PasswordChar(password);
        alteredPassword.alterPassword();

        return alteredPassword.toString();
    }
//
//    @GetMapping("/passwords/random/")
//    public String getRandomWords(@RequestParam("words") Integer words, @RequestParam("chars") Integer chars, @RequestParam("numbers") Integer numbers , @RequestParam("upperFirst") boolean upperFirst){
//        return WordsGenerator.getRandomPass(wordService, words);
//    }


    @PostMapping("/")
    @ResponseBody
    public String postRoot(@RequestBody String root) {
        return "post-" + root;
    }


}
