package com.gmail.herman.uladzimir.validator;

import com.gmail.herman.uladzimir.entity.News;

public class NewsValidator {

    private static final int MIN_LENGTH_TITLE = 5;
    private static final int MAX_LENGTH_TITLE = 50;
    private static final int MIN_LENGTH_CONTENT = 10;
    private static final int MAX_LENGTH_CONTENT = 255;
    private static final int MAX_LENGTH_PHOTO = 100;

    public boolean isNewsCorrect(News news) {
        return isTitleCorrect(news.getTitle()) &&
                isContentCorrect(news.getContent()) &&
                isPhotoCorrect(news.getPhoto());
    }

    private boolean isTitleCorrect(String title) {
        return title.length() >= MIN_LENGTH_TITLE &&
                title.length() <= MAX_LENGTH_TITLE;
    }

    private boolean isContentCorrect(String content) {
        return content.length() >= MIN_LENGTH_CONTENT &&
                content.length() <= MAX_LENGTH_CONTENT;
    }

    private boolean isPhotoCorrect(String photo) {
        return !photo.isEmpty() &&
                photo.length() <= MAX_LENGTH_PHOTO;
    }

}