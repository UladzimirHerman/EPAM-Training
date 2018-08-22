package com.epam.herman.uladzimir.validator;

import com.epam.herman.uladzimir.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ProductValidatorTest {

    private ProductValidator productValidator;
    private Product product;
    private boolean expected;

    public ProductValidatorTest
            (String name, String description, BigDecimal price, String photo, boolean expected) {
        productValidator = new ProductValidator();
        product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setPhoto(photo);
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = {
                {"Correct name", "Correct description", new BigDecimal(10.00), "Correct photo", true},
                {"Bad", "Correct description", new BigDecimal(10.00), "Correct photo", false},
                {"Correct name", "Bad", new BigDecimal(10.00), "Correct photo", false},
                {"Correct name", "Correct description", new BigDecimal(50.15), "Correct photo", false},
                {"Correct name", "Correct description", new BigDecimal(10.00), "", false}};
        return Arrays.asList(data);
    }

    @Test
    public void isProductCorrectTest() {
        assertEquals(expected, productValidator.isProductCorrect(product));
    }

}