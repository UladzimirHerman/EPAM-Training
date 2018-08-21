package com.epam.herman.uladzimir.validator;

import com.epam.herman.uladzimir.entity.OrderInfo;

/**
 * Class {@link OrderInfoValidator} is used to check
 * the data members of {@link OrderInfo}.
 *
 * @author Uladzimir Herman
 */
public class OrderInfoValidator {

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 5;

    /**
     * Check the order information
     *
     * @param orderInfo order information for checking
     * @return true, if correct or false, if incorrect
     */
    public boolean isOrderInfoCorrect(OrderInfo orderInfo) {
        return isQuantityCorrect(orderInfo.getQuantity());
    }

    /**
     * Check the quantity
     *
     * @param quantity quantity for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isQuantityCorrect(int quantity) {
        return quantity >= MIN_QUANTITY &&
                quantity <= MAX_QUANTITY;
    }

}