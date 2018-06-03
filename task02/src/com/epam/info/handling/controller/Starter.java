package com.epam.info.handling.controller;

import com.epam.info.handling.model.entity.Text;
import com.epam.info.handling.model.parser.ParagraphParser;
import com.epam.info.handling.model.parser.SentenceParser;
import com.epam.info.handling.model.parser.TextParser;
import com.epam.info.handling.util.FileWorker;
import com.epam.info.handling.util.TextWorker;

import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Starter {

    static {
        new DOMConfigurator().doConfigure("resource/log4j.xml",
                LogManager.getLoggerRepository());
    }

    private static final Logger LOG = Logger.getLogger(Starter.class);

    public static void main(String[] args) {
        LOG.info("application startup");

        FileWorker fileWorker = new FileWorker();

        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);

        try {
            textParser.setText(fileWorker.readFile());

            Text text = (Text) textParser.parse();
            fileWorker.writeFile("rebuild_text.txt", text.build());

            TextWorker textWorker = new TextWorker();
            fileWorker.writeFile("words_by_alphabet.txt",
                    textWorker.printWordsByAlphabet(textWorker.receiveWordsFromText(textParser)));
            fileWorker.writeFile("words_by_vowels.txt",
                    textWorker.printWordsByVowels(textWorker.receiveWordsFromText(textParser)));

            LOG.info("application successfully completed");
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

    }
}