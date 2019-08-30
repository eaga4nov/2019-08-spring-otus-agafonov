package ru.agafonov.otus.springxml.service;

import ru.agafonov.otus.springxml.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionLoaderService {
    List<Question> loadCsv()throws IOException;
}
