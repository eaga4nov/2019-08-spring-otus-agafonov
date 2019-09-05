package ru.agafonov.otus.springannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.agafonov.otus.springannotation.service.SurveyService;

import java.io.IOException;

public class QuestionApplication {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SurveyService questionService = context.getBean(SurveyService.class);
        questionService.startSurvey();
    }
}
