package com.epam.herman.uladzimir.util;

/**
 * Class {@link PaginationUtil} is used to define the pagination parameters.
 *
 * @author Uladzimir Herman
 */
public class PaginationUtil {

    /**
     * Define the necessary offset
     *
     * @param page  page number
     * @param limit items on the page
     * @return starting position for getting entities
     */
    public static int defineOffset(int page, int limit) {
        return page * limit - limit;
    }

    /**
     * Define the page quantity
     *
     * @param totalRows total number of records
     * @param limit     items on the page
     * @return page quantity
     */
    public static int definePageQuantity(int totalRows, int limit) {
        return (int) Math.ceil((double) totalRows / limit);
    }

}