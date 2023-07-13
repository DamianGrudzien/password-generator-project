package com.passwordgenerator.damiangrudzien.config;

import com.passwordgenerator.damiangrudzien.model.Word;
import com.passwordgenerator.damiangrudzien.repository.jpa.WordRepository;
import com.passwordgenerator.damiangrudzien.util.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
//public class CsvDataLoader implements ApplicationListener<ContextRefreshedEvent> {
public class CsvDataLoader implements ApplicationRunner{
//public class CsvDataLoader{
    @Autowired
    private CsvReader csvReader;

    @Autowired
    private WordRepository wordRepository;

    @Value("${csv.file.path}")
    private String csvFilePath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long tableSize = wordRepository.count();
        if (tableSize > 10000){
            return;
        }
        log.info("Start to load words from file");
        List<Word> wordsToSave = csvReader.loadDataFromCsv(csvFilePath);
        List<Word> queryList = new ArrayList<>();
        int counter = 0;
        log.info("Counter is: " + counter);
        log.info("Amount of word to save: " + wordsToSave.size());
        for (int i = 0; i < wordsToSave.size() ;i++) {
            queryList.add(wordsToSave.get(i));
            counter++;

            if (counter >= 500 || ((wordsToSave.size() - i) < 500)) {
                log.info("About to save words to DB");
                log.info("Counter is: " + counter);
                log.info("QueryList size: " + queryList.size());
                try {
                    wordRepository.saveAll(queryList);
                    log.info("Saved");
                    counter = 0;
                } catch (Exception e) {
                    log.info("Error: " + e.getMessage());
//                throw new RuntimeException(e);
                }
                queryList = new ArrayList<>();
            }

        }
        log.info("Saving ended!");
    }
}