package com.epam.info.handling.service;

import com.epam.info.handling.model.entity.*;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextWorker {

    private static final Logger LOG = Logger.getLogger(TextWorker.class);

    private static final String VOWELS = "aeiouy";
    private static final int CALCULATION_ACCURACY = 10_000;

    private static final String REGEXP_SOURCE = "regexp";
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(REGEXP_SOURCE);

    public List<Content> getTextParts(Content content) {
        return ((Composite) content).getContentList();
    }

    public List<Content> getParagraphs(List<Content> textParts) {
        List<Content> paragraphs = new ArrayList<>();

        for (Content paragraph : textParts) {
            if (paragraph instanceof Composite) {
                paragraphs.add(paragraph);
            }
        }

        return paragraphs;
    }

    public List<Content> getSentences(List<Content> paragraphs) {
        List<Content> sentences = new ArrayList<>();

        for (Content paragraph : paragraphs) {
            sentences.addAll(((Composite) paragraph).getContentList());
        }

        return sentences;
    }

    public List<Content> getSentenceParts(List<Content> sentences) {
        List<Content> sentenceParts = new ArrayList<>();

        for (Content sentence : sentences) {
            sentenceParts.addAll(((Composite) sentence).getContentList());
        }

        return sentenceParts;
    }

    public List<String> getWords(List<Content> sentenceParts) {
        Pattern wordPattern = Pattern.compile(resourceBundle.getString("WORD"));
        Matcher wordMatcher;
        List<String> words = new ArrayList<>();

        for (Content part : sentenceParts) {
            wordMatcher = wordPattern.matcher(((Component) part).getComponent());

            if (wordMatcher.find()) {
                words.add(((Component) part).getComponent());
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

        LOG.info("Method printWordsByAlphabet successfully completed");

        return stringBuilder.toString();
    }

    private TreeMap<Character, TreeSet<String>> sortWordsByAlphabet(List<String> words) {
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

        LOG.info("Method printWordsByVowels successfully completed");

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