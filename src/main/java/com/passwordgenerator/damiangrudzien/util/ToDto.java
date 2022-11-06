package com.passwordgenerator.damiangrudzien.util;

import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.dto.WordDto;

public class ToDto {
    private ToDto() {
        throw new IllegalCallerException();
    }

    public static WordDto wordAsDto(Word word) {
        return WordDto.builder()
                      .id(word.getId())
                      .word(word.getWord())
                      .build();
    }
}
