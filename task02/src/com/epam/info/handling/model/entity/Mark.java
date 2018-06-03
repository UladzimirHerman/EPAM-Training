package com.epam.info.handling.model.entity;

public class Mark extends SentencePart {

    private String mark;

    public Mark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String build() {
        return mark;
    }

}