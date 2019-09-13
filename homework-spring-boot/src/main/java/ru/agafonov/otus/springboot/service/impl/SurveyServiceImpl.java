
package ru.agafonov.otus.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.agafonov.otus.springboot.domain.Question;
import ru.agafonov.otus.springboot.localization.LocalizationService;
import ru.agafonov.otus.springboot.service.QuestionLoaderService;
import ru.agafonov.otus.springboot.service.SurveyService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
public class SurveyServiceImpl implements SurveyService {

    private static final String HIDDEN_LETTERS = "question.hint.hidden-characters";
    private static final String VALID_CHARACTERS = "question.answer.valid-character";
    private static final String HIDING_SYMBOL = "*";
    private static final String INVALID_CHARACTERS_MESSAGE = "question.message.invalid-character";
    private static final String ENTER_YOU_NAME = "question.user.name";
    private static final String ENTER_YOU_LAST_NAME = "question.user.last-name";
    private static final String PASSED_SURVEY_MESSAGE = "question.message.passed-survey";
    private static final String SURVEY_RESULT_MESSAGES = "question.message.survey-result";
    private static final String QUESTION_BODY_MESSAGE = "question.message.body-question";
    private static final String CORRECT_ANSWER_MESSAGE = "question.message.correct-answer-accept";
    private static final String NOT_CORRECT_ANSWER_MESSAGE = "question.message.not-correct-answer-accept";
    private static final String NOT_PASSED_SURVEY_MESSAGE = "question.message.not-passed-survey";
    private static final String CURRENT_LOCALE = "question.message.current-locale";

    private final QuestionLoaderService loader;
    private Integer numberCorrectAnswerToPassSurvey;
    private final LocalizationService localization;

    @Autowired
    public SurveyServiceImpl(QuestionLoaderService loader,
                             LocalizationService localization,
                             @Value("#{T(java.lang.Integer).parseInt('${survey.number-answer-to-pass}')}") Integer numberCorrectAnswerToPassSurvey) {

        this.numberCorrectAnswerToPassSurvey = numberCorrectAnswerToPassSurvey;
        this.loader = loader;
        this.localization = localization;
    }

    @Override
    public void startSurvey() throws IOException {
        log.info(localization.getLocalizedMessage(CURRENT_LOCALE), localization.getLocale().toString());
        Scanner in = new Scanner(System.in);
        askUserData(in);
        askQuestions(in, getQuestions());
    }

    @Override
    public List<Question> getQuestions() throws IOException {
        log.debug(localization.getLocalizedQuestionResource());
        return loader.loadCsv(localization.getLocalizedQuestionResource());
    }

    private void askUserData(Scanner in) {
        String[] listQuestionUserData = {ENTER_YOU_NAME, ENTER_YOU_LAST_NAME};
        for (String question : listQuestionUserData) {
            String currentQuestion = localization.getLocalizedMessage(question);
            log.info(currentQuestion);
            String input;
            while (!(input = in.nextLine()).matches(localization.getLocalizedMessage(VALID_CHARACTERS))) {
                log.info(localization.getLocalizedMessage(INVALID_CHARACTERS_MESSAGE));
            }
            log.info("{} : {}", currentQuestion, input);
        }
    }

    private void askQuestions(Scanner in, List<Question> listQuestion) {

        int correctAnswer = 0;
        for (Question question : listQuestion) {
            String currentQuestion = question.getText();
            String currentAnswer = question.getAnswer();
            String questionBodyMessage = localization.getLocalizedMessage(QUESTION_BODY_MESSAGE);
            String hiddenLetters = localization.getLocalizedMessage(HIDDEN_LETTERS);
            log.info(questionBodyMessage, currentQuestion, currentAnswer.replaceAll(hiddenLetters, HIDING_SYMBOL));
            String input;
            while (!(input = in.nextLine()).matches(localization.getLocalizedMessage(VALID_CHARACTERS))) {
                log.info(localization.getLocalizedMessage(INVALID_CHARACTERS_MESSAGE));
            }
            if (input.equalsIgnoreCase(currentAnswer)) {
                log.info(localization.getLocalizedMessage(CORRECT_ANSWER_MESSAGE), input);
                correctAnswer++;
            } else {
                log.info(localization.getLocalizedMessage(NOT_CORRECT_ANSWER_MESSAGE), input, currentAnswer);
            }
        }
        log.info(localization.getLocalizedMessage(SURVEY_RESULT_MESSAGES), correctAnswer, listQuestion.size());
        if (correctAnswer >= numberCorrectAnswerToPassSurvey) {
            log.info(localization.getLocalizedMessage(PASSED_SURVEY_MESSAGE));
        } else {
            log.info(localization.getLocalizedMessage(NOT_PASSED_SURVEY_MESSAGE));
        }
    }
}