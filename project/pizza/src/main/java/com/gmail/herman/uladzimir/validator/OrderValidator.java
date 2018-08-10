package com.gmail.herman.uladzimir.validator;

public class OrderValidator {

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 5;

    public boolean isQuantityCorrect(int quantity) {
        return quantity >= MIN_QUANTITY &&
                quantity <= MAX_QUANTITY;
    }

}