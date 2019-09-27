package ru.agafonov.otus.springboot.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.agafonov.otus.springboot.domain.Question;
import ru.agafonov.otus.springboot.service.QuestionLoaderService;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("QuestionLoadService должен")
class QuestionCsvLoaderServiceTest {
    @Autowired
    private QuestionLoaderService loader;

    @Test
    @DisplayName("Загрузить файл csv, проверить корректность данных")
    void testLoadingCsv() throws IOException {
        List<Question> questions = loader.loadCsv("question-list.csv");
        assertEquals(2, questions.size());
        assertEquals("The biggest mountain in the world", questions.get(1).getText());
        assertEquals("The deepest lake in the world", questions.get(0).getText());
        assertEquals("Everest", questions.get(1).getAnswer());
        assertEquals("Baikal", questions.get(0).getAnswer());

    }

    @Test
    @DisplayName("Загрузить не корректно заполненный файл csv исключить строки с пустыми вопросами и ответами")
    void testLoadingCsvWithEmptyQuestionOrEmptyAnswer() throws IOException {
        List<Question> questions = loader.loadCsv("bad-question-list.csv");
        assertEquals(1, questions.size());
        assertEquals("Question1", questions.get(0).getText());
        assertEquals("Answer1", questions.get(0).getAnswer());


    }
}
