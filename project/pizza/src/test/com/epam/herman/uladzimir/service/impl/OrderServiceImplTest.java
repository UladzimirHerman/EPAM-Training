package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.entity.Order;
import com.epam.herman.uladzimir.entity.OrderStatus;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.ServiceException;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    private OrderServiceImpl orderService;

    public OrderServiceImplTest() {
        orderService = new OrderServiceImpl();
    }

    @Test
    public void findAllTest() throws ServiceException {
        assertEquals(4, orderService.findAll(0, 4).size());
    }

    @Test
    public void findByIdTest() throws ServiceException {
        assertNotNull(orderService.findById(1));
    }

    @Test
    public void insertTest() throws ServiceException {
        Order order = new Order();
        order.setId(64);
        order.setDate(new Date());
        User user = new User();
        user.setId(1);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.NEW);
        orderService.insert(order);
        Order orderNew = orderService.findById(64);
        order.setDate(orderNew.getDate());
        assertEquals(order, orderNew);
    }

    @Test
    public void updateTest() throws ServiceException {
        Order order = orderService.findById(64);
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        orderService.update(order);
        assertEquals(order, orderService.findById(64));
    }

    @Test(expected = ServiceException.class)
    public void deleteByIdTest() throws ServiceException {
        orderService.deleteById(64);
        orderService.findById(64);
    }

    @Test
    public void countTest() throws ServiceException {
        assertEquals(63, orderService.count());
    }

    @Test
    public void countArchiveTest() throws ServiceException {
        assertEquals(59, orderService.countArchive());
    }

    @Test
    public void countArchiveByUserIdTest() throws ServiceException {
        assertEquals(3, orderService.countArchive(4));
    }

    @Test
    public void countOpenTest() throws ServiceException {
        assertEquals(3, orderService.countOpen());
    }

    @Test
    public void countOpenByUserIdTest() throws ServiceException {
        assertEquals(0, orderService.countOpen(4));
    }

    @Test
    public void isBasketExistTest() throws ServiceException {
        assertFalse(orderService.isBasketExist(4));
    }

    @Test
    public void findArchiveTest() throws ServiceException {
        assertEquals(4, orderService.findArchive(0, 4).size());
    }

    @Test
    public void findFullInfoArchiveTest() throws ServiceException {
        assertEquals(4, orderService.findFullInfoArchive(0, 4).size());
    }

    @Test
    public void findArchiveByUserIdTest() throws ServiceException {
        assertEquals(3, orderService.findArchive(4, 0, 4).size());
    }

    @Test
    public void findFullInfoArchiveByUserIdTest() throws ServiceException {
        assertEquals(3, orderService.findFullInfoArchive(4, 0, 4).size());
    }

    @Test
    public void findBasketTest() throws ServiceException {
        assertNotNull(orderService.findBasket(9));
    }

    @Test
    public void findOpenTest() throws ServiceException {
        assertEquals(3, orderService.findOpen(0, 4).size());
    }

    @Test
    public void findFullInfoOpenTest() throws ServiceException {
        assertEquals(3, orderService.findFullInfoOpen(0, 4).size());
    }

    @Test
    public void findOpenByUserIdTest() throws ServiceException {
        assertEquals(0, orderService.findOpen(4, 0, 4).size());
    }

    @Test
    public void findFullInfoOpenByUserIdTest() throws ServiceException {
        assertEquals(0, orderService.findFullInfoOpen(4, 0, 4).size());
    }

}