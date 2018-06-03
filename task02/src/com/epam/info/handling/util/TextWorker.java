package com.epam.info.handling.util;

import com.epam.info.handling.model.entity.*;
import com.epam.info.handling.model.parser.Parser;
import com.epam.info.handling.model.parser.TextParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.*;

public class TextWorker {

    static {
        new DOMConfigurator().doConfigure("resource/log4j.xml",
                LogManager.getLoggerRepository());
    }

    private static final Logger LOG = Logger.getLogger(TextWorker.class);

    private static final String VOWELS = "aeiouy";
    private static final int CALCULATION_ACCURACY = 10_000;

    public List<String> receiveWordsFromText(Parser parser) {
        List<TextPart> textParts = ((TextParser) parser).getAllText().getTextParts();
        List<Sentence> sentences;
        List<SentencePart> sentenceParts;
        List<String> words = new ArrayList<>();

        for (TextPart textPart : textParts) {
            if (textPart instanceof Paragraph) {
                sentences = ((Paragraph) textPart).getSentences();
                for (Sentence sentence : sentences) {
                    sentenceParts = sentence.getSentenceParts();
                    for (SentencePart sentencePart : sentenceParts) {
                        if (sentencePart instanceof Word) {
                            words.add(((Word) sentencePart).getWord());
                        }
                    }
                }
            }
        }

        return words;
    }

    public String printWordsByAlphabet(List<String> words) {
        TreeMap<Character, TreeSet<String>> treeMap = sortWordsByAlphabet(words);
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Character, TreeSet<String>> entry : treeMap.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" - ");
            stringBuilder.append(entry.getValue());
            stringBuilder.append(System.getProperty("line.separator"));
        }

        LOG.info("method printWordsByAlphabet successfully completed");

        return stringBuilder.toString();
    }

    private TreeMap<Character, TreeSet<String>> sortWordsByAlphabet(List<String> words){
        TreeMap<Character, TreeSet<String>> treeMap = new TreeMap<>();
        char key;

        for (String word : words) {
            word = word.toLowerCase();
            key = word.charAt(0);

            if (!treeMap.containsKey(key)) {
                TreeSet<String> strings = new TreeSet<>();
                strings.add(word);
                treeMap.put(key, strings);
            } else {
                for (Map.Entry<Character, TreeSet<String>> entry : treeMap.entrySet()) {
                    if (entry.getKey() == key) {
                        TreeSet<String> newStrings = entry.getValue();
                        newStrings.add(word);
                        entry.setValue(newStrings);
                    }
                }
            }

        }

        return treeMap;
    }

    public String printWordsByVowels(List<String> words) {
        TreeMap<Integer, TreeSet<String>> treeMap = sortWordsByVowels(words);
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Integer, TreeSet<String>> entry : treeMap.entrySet()) {
            stringBuilder.append((double) entry.getKey() / CALCULATION_ACCURACY);
            stringBuilder.append(" - ");
            stringBuilder.append(entry.getValue());
            stringBuilder.append(System.getProperty("line.separator"));
        }

        LOG.info("method printWordsByVowels successfully completed");

        return stringBuilder.toString();
    }

    private TreeMap<Integer, TreeSet<String>> sortWordsByVowels(List<String> words) {
        TreeMap<Integer, TreeSet<String>> treeMap = new TreeMap<>();
        int key;

        for (String word : words) {
            word = word.toLowerCase();
            key = defineLetterRatio(word);

            if (!treeMap.containsKey(key)) {
                TreeSet<String> strings = new TreeSet<>();
                strings.add(word);
                treeMap.put(key, strings);
            } else {
                for (Map.Entry<Integer, TreeSet<String>> entry : treeMap.entrySet()) {
                    if (entry.getKey() == key) {
                        TreeSet<String> newStrings = entry.getValue();
                        newStrings.add(word);
                        entry.setValue(newStrings);
                    }
                }

            }

        }

        return treeMap;
    }

    private int defineLetterRatio(String word) {
        int count = 0;
        char[] letters = word.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            if (VOWELS.contains(letters[i] + "")) {
                count++;
            }
        }

        return CALCULATION_ACCURACY * count / letters.length;
    }

}