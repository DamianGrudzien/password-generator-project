package com.passwordgenerator.damiangrudzien.model.decorator;


import com.passwordgenerator.damiangrudzien.model.implementation.PasswordImpl;

import java.util.List;

public abstract class PasswordDecorator implements Password {
    private PasswordImpl passwordImpl;

    public PasswordDecorator(PasswordImpl passwordImpl) {
        this.passwordImpl = passwordImpl;
    }

    public List<String> alterPassword(){
        return passwordImpl.alterPassword();
    }
}
