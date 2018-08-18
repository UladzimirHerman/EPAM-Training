package com.gmail.herman.uladzimir.validator;

import com.gmail.herman.uladzimir.entity.Product;

import java.math.BigDecimal;

/**
 * Class {@link ProductValidator} is used to check
 * the data members of {@link Product}.
 *
 * @author Uladzimir Herman
 */
public class ProductValidator {

    private static final int MIN_LENGTH_NAME = 5;
    private static final int MAX_LENGTH_NAME = 50;
    private static final int MIN_LENGTH_DESCRIPTION = 10;
    private static final int MAX_LENGTH_DESCRIPTION = 150;
    private static final BigDecimal MIN_VALUE_PRICE = new BigDecimal(0.00);
    private static final BigDecimal MAX_VALUE_PRICE = new BigDecimal(50.00);
    private static final int MAX_LENGTH_PHOTO = 100;

    /**
     * Check the product
     *
     * @param product product for checking
     * @return true, if correct or false, if incorrect
     */
    public boolean isProductCorrect(Product product) {
        return isNameCorrect(product.getName()) &&
                isDescriptionCorrect(product.getDescription()) &&
                isPriceCorrect(product.getPrice()) &&
                isPhotoCorrect(product.getPhoto());
    }

    /**
     * Check the name
     *
     * @param name name for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isNameCorrect(String name) {
        return name.length() >= MIN_LENGTH_NAME &&
                name.length() <= MAX_LENGTH_NAME;
    }

    /**
     * Check the description
     *
     * @param description description for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isDescriptionCorrect(String description) {
        return description.length() >= MIN_LENGTH_DESCRIPTION &&
                description.length() <= MAX_LENGTH_DESCRIPTION;
    }

    /**
     * Check the price
     *
     * @param price price for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isPriceCorrect(BigDecimal price) {
        return price.compareTo(MIN_VALUE_PRICE) >= 0 &&
                price.compareTo(MAX_VALUE_PRICE) <= 0;
    }

    /**
     * Check the length of the photo name
     *
     * @param photo photo name for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isPhotoCorrect(String photo) {
        return !photo.isEmpty() &&
                photo.length() <= MAX_LENGTH_PHOTO;
    }

}