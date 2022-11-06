package com.passwordgenerator.damiangrudzien.exceptions;

public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super("Word was not found in DataBase!");
    }
}
