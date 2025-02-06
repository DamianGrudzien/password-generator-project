package com.passwordgenerator.damiangrudzien.dbinit.user;

import com.passwordgenerator.damiangrudzien.model.Role;
import com.passwordgenerator.damiangrudzien.model.User;
import com.passwordgenerator.damiangrudzien.model.enums.UserRole;
import com.passwordgenerator.damiangrudzien.repository.jpa.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class UserLoader {

	private PasswordEncoder encoder;
	private UserRepository userRepository;

	@Autowired
	public UserLoader(PasswordEncoder encoder, UserRepository userRepository) {
		this.encoder = encoder;
		this.userRepository = userRepository;
	}

	@Value("${local.admin.password}")
	private String password;

	@Value("${local.admin.username}")
	private String username;




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
