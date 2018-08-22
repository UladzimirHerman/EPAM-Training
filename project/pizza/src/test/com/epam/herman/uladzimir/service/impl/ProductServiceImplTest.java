package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.entity.Product;
import com.epam.herman.uladzimir.exception.ServiceException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductServiceImplTest {

    private ProductServiceImpl productService;

    public ProductServiceImplTest() {
        productService = new ProductServiceImpl();
    }

    @Test
    public void findAllTest() throws ServiceException {
        assertEquals(4, productService.findAll(0, 4).size());
    }

    @Test
    public void findByIdTest() throws ServiceException {
        assertNotNull(productService.findById(1));
    }

    @Test
    public void insertTest() throws ServiceException {
        Product product = new Product();
        product.setId(17);
        product.setName("Correct name");
        product.setDescription("Correct description");
        product.setPrice(new BigDecimal(15.75));
        product.setPhoto("Correct photo name");
        product.setSale(false);
        productService.insert(product);
        assertEquals(product, productService.findById(17));
    }

    @Test
    public void updateTest() throws ServiceException {
        Product product = productService.findById(17);
        product.setName("Updated name");
        product.setDescription("Updated description");
        product.setPhoto("Updated photo name");
        product.setSale(true);
        productService.update(product);
        assertEquals(product, productService.findById(17));
    }

    @Test(expected = ServiceException.class)
    public void deleteByIdTest() throws ServiceException {
        productService.deleteById(17);
        productService.findById(17);
    }

    @Test
    public void countTest() throws ServiceException {
        assertEquals(16, productService.count());
    }

    @Test
    public void countForSaleTest() throws ServiceException {
        assertEquals(16, productService.countForSale());
    }

    @Test
    public void findForSaleTest() throws ServiceException {
        assertEquals(4, productService.findForSale(0, 4).size());
    }

}