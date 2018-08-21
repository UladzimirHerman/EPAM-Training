package com.epam.herman.uladzimir.validator;

/**
 * Class {@link PaginationValidator} contains the method for checking page number.
 *
 * @author Uladzimir Herman
 */
public class PaginationValidator {

    private static final int MIN_PAGE_VALUE = 1;

    /**
     * Check the page number
     *
     * @param pageNumber page number for checking
     * @return true, if page number more, than zero or false, if less, than one
     */
    public boolean isPageNumberCorrect(int pageNumber) {
        return pageNumber >= MIN_PAGE_VALUE;
    }

}