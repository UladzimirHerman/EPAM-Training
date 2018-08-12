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
    public int countArchive() throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countArchive();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return count;
    }

    @Override
    public int countArchive(int userId) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countArchive(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return count;
    }

    @Override
    public int countOpen() throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countOpen();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return count;
    }

    @Override
    public int countOpen(int userId) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countOpen(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return count;
    }

    @Override
    public boolean isBasketExist(int userId) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();

        try {
            return orderDAO.isBasketExist(userId);
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when checking the existence of a basket: ", e);
            throw new ServiceException(e);
        }

    }

    @Override
    public List<Order> findArchive(int offset, int limit) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders;

        try {
            orders = orderDAO.findArchive(offset, limit);

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
    public List<Order> findArchive(int userId, int offset, int limit) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders;

        try {
            orders = orderDAO.findArchive(userId, offset, limit);

            if (!orders.isEmpty()) {
                orders = fillOrdersWithInfo(orders);
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding archive orders by user id: ", e);
            throw new ServiceException(e);
        }

        return orders;
    }

    @Override
    public Order findBasket(int userId) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        Order order;

        try {
            order = orderDAO.findBasket(userId);
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding user's basket: ", e);
            throw new ServiceException(e);
        }

        return order;
    }

    @Override
    public List<Order> findOpen(int offset, int limit) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders;

        try {
            orders = orderDAO.findOpen(offset, limit);

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
    public List<Order> findOpen(int userId, int offset, int limit) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders;

        try {
            orders = orderDAO.findOpen(userId, offset, limit);

            if (!orders.isEmpty()) {
                orders = fillOrdersWithInfo(orders);
            }

        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding open orders by user id: ", e);
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