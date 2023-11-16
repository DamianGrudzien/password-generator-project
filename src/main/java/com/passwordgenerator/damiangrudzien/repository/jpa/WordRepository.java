package com.passwordgenerator.damiangrudzien.repository.jpa;

import com.passwordgenerator.damiangrudzien.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

	@Query(value = "SELECT * from word order BY id ASC LIMIT 1", nativeQuery = true)
	Optional<Word> getFirstWord();


}
