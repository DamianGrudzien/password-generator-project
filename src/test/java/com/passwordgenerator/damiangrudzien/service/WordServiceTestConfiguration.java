package com.passwordgenerator.damiangrudzien.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class WordServiceTestConfiguration {

//    @Bean
//    @Primary
//    public WordService wordService(){
//        return Mockito.mock(WordService.class);
//    }
}
