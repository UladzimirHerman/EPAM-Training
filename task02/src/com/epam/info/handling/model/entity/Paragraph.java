package com.epam.info.handling.model.entity;

import com.epam.info.handling.model.Content;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends TextPart {

    private List<Sentence> sentences;
    private String paragraph;

    public Paragraph() {
        sentences = new ArrayList<>();
    }

    public Paragraph(String paragraph) {
        this.sentences = new ArrayList<>();
        this.paragraph = paragraph;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public void removeSentence(Sentence sentence) {
        sentences.remove(sentence);
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder();

        if (paragraph != null) {
            stringBuilder.append(paragraph);
        } else {
            for (Content content : sentences) {
                stringBuilder.append(content.build());
            }
            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }

}