package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    public Optional<Word> findById(Long id){
        return wordRepository.findAll().stream().filter(word -> word.getId() == id).findFirst();
    }
}
