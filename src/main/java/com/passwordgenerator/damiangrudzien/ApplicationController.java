package com.passwordgenerator.damiangrudzien;

import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.model.WordDto;
import com.passwordgenerator.damiangrudzien.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

    @Autowired
    WordService wordService;

    @GetMapping("/")
    @ResponseBody
    public String getRoot() {
        return "root";
    }

    @GetMapping("/passwords/{id}")
    public WordDto getPassword(@PathVariable("id") Long id){
        return wordService.findById(id).map(this::wordAsDto).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/")
    @ResponseBody
    public String postRoot(@RequestBody String root) {
        return "post-" + root;
    }



    public WordDto wordAsDto(Word word){
        return WordDto.builder()
                .id(word.getId())
                .word(word.getWord())
                .build();
    }


}
