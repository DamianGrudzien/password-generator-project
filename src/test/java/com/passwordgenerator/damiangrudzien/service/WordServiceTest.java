package com.passwordgenerator.damiangrudzien.service;

import com.passwordgenerator.damiangrudzien.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
//@RunWith(SpringJUnit4ClassRunner.class)
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