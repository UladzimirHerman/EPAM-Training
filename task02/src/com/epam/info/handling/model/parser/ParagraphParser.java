package com.epam.info.handling.model.parser;

import com.epam.info.handling.model.Content;
import com.epam.info.handling.model.entity.Paragraph;
import com.epam.info.handling.model.entity.Sentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends Parser {

    private Paragraph paragraph;

    public ParagraphParser() {
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    @Override
    public Content parse() {
        Pattern sentencePattern = Pattern.compile(resourceBundle.getString("SENTENCE"));
        Matcher sentenceMatcher = sentencePattern.matcher(text);

        paragraph = new Paragraph();

        while (sentenceMatcher.find()) {

            if (next != null) {
                next.setText(sentenceMatcher.group());
                paragraph.addSentence((Sentence) next.parse());
            } else {
                paragraph.addSentence(new Sentence(sentenceMatcher.group()));
            }

        }

        return paragraph;
    }

}