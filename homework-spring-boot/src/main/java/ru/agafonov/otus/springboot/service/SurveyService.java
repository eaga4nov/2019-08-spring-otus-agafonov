package ru.agafonov.otus.springboot.service;

import ru.agafonov.otus.springboot.domain.Question;

import java.io.IOException;
import java.util.List;

public interface SurveyService {

    List<Question> getQuestions() throws IOException;

    void startSurvey() throws IOException;
}