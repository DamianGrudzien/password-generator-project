package com.passwordgenerator.damiangrudzien.controller;

import com.passwordgenerator.damiangrudzien.exceptions.BusinessException;
import com.passwordgenerator.damiangrudzien.exceptions.NotFoundException;
import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.model.response.ErrorResponse;
import com.passwordgenerator.damiangrudzien.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/word")
@Slf4j
public class WordController {

	public WordController(WordService wordService) {
		this.wordService = wordService;
	}

	WordService wordService;

	@GetMapping("/{id}")
	public Word getWordById(@PathVariable("id") Long id) {
		return wordService.findById(id);
	}

	@GetMapping("/random")
	public String getRandomWord() {
		log.info("Getting random word.");
		return wordService.getRandomWord();
	}

	@GetMapping("/random/{words}")
	public String getRandomWords(@PathVariable("words") Integer words) {
		return "list of words";
	}

	@GetMapping("/")
	public List<Word> getAllWords() {
		return wordService.findAll();
	}

	@PostMapping("/flush")
	public void flushCache() {
		wordService.flushCache();
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorResponse exceptionHandler(BusinessException be) {
		return new ErrorResponse(be.getMessage());
	}

}
