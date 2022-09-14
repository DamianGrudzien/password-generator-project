package com.passwordgenerator.damiangrudzien.model.implementation;

import com.passwordgenerator.damiangrudzien.model.decorator.PasswordDecorator;
import com.passwordgenerator.damiangrudzien.util.Chars;
import lombok.Getter;

import java.util.List;
import java.util.Map;

import static com.passwordgenerator.damiangrudzien.util.NumberGenerator.makeRandomNumbers;


public class PasswordChar extends PasswordDecorator {

    PasswordImpl passwordImpl;


    @Getter
    Map<Character, String> charsToReplace;


    public PasswordChar(PasswordImpl passwordImpl) {
        super(passwordImpl);
        this.passwordImpl = passwordImpl;
        this.charsToReplace = Chars.fillChars();
    }

    @Override
    public List<String> alterPassword() {
        if (charsToReplace != null) {
            List<String> password = super.alterPassword();
            Integer charAmount = this.passwordImpl.getCharAmount();
            for (int i = 0; i < charAmount; i++) {
                makeAlternationOfCharacter(password, makeRandomNumbers(this.passwordImpl.getCharAmount(), this.charsToReplace.size()));
            }
            return password;
        } else {
            return this.passwordImpl.getWords();
        }
    }

    private void makeAlternationOfCharacter(List<String> password, List<Integer> randomNumbers) {
        int usedChar = 0;
        for (int i = 0; i < password.size(); i++) {
            if (usedChar < this.passwordImpl.getCharAmount()) {
                char[] passwordChars = password.get(i)
                                               .toCharArray();
                for (int j = 0; j < passwordChars.length; j++) {
                    if (charsToReplace.containsKey(passwordChars[j]) && usedChar < this.passwordImpl.getCharAmount()) {
                        passwordChars[j] = charsToReplace.get(passwordChars[j])
                                                         .charAt(0);
                        usedChar++;
                    } else {
                        break;
                    }
                }
                password.set(i, alterToPrettyString(passwordChars));
            } else {
                break;
            }
        }
    }

    private String alterToPrettyString(char[] passwordChars) {
        StringBuilder sb = new StringBuilder();
        for (char c : passwordChars) {
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String subPassword : passwordImpl.getWords()) {
            sb.append(subPassword);
        }
        return sb.toString();
    }
}
