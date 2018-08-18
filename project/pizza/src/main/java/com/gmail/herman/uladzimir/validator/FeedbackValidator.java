package com.gmail.herman.uladzimir.validator;

import com.gmail.herman.uladzimir.entity.Feedback;

/**
 * Class {@link FeedbackValidator} is used to check
 * the data members of {@link Feedback}.
 *
 * @author Uladzimir Herman
 */
public class FeedbackValidator {

    private static final int MIN_LENGTH_COMMENT = 10;
    private static final int MAX_LENGTH_COMMENT = 255;
    private static final int MIN_RATING_VALUE = 1;
    private static final int MAX_RATING_VALUE = 5;

    /**
     * Check the feedback
     *
     * @param feedback feedback for checking
     * @return true, if correct or false, if incorrect
     */
    public boolean isFeedbackCorrect(Feedback feedback) {
        return isCommentCorrect(feedback.getComment()) &&
                isRatingCorrect(feedback.getRating());
    }

    /**
     * Check the comment
     *
     * @param comment comment for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isCommentCorrect(String comment) {
        return comment.length() >= MIN_LENGTH_COMMENT &&
                comment.length() <= MAX_LENGTH_COMMENT;
    }

    /**
     * Check the rating
     *
     * @param rating rating for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isRatingCorrect(int rating) {
        return rating >= MIN_RATING_VALUE &&
                rating <= MAX_RATING_VALUE;
    }

}