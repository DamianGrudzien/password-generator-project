package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.model.Role;
import com.passwordgenerator.damiangrudzien.model.User;
import com.passwordgenerator.damiangrudzien.model.enums.UserRole;
import com.passwordgenerator.damiangrudzien.model.exceptions.UserAlreadyExistedException;
import com.passwordgenerator.damiangrudzien.model.exceptions.UserNotFoundException;
import com.passwordgenerator.damiangrudzien.model.response.UserResponse;
import com.passwordgenerator.damiangrudzien.repository.jpa.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;

	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		log.info("Found users! Count: {}", users.size());
		return users.stream().map(u -> modelMapper.map(u, UserResponse.class)).toList();
	}

	public User getUser(String username) {
		return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
	}

	public User saveUser(User user) {
		log.info("User: " + user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (userRepository.findAll().isEmpty()) {
			Role role = new Role(UserRole.ADMIN);
			role.setUser(user);
			user.setRoles(List.of(role));
		} else {
			Role role = new Role(UserRole.USER);
			role.setUser(user);
			user.setRoles(List.of(role));
		}
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserAlreadyExistedException();
		}
		log.info("User registrated: {}", user);
		return userRepository.save(user);
	}

	public void deleteUser(String username) {
		User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
		userRepository.delete(user);
		log.info("User deleted: {}", user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
	}
}
