package com.gmail.herman.uladzimir.util;

public class PaginationUtil {

    public static int defineOffset(int page, int limit) {
        return page * limit - limit;
    }

    public static int definePageQuantity(int totalRows, int limit) {
        return (int) Math.ceil((double) totalRows / limit);
    }

}