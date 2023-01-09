package com.passwordgenerator.damiangrudzien.controller;

import com.passwordgenerator.damiangrudzien.model.dto.WordDto;
import com.passwordgenerator.damiangrudzien.service.WordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/word")
public class WordController {

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    WordService wordService;

    @GetMapping("/{id}")
    public WordDto getPassword(@PathVariable("id") Long id) {
        return wordService.findById(id);
    }

    @GetMapping("/random/")
    public String getRandomWord() {
        return "random word";
    }

    @GetMapping("/random/{words}")
    public String getRandomWords(@PathVariable("words") Integer words) {
        return "list of words";
    }

}
