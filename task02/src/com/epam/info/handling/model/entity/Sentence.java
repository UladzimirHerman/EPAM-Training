package com.epam.info.handling.model.entity;

import com.epam.info.handling.model.Content;

import java.util.ArrayList;
import java.util.List;

public class Sentence implements Content {

    private List<SentencePart> sentenceParts;
    private String sentence;

    public Sentence() {
        sentenceParts = new ArrayList<>();
    }

    public Sentence(String sentence) {
        this.sentenceParts = new ArrayList<>();
        this.sentence = sentence;
    }

    public List<SentencePart> getSentenceParts() {
        return sentenceParts;
    }

    public void setSentenceParts(List<SentencePart> sentenceParts) {
        this.sentenceParts = sentenceParts;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void addSentencePart(SentencePart sentencePart) {
        sentenceParts.add(sentencePart);
    }

    public void removeSentencePart(SentencePart sentencePart) {
        sentenceParts.remove(sentencePart);
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder();

        if (sentence != null) {
            stringBuilder.append(sentence);
        } else {
            for (Content content : sentenceParts) {
                stringBuilder.append(content.build());
            }
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

}