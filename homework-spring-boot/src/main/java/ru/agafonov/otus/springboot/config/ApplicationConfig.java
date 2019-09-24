package ru.agafonov.otus.springboot.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Getter
@Component
public class ApplicationConfig {
    private Locale locale;
    private int numberCorrectAnswerToPassSurvey;

    public ApplicationConfig(ApplicationProperties properties) {
        this.locale = new Locale(properties.getLanguage(), properties.getCountry());
        this.numberCorrectAnswerToPassSurvey = properties.getNumberAnswerToPass();
    }
}
