package com.passwordgenerator.damiangrudzien.model.implementation;


import com.passwordgenerator.damiangrudzien.model.Decorator.Password;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@EqualsAndHashCode
public class PasswordImpl implements Password {
    private List<String> words;
    private Integer wordAmount;
    private Integer charAmount;
    private Boolean upperFirst;

    @Override
    public List<String> alterPassword() {
        return words;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(String subPassword : words){
            sb.append(subPassword);
        }
        return sb.toString();
    }
}
