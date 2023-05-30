package com.passwordgenerator.damiangrudzien.controller;

import com.passwordgenerator.damiangrudzien.exceptions.BusinessException;
import com.passwordgenerator.damiangrudzien.exceptions.NotFoundException;
import com.passwordgenerator.damiangrudzien.model.dto.PasswordDTO;
import com.passwordgenerator.damiangrudzien.model.request.PasswordRequestDTO;
import com.passwordgenerator.damiangrudzien.model.response.ErrorResponse;
import com.passwordgenerator.damiangrudzien.service.PasswordService;
import com.passwordgenerator.damiangrudzien.service.WordService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/password/")

public class PasswordController {
	@Autowired
	PasswordService passwordService;

	@GetMapping("/")
	public PasswordDTO getPasswordWithProperties(@RequestBody PasswordRequestDTO passwordRequest) {
		return passwordService.getPassword(passwordRequest);
	}

	@GetMapping("/default")
	public PasswordDTO getPasswordWithDefaultProperties() {
		return passwordService.getDefaultPassword();
	}


	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorResponse exceptionHandler(BusinessException be) {
		return new ErrorResponse(be.getMessage());
	}

}
