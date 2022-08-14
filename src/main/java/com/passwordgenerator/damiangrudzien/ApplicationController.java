package com.passwordgenerator.damiangrudzien;

import com.passwordgenerator.damiangrudzien.model.WordDto;
import com.passwordgenerator.damiangrudzien.service.PasswordGenerator;
import com.passwordgenerator.damiangrudzien.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController{

    @Autowired
    WordService wordService;

    @RequestMapping("/")
    @ResponseBody
    public String getRoot() {
        return "root";
    }

    @RequestMapping("/passwords/{id}")
    @ResponseBody
    public WordDto getPassword(@PathVariable("id") Long id){
        return wordService.findById(id).map(ToDto::wordAsDto).orElseThrow(RuntimeException::new);
    }

    @RequestMapping("/passwords/random")
    @ResponseBody
    public String getRandomPassword(){
        return PasswordGenerator.getRandomPass(wordService);
    }

    @PostMapping("/")
    @ResponseBody
    public String postRoot(@RequestBody String root) {
        return "post-" + root;
    }


}
