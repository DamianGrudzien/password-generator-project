package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.model.dto.PasswordDTO;
import com.passwordgenerator.damiangrudzien.model.request.PasswordRequestDTO;
import com.passwordgenerator.damiangrudzien.util.Chars;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PasswordService {

	WordService wordService;

	public PasswordDTO getPassword(PasswordRequestDTO pp) {
		List<String> randomWords = wordService.getRandomPass(pp.getWordAmount());
		Boolean isUpperFirst = pp.getUpperFirst();

		if (Boolean.TRUE.equals(isUpperFirst)) {
			randomWords = randomWords.stream()
					.map(word -> word.substring(0, 1).toUpperCase() +
							word.substring(1).toLowerCase())
					.collect(Collectors.toList());
		}

		Map<String, String> charsToReplace = Chars.getCharToReplace();
		String password;

		if (pp.getCharAmount() != 0 && !charsToReplace.isEmpty()) {
			password = Arrays.stream(randomWords.toString()
							.split(""))
					.map(letter -> charsToReplace.getOrDefault(letter, letter))
					.collect(Collectors.joining());
		} else {
			password = String.join("", randomWords);
		}

		return new PasswordDTO(password);
	}

	public PasswordDTO getDefaultPassword() {
		return null;
	}
}
