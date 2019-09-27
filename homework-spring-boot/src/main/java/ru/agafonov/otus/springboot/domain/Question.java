package ru.agafonov.otus.springboot.domain;

import com.opencsv.bean.CsvBindByName;

public class Question {

    @CsvBindByName(column = "text")
    private String text;

    @CsvBindByName(column = "answer")
    private String answer;

    public String getText() {
        return this.text;
    }

    public String getAnswer() {
        return this.answer;
    }
}
