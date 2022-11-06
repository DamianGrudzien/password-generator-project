package com.passwordgenerator.damiangrudzien;

import com.passwordgenerator.damiangrudzien.dto.ErrorDTO;
import com.passwordgenerator.damiangrudzien.dto.WordDto;
import com.passwordgenerator.damiangrudzien.exceptions.BusinessException;
import com.passwordgenerator.damiangrudzien.exceptions.NotFoundException;
import com.passwordgenerator.damiangrudzien.model.PasswordDTO;
import com.passwordgenerator.damiangrudzien.model.PasswordProperties;
import com.passwordgenerator.damiangrudzien.service.PasswordService;
import com.passwordgenerator.damiangrudzien.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {
    WordService wordService;
    PasswordService passwordService;



    public ApplicationController(
            WordService wordService,
            PasswordService passwordService) {
        this.wordService = wordService;
        this.passwordService = passwordService;
    }


    @GetMapping("/")
    @ResponseBody
    public String getRoot() {
        return "root";
    }

    @GetMapping("/passwords/{id}")
    public WordDto getPassword(@PathVariable("id") Long id) {
        return wordService.findById(id);
    }

    @GetMapping("/passwords/randomWord")
    public String getRandomWord() {
        return "random word";
    }

    @GetMapping("/passwords/random/{words}")
    public String getRandomWords(@PathVariable("words") Integer words) {
        return "list of words";
    }

    @GetMapping("/passwords/random/words-test")
    public PasswordDTO getRandomWordsTest(@RequestBody PasswordProperties pp){
        return passwordService.getPassword(pp);
    }

    @GetMapping("/passwords/random/")
    public String getRandomWords(@RequestParam("words") Integer words, @RequestParam("chars") Integer chars, @RequestParam("numbers") Integer numbers , @RequestParam("upperFirst") boolean upperFirst){
        return wordService.getRandomWord();
    }


    @PostMapping("/")
    @ResponseBody
    public String postRoot(@RequestBody String root) {
        return "post-" + root;
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDTO exceptionHandler(BusinessException be){
        return new ErrorDTO(be.getMessage());
    }

}
