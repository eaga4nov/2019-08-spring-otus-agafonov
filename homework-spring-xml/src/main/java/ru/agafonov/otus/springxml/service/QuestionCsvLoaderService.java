package ru.agafonov.otus.springxml.service;


import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.agafonov.otus.springxml.domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


@Slf4j
public class QuestionCsvLoaderService implements QuestionLoaderService {
    private String pathToCsv;
    public QuestionCsvLoaderService(String pathToCsv) {
        this.pathToCsv = pathToCsv;
    }

    public List<Question> loadCsv() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pathToCsv);
        InputStreamReader reader = new InputStreamReader(inputStream);
        List<Question> listQuestions = new CsvToBeanBuilder(reader)
                .withType(Question.class)
                .build()
                .parse();
        return listQuestions;
    }
}




