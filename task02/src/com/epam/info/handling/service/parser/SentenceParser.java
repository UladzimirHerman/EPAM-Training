package com.epam.info.handling.service.parser;

import com.epam.info.handling.model.entity.Component;
import com.epam.info.handling.model.entity.Composite;
import com.epam.info.handling.model.entity.Content;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends Parser {

    @Override
    public Content parse() {
        Pattern sentencePartPattern = Pattern.compile(resourceBundle.getString("WORD") +
                "|" + resourceBundle.getString("MARK"));
        Matcher sentencePartMatcher = sentencePartPattern.matcher(text);

        Composite composite = new Composite();

        while (sentencePartMatcher.find()) {
            composite.add(new Component(sentencePartMatcher.group()));
        }

        return composite;
    }

}