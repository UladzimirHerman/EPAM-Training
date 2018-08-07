package com.gmail.herman.uladzimir.service.impl;

import com.gmail.herman.uladzimir.dao.OrderDAO;
import com.gmail.herman.uladzimir.dao.impl.OrderDAOImpl;
import com.gmail.herman.uladzimir.entity.Order;
import com.gmail.herman.uladzimir.exception.DAOException;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.OrderInfoService;
import com.gmail.herman.uladzimir.service.OrderService;
import com.gmail.herman.uladzimir.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl extends AbstractService<Order, OrderDAOImpl>
        implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public OrderDAOImpl getObjectDAOImpl() {
        return new OrderDAOImpl();
    }

    @Override
    public int countArchiveOrders() throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countArchiveOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return count;
    }

    @Override
    public int countOpenOrders() throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countOpenOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return count;
    }

    @Override
    public List<Order> findArchiveOrders(int offset, int limit) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders;

        try {
            orders = orderDAO.findArchiveOrders(offset, limit);

            if (!orders.isEmpty()) {
                orders = fillOrdersWithInfo(orders);
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding archive orders: ", e);
            throw new ServiceException(e);
        }

        return orders;
    }

    @Override
    public List<Order> findOpenOrders(int offset, int limit) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders;

        try {
            orders = orderDAO.findOpenOrders(offset, limit);

            if (!orders.isEmpty()) {
                orders = fillOrdersWithInfo(orders);
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding open orders: ", e);
            throw new ServiceException(e);
        }

        return orders;
    }

    @Override
    public List<Order> fillOrdersWithInfo(List<Order> orders) throws ServiceException {
        UserService userService = new UserServiceImpl();
        OrderInfoService orderInfoService = new OrderInfoServiceImpl();

        for (Order order : orders) {
            order.setUser(userService.findUserAndUserInfoById(order.getUser().getId()));
            order.setOrderInfoList(orderInfoService.fillOrderInfoWithInfo
                    (orderInfoService.findByOrderId(order.getId())));
        }

        return orders;
    }

}