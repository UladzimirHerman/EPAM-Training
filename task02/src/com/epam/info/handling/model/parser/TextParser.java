package com.epam.info.handling.model.parser;

import com.epam.info.handling.model.Content;
import com.epam.info.handling.model.entity.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser extends Parser {

    private Text allText;

    public TextParser() {
    }

    public Text getAllText() {
        return allText;
    }

    public void setAllText(Text allText) {
        this.allText = allText;
    }

    @Override
    public Content parse() {
        Pattern partTextPattern = Pattern.compile(resourceBundle.getString("PARAGRAPH") +
                "|" + resourceBundle.getString("LISTING"));
        Matcher partTextMatcher = partTextPattern.matcher(text);

        Pattern paragraphPattern = Pattern.compile(resourceBundle.getString("PARAGRAPH"));
        Matcher paragraphMatcher;

        allText = new Text();

        while (partTextMatcher.find()) {
            paragraphMatcher = paragraphPattern.matcher(partTextMatcher.group());

            if (paragraphMatcher.find()) {

                if (next != null) {
                    next.setText(partTextMatcher.group());
                    allText.addTextPart((Paragraph) next.parse());
                } else {
                    allText.addTextPart(new Paragraph(partTextMatcher.group()));
                }

            } else {
                allText.addTextPart(new Listing(partTextMatcher.group()));
            }

        }

        return allText;
    }

}