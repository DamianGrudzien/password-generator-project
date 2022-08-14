package com.passwordgenerator.damiangrudzien;

import com.passwordgenerator.damiangrudzien.model.WordDto;
import com.passwordgenerator.damiangrudzien.service.PasswordGenerator;
import com.passwordgenerator.damiangrudzien.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController{

    @Autowired
    WordService wordService;

    @GetMapping("/")
    @ResponseBody
    public String getRoot() {
        return "root";
    }

    @GetMapping("/passwords/{id}")
    public WordDto getPassword(@PathVariable("id") Long id){
        return wordService.findById(id).map(ToDto::wordAsDto).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/passwords/random")
    public String getRandomPassword(){
        return PasswordGenerator.getRandomPass(wordService);
    }

    @PostMapping("/")
    @ResponseBody
    public String postRoot(@RequestBody String root) {
        return "post-" + root;
    }


}
