package ru.agafonov.otus.springxml.domain;

import com.opencsv.bean.CsvBindByName;

public class Question {

    @CsvBindByName(column = "Text Question")
    private String text;

    @CsvBindByName(column = "Answer")
    private String answer;

    public String getText() {
        return this.text;
    }

    public String getAnswer() {
        return this.answer;
    }
}
