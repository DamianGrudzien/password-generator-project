package com.passwordgenerator.damiangrudzien.model.response;

import com.passwordgenerator.damiangrudzien.model.Role;
import com.passwordgenerator.damiangrudzien.model.enums.UserStatus;
import lombok.Data;
import java.util.List;


@Data
public class UserResponse {
	private String username;
	private List<Role> roles;
	private UserStatus userStatus;
}
