package com.gmail.herman.uladzimir.validator;

import com.gmail.herman.uladzimir.entity.News;

/**
 * Class {@link NewsValidator} is used to check the data members of {@link News}.
 *
 * @author Uladzimir Herman
 */
public class NewsValidator {

    private static final int MIN_LENGTH_TITLE = 5;
    private static final int MAX_LENGTH_TITLE = 50;
    private static final int MIN_LENGTH_CONTENT = 10;
    private static final int MAX_LENGTH_CONTENT = 255;
    private static final int MAX_LENGTH_PHOTO = 100;

    /**
     * Check news
     *
     * @param news news for checking
     * @return true, if correct or false, if incorrect
     */
    public boolean isNewsCorrect(News news) {
        return isTitleCorrect(news.getTitle()) &&
                isContentCorrect(news.getContent()) &&
                isPhotoCorrect(news.getPhoto());
    }

    /**
     * Check the title
     *
     * @param title title for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isTitleCorrect(String title) {
        return title.length() >= MIN_LENGTH_TITLE &&
                title.length() <= MAX_LENGTH_TITLE;
    }

    /**
     * Check the content
     *
     * @param content content for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isContentCorrect(String content) {
        return content.length() >= MIN_LENGTH_CONTENT &&
                content.length() <= MAX_LENGTH_CONTENT;
    }

    /**
     * Check the photo
     *
     * @param photo photo for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isPhotoCorrect(String photo) {
        return !photo.isEmpty() &&
                photo.length() <= MAX_LENGTH_PHOTO;
    }

}