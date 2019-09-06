package ru.agafonov.otus.springannotation.service;

import ru.agafonov.otus.springannotation.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionLoaderService {

    List<Question> loadCsv() throws IOException;
}