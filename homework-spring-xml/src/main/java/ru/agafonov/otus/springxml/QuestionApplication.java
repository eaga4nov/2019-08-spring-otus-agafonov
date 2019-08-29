package ru.agafonov.otus.springxml;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.agafonov.otus.springxml.service.QuestionLoaderService;


public class QuestionApplication {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        QuestionLoaderService loaderService = context.getBean(QuestionLoaderService.class);

        loaderService.getListQuestion();

    }

}
