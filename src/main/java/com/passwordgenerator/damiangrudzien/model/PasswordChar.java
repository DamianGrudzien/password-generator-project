package com.passwordgenerator.damiangrudzien.model;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class PasswordChar extends PasswordDecorator {

    private static final Logger logger = LoggerFactory.getLogger(PasswordChar.class);
    static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    PasswordImpl passwordImpl;

    @Getter
    Map<String, String> charsToReplace;


    public PasswordChar(PasswordImpl passwordImpl){
        super(passwordImpl);
        this.passwordImpl = passwordImpl;
        fillChars();
    }

    private void fillChars() {
        charsToReplace = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            Properties properties = new Properties();
            properties.load(fis);

            properties.forEach((key, value) -> charsToReplace.putIfAbsent(key.toString(),value.toString()));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<String> alterPassword() {
        List<String> password = super.alterPassword();
        Integer charAmount = this.passwordImpl.getCharAmount();


        return password;
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
