package com.epam.info.handling.model.parser;

import com.epam.info.handling.model.Content;
import com.epam.info.handling.model.entity.Mark;
import com.epam.info.handling.model.entity.Sentence;
import com.epam.info.handling.model.entity.Word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends Parser {

    private Sentence sentence;

    public SentenceParser() {
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    @Override
    public Content parse() {
        Pattern sentencePartPattern = Pattern.compile(resourceBundle.getString("WORD") +
                "|" + resourceBundle.getString("MARK"));
        Matcher sentencePartMatcher = sentencePartPattern.matcher(text);

        Pattern wordPattern = Pattern.compile(resourceBundle.getString("WORD"));
        Matcher wordMatcher;

        sentence = new Sentence();

        while (sentencePartMatcher.find()) {

            wordMatcher = wordPattern.matcher(sentencePartMatcher.group());

            if (wordMatcher.find()) {
                sentence.addSentencePart(new Word(sentencePartMatcher.group()));
            } else {
                sentence.addSentencePart(new Mark(sentencePartMatcher.group()));
            }

        }

        return sentence;
    }

}