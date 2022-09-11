package com.passwordgenerator.damiangrudzien.model.implementation;

import com.passwordgenerator.damiangrudzien.model.Decorator.PasswordDecorator;
import com.passwordgenerator.damiangrudzien.util.Chars;
import lombok.Getter;

import java.util.*;

import static com.passwordgenerator.damiangrudzien.util.NumberGenerator.*;


public class PasswordChar extends PasswordDecorator {

    PasswordImpl passwordImpl;


    @Getter
    Map<String, String> charsToReplace;


    public PasswordChar(PasswordImpl passwordImpl){
        super(passwordImpl);
        this.passwordImpl = passwordImpl;
        this.charsToReplace = Chars.fillChars();
    }

    @Override
    public List<String> alterPassword() {
        List<String> password = super.alterPassword();
        Integer charAmount = this.passwordImpl.getCharAmount();
        for(int i = 0; i< charAmount; i++){
               makeAlternationOfCharacter(password,makeRandomNumbers(this.passwordImpl.getCharAmount(), charsToReplace.size()));
        }
        return password;
    }

    private void makeAlternationOfCharacter(List<String> password, List<Integer> integers) {
        List<Integer> randomNumbers = integers;

    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(String subPassword : passwordImpl.getWords()){
            sb.append(subPassword);
        }
        return sb.toString();
    }
}
