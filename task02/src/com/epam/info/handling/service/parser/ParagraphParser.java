package com.epam.info.handling.service.parser;

import com.epam.info.handling.model.entity.Component;
import com.epam.info.handling.model.entity.Composite;
import com.epam.info.handling.model.entity.Content;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends Parser {

    @Override
    public Content parse() {
        Pattern sentencePattern = Pattern.compile(resourceBundle.getString("SENTENCE"));
        Matcher sentenceMatcher = sentencePattern.matcher(text);

        Composite composite = new Composite();

        while (sentenceMatcher.find()) {

            if (next != null) {
                next.setText(sentenceMatcher.group());
                composite.add(next.parse());
            } else {
                composite.add(new Component(sentenceMatcher.group()));
            }

        }

        return composite;
    }

}