package com.passwordgenerator.damiangrudzien.model;

import com.passwordgenerator.damiangrudzien.model.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@Table(name = "role_table")
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long role_id;
	@Enumerated(EnumType.STRING)
	private UserRole roleName;

	@ManyToOne
	private User user;

	public Role(UserRole roleName) {
		this.roleName = roleName;
	}
	@Override
	public String getAuthority() {
		return roleName.getRoleName();
	}
}
