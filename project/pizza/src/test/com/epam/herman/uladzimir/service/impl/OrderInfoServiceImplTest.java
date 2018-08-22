package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.entity.Order;
import com.epam.herman.uladzimir.entity.OrderInfo;
import com.epam.herman.uladzimir.entity.Product;
import com.epam.herman.uladzimir.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderInfoServiceImplTest {

    private OrderInfoServiceImpl orderInfoService;

    public OrderInfoServiceImplTest() {
        orderInfoService = new OrderInfoServiceImpl();
    }

    @Test
    public void findAllTest() throws ServiceException {
        assertEquals(4, orderInfoService.findAll(0, 4).size());
    }

    @Test
    public void findByIdTest() throws ServiceException {
        assertNotNull(orderInfoService.findById(1));
    }

    @Test
    public void insertTest() throws ServiceException {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(104);
        Order order = new Order();
        order.setId(1);
        orderInfo.setOrder(order);
        Product product = new Product();
        product.setId(1);
        orderInfo.setProduct(product);
        orderInfo.setQuantity(2);
        orderInfoService.insert(orderInfo);
        assertEquals(orderInfo, orderInfoService.findById(104));
    }

    @Test
    public void updateTest() throws ServiceException {
        OrderInfo orderInfo = orderInfoService.findById(104);
        orderInfo.setQuantity(1);
        orderInfoService.update(orderInfo);
        assertEquals(orderInfo, orderInfoService.findById(104));
    }

    @Test(expected = ServiceException.class)
    public void deleteByIdTest() throws ServiceException {
        orderInfoService.deleteById(104);
        orderInfoService.findById(104);
    }

    @Test
    public void findByOrderIdTest() throws ServiceException {
        assertEquals(2, orderInfoService.findByOrderId(1).size());
    }

    @Test
    public void findFullInfoByOrderIdTest() throws ServiceException {
        assertEquals(2, orderInfoService.findFullInfoByOrderId(1).size());
    }

}