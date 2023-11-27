package com.passwordgenerator.damiangrudzien.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.passwordgenerator.damiangrudzien.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@Table(name = "role_table")
@Getter
@Setter
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private UserRole roleName;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;

	public Role(UserRole roleName) {
		this.roleName = roleName;
	}
	@Override
	public String getAuthority() {
		return roleName.getRoleName();
	}
}
