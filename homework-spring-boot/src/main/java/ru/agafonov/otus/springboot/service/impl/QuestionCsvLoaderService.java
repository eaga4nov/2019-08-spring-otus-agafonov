package ru.agafonov.otus.springboot.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.agafonov.otus.springboot.domain.Question;
import ru.agafonov.otus.springboot.service.QuestionLoaderService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuestionCsvLoaderService implements QuestionLoaderService {

    @Override
    public List<Question> loadCsv(String resourceName) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
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