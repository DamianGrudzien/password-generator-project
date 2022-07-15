package com.passwordgenerator.damiangrudzien.repository;

import com.passwordgenerator.damiangrudzien.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Override
    Optional<Word> findById(Long aLong);
}
