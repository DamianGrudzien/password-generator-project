package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.model.dto.WordDto;
import com.passwordgenerator.damiangrudzien.exceptions.NotFoundException;
import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.repository.WordRepository;
import com.passwordgenerator.damiangrudzien.util.ToDto;
import com.passwordgenerator.damiangrudzien.util.WordsGenerator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class WordService {
    private WordRepository wordRepository;

    public WordDto findById(Long id) {
        Optional<Word> wordById = wordRepository.findById(id);
        if (wordById.isEmpty()) {
            throw new NotFoundException();
        }
        return wordById.map(ToDto::wordAsDto)
                       .orElseThrow(NotFoundException::new);
    }

    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    public String getRandomWord() {
        WordsGenerator wordsGenerator = new WordsGenerator(this);
        return wordsGenerator.getRandomPass(1)
                             .get(0);
    }
}
