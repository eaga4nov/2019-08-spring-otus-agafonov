package ru.agafonov.otus.springboot.localization;

import java.util.Locale;

public interface LocalizationService {
    String getLocalizedQuestionResource();

    String getLocalizedMessage(String message);

    Locale getLocale();
}
