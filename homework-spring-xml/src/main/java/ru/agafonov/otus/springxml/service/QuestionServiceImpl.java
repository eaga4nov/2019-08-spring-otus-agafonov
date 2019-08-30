package ru.agafonov.otus.springxml.service;

import lombok.extern.slf4j.Slf4j;
import ru.agafonov.otus.springxml.domain.Question;

import java.util.List;
import java.util.Scanner;

@Slf4j
public class QuestionServiceImpl implements QuestionService {
    private static final String HIDDENLETTERS = "[аоейуия]";
    private static final String RUSSIALETTER = "[а-яА-Я]+";
    private static final String HIDINGSYMBOL = "*";
    private static final String NOTWRITTEINRUSSIA = "Ответ к сожалению не понятен, напишите пожалуйста по Русски";
    private final QuestionLoaderService loader;

    public QuestionServiceImpl(QuestionLoaderService loader) {
        this.loader = loader;
    }
    public List<Question> getQuestions() {
        return loader.loadCsv();
    }
    public void askingQuestions() {
        List<Question> listQuestion = getQuestions();
        Scanner in = new Scanner(System.in);
        for (Question question : listQuestion) {
            log.info("Вопрос: {} (подсказка {})\n", question.getText(), question.getAnswer().replaceAll(HIDDENLETTERS, HIDINGSYMBOL));
            while (true) {
                String answer = in.nextLine();
                if (answer.matches(RUSSIALETTER)) {
                    break;
                }
                log.info(NOTWRITTEINRUSSIA);
            }
        }
        in.close();
    }
}