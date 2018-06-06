package com.epam.info.handling.service.parser;

import com.epam.info.handling.model.entity.Content;
import com.epam.info.handling.model.entity.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser extends Parser {

    @Override
    public Content parse() {
        Pattern partTextPattern = Pattern.compile(resourceBundle.getString("PARAGRAPH") +
                "|" + resourceBundle.getString("LISTING"));
        Matcher partTextMatcher = partTextPattern.matcher(text);

        Pattern paragraphPattern = Pattern.compile(resourceBundle.getString("PARAGRAPH"));
        Matcher paragraphMatcher;

        Composite composite = new Composite();

        while (partTextMatcher.find()) {
            paragraphMatcher = paragraphPattern.matcher(partTextMatcher.group());

            if (paragraphMatcher.find()) {

                if (next != null) {
                    next.setText(partTextMatcher.group());
                    composite.add(next.parse());
                } else {
                    composite.add(new Component(partTextMatcher.group()));
                }

            } else {
                composite.add(new Component(partTextMatcher.group()));
            }

        }

        return composite;
    }

}