package ru.agafonov.otus.springboot.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.agafonov.otus.springboot.service.SurveyService;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class SurveyCommands {

    private final SurveyService survey;

    @ShellMethod(value = "Start survey", key = {"1"})
    void askUserData() throws IOException {
        survey.startSurvey();
    }
}
