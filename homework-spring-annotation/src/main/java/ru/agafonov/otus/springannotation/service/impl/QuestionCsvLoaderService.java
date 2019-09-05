package ru.agafonov.otus.springannotation.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.agafonov.otus.springannotation.domain.Question;
import ru.agafonov.otus.springannotation.service.QuestionLoaderService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class QuestionCsvLoaderService implements QuestionLoaderService {
    private String pathToCsv;

    public QuestionCsvLoaderService(String pathToCsv) {
        this.pathToCsv = pathToCsv;
    }

    public List<Question> loadCsv() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pathToCsv);
             InputStreamReader reader = new InputStreamReader(inputStream)) {
            return (List<Question>) new CsvToBeanBuilder(reader)
                    .withType(Question.class)
                    .build()
                    .parse();
        }
    }
}




