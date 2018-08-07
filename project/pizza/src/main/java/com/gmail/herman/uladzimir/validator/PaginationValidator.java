package com.gmail.herman.uladzimir.validator;

public class PaginationValidator {

    private static final int MIN_OFFSET_VALUE = 0;

    public boolean isPageNumberCorrect(int pageNumber) {
        return pageNumber > MIN_OFFSET_VALUE;
    }

}