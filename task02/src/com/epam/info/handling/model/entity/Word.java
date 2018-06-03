package com.epam.info.handling.model.entity;

public class Word extends SentencePart {

    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String build() {
        return word;
    }

}