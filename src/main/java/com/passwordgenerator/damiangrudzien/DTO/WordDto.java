package com.passwordgenerator.damiangrudzien.DTO;

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