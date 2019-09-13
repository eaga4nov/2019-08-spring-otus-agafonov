package ru.agafonov.otus.springboot.service;

import ru.agafonov.otus.springboot.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionLoaderService {

    List<Question> loadCsv(String resourceName) throws IOException;
}