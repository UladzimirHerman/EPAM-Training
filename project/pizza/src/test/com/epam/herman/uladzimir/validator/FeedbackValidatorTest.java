package com.epam.herman.uladzimir.validator;

import com.epam.herman.uladzimir.entity.Feedback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FeedbackValidatorTest {

    private FeedbackValidator feedbackValidator;
    private Feedback feedback;
    private boolean expected;

    public FeedbackValidatorTest(String comment, int rating, boolean expected) {
        feedbackValidator = new FeedbackValidator();
        feedback = new Feedback();
        feedback.setComment(comment);
        feedback.setRating(rating);
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = {
                {"Correct comment", 5, true},
                {"Bad", 5, false},
                {"Correct comment", 10, false}};
        return Arrays.asList(data);
    }

    @Test
    public void isFeedbackCorrectTest() {
        assertEquals(expected, feedbackValidator.isFeedbackCorrect(feedback));
    }

}