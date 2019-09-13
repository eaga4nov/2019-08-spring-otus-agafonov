package ru.agafonov.otus.springboot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.agafonov.otus.springboot.service.SurveyService;

import java.io.IOException;

@ComponentScan
@Configuration
@PropertySource(value = "classpath:application.properties")
public class QuestionApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(QuestionApplication.class);
        SurveyService questionService = context.getBean(SurveyService.class);
        questionService.startSurvey();
    }
}