package com.epam.herman.uladzimir.validator;

import com.epam.herman.uladzimir.entity.OrderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderInfoValidatorTest {

    private OrderInfoValidator orderInfoValidator;
    private OrderInfo orderInfo;
    private boolean expected;

    public OrderInfoValidatorTest(int quantity, boolean expected) {
        orderInfoValidator = new OrderInfoValidator();
        orderInfo = new OrderInfo();
        orderInfo.setQuantity(quantity);
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = {
                {3, true},
                {7, false},
                {0, false}};
        return Arrays.asList(data);
    }

    @Test
    public void isOrderInfoCorrectTest() {
        assertEquals(expected, orderInfoValidator.isOrderInfoCorrect(orderInfo));
    }

}