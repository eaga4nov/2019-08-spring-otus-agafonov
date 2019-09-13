
package ru.agafonov.otus.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import ru.agafonov.otus.springboot.domain.Question;
import ru.agafonov.otus.springboot.service.QuestionLoaderService;
import ru.agafonov.otus.springboot.service.SurveyService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Slf4j
@Service
public class SurveyServiceImpl implements SurveyService {

    private static final String HIDDEN_LETTERS = "question.hiddencharacters";
    private static final String VALID_CHARACTERS = "question.validcharacter";
    private static final String HIDING_SYMBOL = "*";
    private static final String INVALID_CHARACTERS_MESSAGE = "question.message.invalidcharacter";
    private static final String ENTER_YOU_NAME = "question.name";
    private static final String ENTER_YOU_LAST_NAME = "question.lastname";
    private static final String PASSED_SURVEY_MESSAGE = "question.message.passedsurvey";
    private static final String SURVEY_RESULT_MESSAGES = "question.message.surveyresult";
    private static final String QUESTION_BODY_MESSAGE = "question.message.body";
    private static final String CORRECT_ANSWER_MESSAGE = "question.message.correctansweraccept";
    private static final String NOT_CORRECT_ANSWER_MESSAGE = "question.message.notcorrectansweraccept";
    private static final String NOT_PASSED_SURVEY_MESSAGE = "question.message.notpassedsurvey";
    private static final String CURRENT_LOCALE = "question.message.currentlocale";


    private Locale locale;
    private final QuestionLoaderService loader;
    private final MessageSource messageSource;
    private Integer numberCorrectAnswerToPassSurvey;

    @Autowired
    public SurveyServiceImpl(QuestionLoaderService loader,
                             MessageSource messageSource,
                             @Value("#{T(java.lang.Integer).parseInt('${app.numberanswertopass}')}") Integer numberCorrectAnswerToPassSurvey) {
        this.numberCorrectAnswerToPassSurvey = numberCorrectAnswerToPassSurvey;
        this.loader = loader;
        this.messageSource = messageSource;
        this.locale = new Locale(System.getProperty("user.country"), System.getProperty("user.language"));
    }

    @Override
    public List<Question> getQuestions() throws IOException {
        return loader.loadCsv();
    }

    @Override
    public void startSurvey() throws IOException {
        log.info(localizeData(CURRENT_LOCALE), locale.toString());
        Scanner in = new Scanner(System.in);
        askUserData(in);
        askQuestions(in, getQuestions());
    }

    private void askUserData(Scanner in) {
        String[] listQuestionUserData = {ENTER_YOU_NAME, ENTER_YOU_LAST_NAME};
        for (String question : listQuestionUserData) {
            String currentQuestion = localizeData(question);
            log.info(currentQuestion);
            String input;
            while (!(input = in.nextLine()).matches(localizeData(VALID_CHARACTERS))) {
                log.info(localizeData(INVALID_CHARACTERS_MESSAGE));
            }
            log.info("{} : {}", currentQuestion, input);
        }
    }

    private void askQuestions(Scanner in, List<Question> listQuestion) {

        int correctAnswer = 0;
        for (Question question : listQuestion) {
            String currentQuestion = localizeData(question.getText());
            String currentAnswer = localizeData(question.getAnswer());
            String hiddenLetters = localizeData(HIDDEN_LETTERS);
            String questionBody = localizeData(QUESTION_BODY_MESSAGE);
            log.info(questionBody, currentQuestion, currentAnswer.replaceAll(hiddenLetters, HIDING_SYMBOL));
            String input;
            while (!(input = in.nextLine()).matches(localizeData(VALID_CHARACTERS))) {
                log.info(localizeData(INVALID_CHARACTERS_MESSAGE));
            }

            if (input.equalsIgnoreCase(currentAnswer)) {
                log.info(localizeData(CORRECT_ANSWER_MESSAGE), input);
                correctAnswer++;
            } else {
                log.info(localizeData(NOT_CORRECT_ANSWER_MESSAGE), input, currentAnswer);
            }
        }
        log.info(localizeData(SURVEY_RESULT_MESSAGES), correctAnswer, listQuestion.size());
        if (correctAnswer >= numberCorrectAnswerToPassSurvey) {
            log.info(localizeData(PASSED_SURVEY_MESSAGE));
        } else {
            log.info(localizeData(NOT_PASSED_SURVEY_MESSAGE));
        }
    }

    private String localizeData(String data) {
        try {
            return messageSource.getMessage(data, null, locale);
        } catch (NoSuchMessageException e) {
            return messageSource.getMessage(data, null, new Locale("en", "US"));

        }
    }
}