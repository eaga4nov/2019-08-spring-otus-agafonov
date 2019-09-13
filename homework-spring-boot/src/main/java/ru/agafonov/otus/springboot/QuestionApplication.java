package ru.agafonov.otus.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.agafonov.otus.springboot.config.ApplicationProperties;
import ru.agafonov.otus.springboot.service.SurveyService;

import java.io.IOException;
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class QuestionApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(QuestionApplication.class,args);
        SurveyService questionService = context.getBean(SurveyService.class);
        questionService.startSurvey();
    }
}