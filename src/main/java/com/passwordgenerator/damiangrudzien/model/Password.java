package com.passwordgenerator.damiangrudzien.model;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class Password {
    List<String> words = new ArrayList<>();

    public void addWord(String word) {
        words.add(word);
    }
}
