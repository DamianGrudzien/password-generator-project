package com.passwordgenerator.damiangrudzien.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Password {

	List<String> words = new ArrayList<>();

	public void addWord(String word) {
		words.add(word);
	}
}
