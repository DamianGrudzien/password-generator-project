package com.passwordgenerator.damiangrudzien.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WordDto {
    private Long id;
    private String word;
}
