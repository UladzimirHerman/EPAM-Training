package com.epam.herman.uladzimir.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PaginationValidatorTest {

    private PaginationValidator paginationValidator;
    private int pageNumber;
    private boolean expected;

    public PaginationValidatorTest(int pageNumber, boolean expected) {
        paginationValidator = new PaginationValidator();
        this.pageNumber = pageNumber;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = {
                {1, true},
                {7, true},
                {0, false}};
        return Arrays.asList(data);
    }

    @Test
    public void isPageNumberCorrectTest() {
        assertEquals(expected, paginationValidator.isPageNumberCorrect(pageNumber));
    }

}