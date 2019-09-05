package ru.agafonov.otus.springannotation;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.agafonov.otus.springannotation.service.SurveyService;

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

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
