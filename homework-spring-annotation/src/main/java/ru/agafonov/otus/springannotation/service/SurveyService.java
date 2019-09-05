package ru.agafonov.otus.springannotation.service;

import ru.agafonov.otus.springannotation.domain.Question;

import java.io.IOException;
import java.util.List;

public interface SurveyService {

    List<Question> getQuestions() throws IOException;

    void startSurvey() throws IOException;
}
