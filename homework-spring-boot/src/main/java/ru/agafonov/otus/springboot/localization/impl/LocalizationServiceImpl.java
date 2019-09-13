package ru.agafonov.otus.springboot.localization.impl;

import org.springframework.context.MessageSource;
import ru.agafonov.otus.springboot.localization.LocalizationService;

import java.util.Locale;

public class LocalizationServiceImpl implements LocalizationService {
    private final MessageSource messageSource;
    private final Locale locale;
    private String resourceName;

    public LocalizationServiceImpl(MessageSource messageSource, Locale locale, String resourceName) {
        this.messageSource = messageSource;
        this.locale = locale;
        this.resourceName = resourceName;
    }

    @Override
    public String getLocalizedQuestionResource() {
        return this.locale.getLanguage() + '_' + this.resourceName;
    }

    @Override
    public String getLocalizedMessage(String data) {
        return messageSource.getMessage(data, null, locale);
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }
}

