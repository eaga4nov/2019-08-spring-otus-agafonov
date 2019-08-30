package ru.agafonov.otus.springxml.service;

import ru.agafonov.otus.springxml.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions();
    void askingQuestions();
}
