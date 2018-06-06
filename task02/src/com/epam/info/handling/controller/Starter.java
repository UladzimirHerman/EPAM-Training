package com.epam.info.handling.controller;

import com.epam.info.handling.model.entity.Content;
import com.epam.info.handling.service.FileWorker;
import com.epam.info.handling.service.TextWorker;
import com.epam.info.handling.service.parser.ParagraphParser;
import com.epam.info.handling.service.parser.Parser;
import com.epam.info.handling.service.parser.SentenceParser;

import java.io.IOException;
import java.util.List;

import com.epam.info.handling.service.parser.TextParser;
import org.apache.log4j.Logger;

public class Starter {

    private static final Logger LOG = Logger.getLogger(Starter.class);

    public static void main(String[] args) {
        LOG.info("Application startup");

        FileWorker fileWorker = new FileWorker();

        Parser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);

        try {
            textParser.setText(fileWorker.readFile());

            Content content = textParser.parse();

            fileWorker.writeFile("rebuild_text.txt", content.build());

            TextWorker textWorker = new TextWorker();
            List<String> words = textWorker.getWords(textWorker.getSentenceParts(textWorker.
                    getSentences(textWorker.getParagraphs(textWorker.getTextParts(content)))));

            fileWorker.writeFile("words_by_alphabet.txt",
                    textWorker.printWordsByAlphabet(words));
            fileWorker.writeFile("words_by_vowels.txt",
                    textWorker.printWordsByVowels(words));

            LOG.info("Application successfully completed");
        } catch (IOException e) {
            LOG.error("There was a problem with the file system ", e);
        }

    }
}