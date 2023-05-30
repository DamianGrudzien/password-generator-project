package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.model.dto.PasswordDTO;
import com.passwordgenerator.damiangrudzien.model.request.PasswordRequestDTO;
import com.passwordgenerator.damiangrudzien.util.Chars;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PasswordService {

	WordService wordService;
	Chars chars;

	private static final Random RANDOM = new Random();

	public PasswordDTO getPassword(PasswordRequestDTO passwordRequest) {
		List<String> randomWords = wordService.getRandomWords(passwordRequest.getWordAmount());
		Boolean isUpperFirst = passwordRequest.getUpperFirst();


		if (Boolean.TRUE.equals(isUpperFirst)) {
			randomWords = randomWords.stream()
					.map(word -> word.substring(0, 1).toUpperCase() +
							word.substring(1).toLowerCase())
					.collect(Collectors.toList());
		}
		int numberAmount = passwordRequest.getNumberAmount();
		if (numberAmount > 0) {
			int numberOfPlaces = randomWords.size();
			for (int i = 0; i < numberAmount; i++) {
				int nextInt = RANDOM.nextInt(0, 9);
				int placeToInsert = RANDOM.nextInt(0, numberOfPlaces);
				randomWords.add(placeToInsert, String.valueOf(nextInt));
				numberOfPlaces++;
			}
		}

		Map<String, String> charsToReplace = chars.getCharToReplace();
		String password;
		int charAmount = passwordRequest.getCharAmount();
		if (charAmount != 0 && !charsToReplace.isEmpty()) {

			String[] lettersSplitted = String.join("", randomWords)
					.split("");
			for (int i = 0; i < lettersSplitted.length && charAmount > 0; i++) {
				if (charsToReplace.containsKey(lettersSplitted[i])) {
					lettersSplitted[i] = charsToReplace.getOrDefault(lettersSplitted[i], lettersSplitted[i]);
					charAmount--;
				}
			}
			password = String.join("", lettersSplitted);
		} else {
			password = String.join("", randomWords);
		}

		return new PasswordDTO(password);
	}

	public PasswordDTO getDefaultPassword() {
		return null;
	}
}
