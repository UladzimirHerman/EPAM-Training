package com.epam.herman.uladzimir.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PaginationUtilTest {

    private int limit;
    private int page;
    private int expectedOffset;
    private int totalRows;
    private int expectedPage;

    public PaginationUtilTest
            (int limit, int page, int expectedOffset, int totalRows, int expectedPage) {
        this.page = page;
        this.totalRows = totalRows;
        this.limit = limit;
        this.expectedOffset = expectedOffset;
        this.expectedPage = expectedPage;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = {
                {10, 1, 0, 1, 1},
                {4, 12, 44, 29, 8},
                {8, 3, 16, 32, 4}};
        return Arrays.asList(data);
    }

    @Test
    public void paginationUtilTest() {
        assertEquals(expectedOffset, PaginationUtil.defineOffset(page, limit));
        assertEquals(expectedPage, PaginationUtil.definePageQuantity(totalRows, limit));
    }

}