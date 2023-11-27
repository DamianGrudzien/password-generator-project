package com.passwordgenerator.damiangrudzien.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {
	ADMIN,USER;
	private final String roleName = "ROLE_" + name();

}
