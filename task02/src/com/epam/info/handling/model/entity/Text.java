package com.epam.info.handling.model.entity;

import com.epam.info.handling.model.Content;

import java.util.ArrayList;
import java.util.List;

public class Text implements Content {

    private List<TextPart> textParts;
    private String textPart;

    public Text() {
        textParts = new ArrayList<>();
    }

    public Text(String textPart) {
        this.textParts = new ArrayList<>();
        this.textPart = textPart;
    }

    public List<TextPart> getTextParts() {
        return textParts;
    }

    public void setTextParts(List<TextPart> textParts) {
        this.textParts = textParts;
    }

    public String getTextPart() {
        return textPart;
    }

    public void setTextPart(String textPart) {
        this.textPart = textPart;
    }

    public void addTextPart(TextPart textPart) {
        textParts.add(textPart);
    }

    public void removeTextPart(TextPart textPart) {
        textParts.remove(textPart);
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder();

        if (textPart != null) {
            stringBuilder.append(textPart);
        } else {
            for (Content content : textParts) {
                stringBuilder.append(content.build());
            }
        }

        return stringBuilder.toString();
    }

}