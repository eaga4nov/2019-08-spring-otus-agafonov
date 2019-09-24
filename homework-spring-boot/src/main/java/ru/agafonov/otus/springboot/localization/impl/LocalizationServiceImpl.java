package ru.agafonov.otus.springboot.localization.impl;

import org.springframework.context.MessageSource;
import ru.agafonov.otus.springboot.localization.LocalizationService;

import java.util.Locale;

public class LocalizationServiceImpl implements LocalizationService {
    private final MessageSource messageSource;
    private final Locale locale;

    public LocalizationServiceImpl(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
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

