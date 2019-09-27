package ru.agafonov.otus.springboot.localization;

import java.util.Locale;

public interface LocalizationService {

    String getLocalizedMessage(String message);

    Locale getLocale();
}
