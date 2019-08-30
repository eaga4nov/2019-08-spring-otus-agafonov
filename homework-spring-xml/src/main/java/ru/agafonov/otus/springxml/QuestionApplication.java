package ru.agafonov.otus.springxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.agafonov.otus.springxml.service.QuestionService;

import java.io.IOException;

public class QuestionApplication {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.askingQuestions();
    }
}
