package com.passwordgenerator.damiangrudzien.dbinit.user;

import com.passwordgenerator.damiangrudzien.model.Role;
import com.passwordgenerator.damiangrudzien.model.User;
import com.passwordgenerator.damiangrudzien.model.enums.UserRole;
import com.passwordgenerator.damiangrudzien.repository.jpa.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserLoader {

	public UserLoader(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	private BCryptPasswordEncoder encoder;
	@Value("${local.admin.password}")
	private String password;

	@Value("${local.admin.username}")
	private String username;

	private UserRepository userRepository;

	public UserLoader(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostConstruct
	public void run() {
		Optional<User> adminOptional = userRepository.findByUsernameIgnoreCase(username);

		if (adminOptional.isPresent()) {
			log.info("Admin already created!");
			return;
		}
		User user = new User(username, password);

		user.setUsername(username);
		user.setPassword(encoder.encode(password));
		Role role = new Role(UserRole.ADMIN);
		role.setUser(user);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		log.info("Saving Admin to DB");
		log.info("Admin: " + username);
		log.info("Pass: " + password);
		userRepository.save(user);
		log.info("Admin saved to DB");
	}
}
