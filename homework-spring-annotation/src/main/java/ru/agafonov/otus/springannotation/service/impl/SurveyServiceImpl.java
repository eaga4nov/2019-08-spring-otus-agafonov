
package ru.agafonov.otus.springannotation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agafonov.otus.springannotation.domain.Question;
import ru.agafonov.otus.springannotation.service.QuestionLoaderService;
import ru.agafonov.otus.springannotation.service.SurveyService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
public class SurveyServiceImpl implements SurveyService {

    private static final String HIDDEN_LETTERS = "[аоейкруия]";
    private static final String RUSSIA_LETTERS = "([а-яА-Я]+)";
    private static final String HIDING_SYMBOL = "*";
    private static final String NOT_WRITE_IN_RUSSIAN = "Ответ к сожалению не понятен, напишите пожалуйста по Русски";
    private static final String ENTER_YOU_NAME = "Укажите Ваше имя";
    private static final String ENTER_YOU_LAST_NAME = "Укажите Вашу фамилию";

    private final QuestionLoaderService loader;

    @Autowired
    public SurveyServiceImpl(QuestionLoaderService loader) {
        this.loader = loader;
    }

    @Override
    public List<Question> getQuestions() throws IOException {
        return loader.loadCsv();
    }

    @Override
    public void startSurvey() throws IOException {
        Scanner in = new Scanner(System.in);
        askUserData(in);
        askQuestions(in, getQuestions());
    }

    private void askUserData(Scanner in) {
        String[] listQuestionUserData = {ENTER_YOU_NAME, ENTER_YOU_LAST_NAME};
        for (String question : listQuestionUserData) {
            log.info(question);
            String input;
            while (!(input = in.nextLine()).matches(RUSSIA_LETTERS)) {
                log.info(NOT_WRITE_IN_RUSSIAN);
            }
            log.info("{} : {}", question, input);
        }
    }

    private void askQuestions(Scanner in, List<Question> listQuestion) {
        int correctAnswer = 0;
        for (Question question : listQuestion) {
            log.info("Вопрос: {}? (подсказка {})",
                    question.getText(),
                    question.getAnswer().replaceAll(HIDDEN_LETTERS, HIDING_SYMBOL)
            );
            String input;
            while (!(input = in.nextLine()).matches(RUSSIA_LETTERS)) {
                log.info(NOT_WRITE_IN_RUSSIAN);
            }

            if (input.equalsIgnoreCase(question.getAnswer())) {
                log.info("Ответ: {} принят - правильный ответ.", input);
                correctAnswer++;
            } else {
                log.info("Ответ {} принят - не правильный ответ ({}).", input, question.getAnswer());
            }
        }
        log.info("Правильных ответов {} из {} ", correctAnswer, listQuestion.size());
    }
}


