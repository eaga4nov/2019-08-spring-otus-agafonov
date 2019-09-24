package ru.agafonov.otus.springboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("survey")
public class ApplicationProperties {

    private String language;
    private String country;
    private int numberAnswerToPass;
}
