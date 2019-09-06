package ru.agafonov.otus.springannotation.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.agafonov.otus.springannotation.domain.Question;
import ru.agafonov.otus.springannotation.service.QuestionLoaderService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuestionCsvLoaderService implements QuestionLoaderService {

    private String pathToCsv;

    public QuestionCsvLoaderService(@Value("${app.csv.path}") String pathToCsv) {
        this.pathToCsv = pathToCsv;
    }

    @Override
    public List<Question> loadCsv() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pathToCsv);
             InputStreamReader reader = new InputStreamReader(inputStream)) {
            List<Question> questions = new CsvToBeanBuilder(reader)
                    .withType(Question.class)
                    .build()
                    .parse();
            return questions.stream()
                    .filter(question -> !question.getText().isEmpty())
                    .filter(question -> !question.getAnswer().isEmpty())
                    .collect(Collectors.toList());
        }
    }
}