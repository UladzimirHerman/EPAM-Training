package com.gmail.herman.uladzimir.validator;

import com.gmail.herman.uladzimir.entity.Feedback;

public class FeedbackValidator {

    private static final int MIN_LENGTH_COMMENT = 10;
    private static final int MAX_LENGTH_COMMENT = 255;
    private static final int MIN_RATING_VALUE = 1;
    private static final int MAX_RATING_VALUE = 5;

    public boolean isFeedbackCorrect(Feedback feedback) {
        return isCommentCorrect(feedback.getComment()) &&
                isRatingCorrect(feedback.getRating());
    }

    private boolean isCommentCorrect(String comment) {
        return comment.length() >= MIN_LENGTH_COMMENT &&
                comment.length() <= MAX_LENGTH_COMMENT;
    }

    private boolean isRatingCorrect(int rating) {
        return rating >= MIN_RATING_VALUE &&
                rating <= MAX_RATING_VALUE;
    }

}