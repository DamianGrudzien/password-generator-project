package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.model.User;
import com.passwordgenerator.damiangrudzien.model.Role;
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
import java.util.stream.Collectors;

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
		return users.stream().map(u -> modelMapper.map(u, UserResponse.class)).collect(Collectors.toList());
	}

	public User getUser(String username) {
		return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
	}

	public User saveUser(User user) {
		log.info("User: " + user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (userRepository.findAll().isEmpty()) {
			user.setRoles(List.of(new Role(UserRole.ADMIN)));
		} else {
			user.setRoles(List.of(new Role(UserRole.USER)));
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

	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

//	public User updateUser(User user) {
//		User userToUpdate = userRepository.findUserByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
//		userToUpdate.setName(user.getName());
//		userToUpdate.setSurname(user.getSurname());
//		userToUpdate.setUsername(user.getUsername());
//		userToUpdate.setPassword(user.getPassword());
//		userToUpdate.setEmail(user.getEmail());
//		userToUpdate.setPhoneNumber(user.getPhoneNumber());
//		return userRepository.save(userToUpdate);
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
	}
}
