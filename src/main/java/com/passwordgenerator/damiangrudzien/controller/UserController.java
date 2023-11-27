package com.passwordgenerator.damiangrudzien.controller;

import com.passwordgenerator.damiangrudzien.model.User;
import com.passwordgenerator.damiangrudzien.model.request.UserRequestDTO;
import com.passwordgenerator.damiangrudzien.model.response.UserResponse;
import com.passwordgenerator.damiangrudzien.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
@AllArgsConstructor
public class UserController {

	private UserService userService;
	private ModelMapper modelMapper;

//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse> getAllUsers(){
		log.info("Starting the return of users.");
		return userService.getAllUsers();
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		User user = modelMapper.map(userRequestDTO, User.class);
		User savedUser = userService.saveUser(user);
		return modelMapper.map(savedUser, UserResponse.class);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@DeleteMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@DeleteMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllUsers() {
		userService.deleteAllUsers();
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@PathVariable String username) {
		return userService.getUser(username);

	}
}
