package com.passwordgenerator.damiangrudzien.util;

import com.passwordgenerator.damiangrudzien.model.dto.WordDto;
import com.passwordgenerator.damiangrudzien.model.Word;

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
