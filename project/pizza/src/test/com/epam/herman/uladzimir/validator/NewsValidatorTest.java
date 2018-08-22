package com.epam.herman.uladzimir.validator;

import com.epam.herman.uladzimir.entity.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NewsValidatorTest {

    private NewsValidator newsValidator;
    private News news;
    private boolean expected;

    public NewsValidatorTest
            (String title, String content, String photo, boolean expected) {
        newsValidator = new NewsValidator();
        news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setPhoto(photo);
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = {
                {"Correct title", "Correct content", "Correct photo", true},
                {"Bad", "Correct content", "Correct photo", false},
                {"Correct title", "Bad", "Correct photo", false},
                {"Correct title", "Correct content", "", false}};
        return Arrays.asList(data);
    }

    @Test
    public void isNewsCorrectTest() {
        assertEquals(expected, newsValidator.isNewsCorrect(news));
    }

}