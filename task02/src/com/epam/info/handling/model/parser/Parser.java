package com.epam.info.handling.model.parser;

import com.epam.info.handling.model.Content;

import java.util.ResourceBundle;

public abstract class Parser {

    protected String text;
    protected Parser next;
    private static final String REGEXP_SOURCE = "regexp";
    protected ResourceBundle resourceBundle = ResourceBundle.getBundle(REGEXP_SOURCE);

    public Parser() {
    }

    public Parser(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Parser getNext() {
        return next;
    }

    public void setNext(Parser next) {
        this.next = next;
    }

    public abstract Content parse();

}