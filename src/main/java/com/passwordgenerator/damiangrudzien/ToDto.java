package com.passwordgenerator.damiangrudzien;

import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.model.WordDto;

public class ToDto {
    public static WordDto wordAsDto(Word word) {
        return WordDto.builder()
                .id(word.getId())
                .word(word.getWord())
                .build();
    }
}
