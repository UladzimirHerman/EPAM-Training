package com.gmail.herman.uladzimir.validator;

import com.gmail.herman.uladzimir.entity.Product;

import java.math.BigDecimal;

public class ProductValidator {

    private static final int MIN_LENGTH_NAME = 5;
    private static final int MAX_LENGTH_NAME = 50;
    private static final int MIN_LENGTH_DESCRIPTION = 10;
    private static final int MAX_LENGTH_DESCRIPTION = 150;
    private static final BigDecimal MIN_VALUE_PRICE = new BigDecimal(0.00);
    private static final BigDecimal MAX_VALUE_PRICE = new BigDecimal(50.00);
    private static final int MAX_LENGTH_PHOTO = 100;

    public boolean isProductCorrect(Product product) {
        return isNameCorrect(product.getName()) &&
                isDescriptionCorrect(product.getDescription()) &&
                isPriceCorrect(product.getPrice()) &&
                isPhotoCorrect(product.getPhoto());
    }

    private boolean isNameCorrect(String name) {
        return name.length() >= MIN_LENGTH_NAME &&
                name.length() <= MAX_LENGTH_NAME;
    }

    private boolean isDescriptionCorrect(String description) {
        return description.length() >= MIN_LENGTH_DESCRIPTION &&
                description.length() <= MAX_LENGTH_DESCRIPTION;
    }

    private boolean isPriceCorrect(BigDecimal price) {
        return price.compareTo(MIN_VALUE_PRICE) >= 0 &&
                price.compareTo(MAX_VALUE_PRICE) <= 0;
    }

    private boolean isPhotoCorrect(String photo) {
        return !photo.isEmpty() &&
                photo.length() <= MAX_LENGTH_PHOTO;
    }

}