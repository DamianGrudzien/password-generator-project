package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.Application;
import com.passwordgenerator.damiangrudzien.model.Word;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
class WordServiceTest {

    @Autowired
    private WordService wordService;

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }
}