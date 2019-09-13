package ru.agafonov.otus.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.agafonov.otus.springboot.localization.LocalizationService;
import ru.agafonov.otus.springboot.localization.impl.LocalizationServiceImpl;

@Configuration
public class LocalizationServiceConfig {

    @Bean
    public LocalizationService localizationService(ApplicationConfig applicationConfig) {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return new LocalizationServiceImpl(ms, applicationConfig.getLocale(), applicationConfig.getSourceName());
    }
}
