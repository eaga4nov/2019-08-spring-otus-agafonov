
package ru.agafonov.otus.springxml.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.agafonov.otus.springxml.domain.Question;
import ru.agafonov.otus.springxml.service.QuestionLoaderService;
import ru.agafonov.otus.springxml.service.QuestionService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private static final String HIDDEN_LETTERS = "[аоейуия]";
    private static final String RUSSIA_LETTERS = "[а-яА-Я]+";
    private static final String HIDING_SYMBOL = "*";
    private static final String NOT_WRITE_IN_RUSSIAN = "Ответ к сожалению не понятен, напишите пожалуйста по Русски";

    private QuestionLoaderService loader;

    public QuestionServiceImpl(QuestionLoaderService loader) {
        this.loader = loader;
    }

    public List<Question> getQuestions() throws IOException {
        return loader.loadCsv();
    }

    public void askingQuestions() throws IOException {
        List<Question> listQuestion = getQuestions();
        try (Scanner in = new Scanner(System.in)) {
            for (Question question : listQuestion) {
                log.info("Вопрос: {} (подсказка {})",
                        question.getText(),
                        question.getAnswer().replaceAll(HIDDEN_LETTERS, HIDING_SYMBOL)
                );
                String input;
                while (!(input = in.nextLine()).matches(RUSSIA_LETTERS)) {
                    log.info(NOT_WRITE_IN_RUSSIAN);
                }
                log.info("Ответ принят {} ", input);
            }
        }
    }
}