package com.passwordgenerator.damiangrudzien.model.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND,"This user is not found");
    }
}
