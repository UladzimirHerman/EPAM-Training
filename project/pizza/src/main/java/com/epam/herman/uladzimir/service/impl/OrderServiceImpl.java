package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.dao.OrderDAO;
import com.epam.herman.uladzimir.dao.impl.OrderDAOImpl;
import com.epam.herman.uladzimir.entity.Order;
import com.epam.herman.uladzimir.entity.OrderInfo;
import com.epam.herman.uladzimir.exception.DAOException;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.OrderInfoService;
import com.epam.herman.uladzimir.service.OrderService;
import com.epam.herman.uladzimir.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class {@link OrderServiceImpl} is used for interacting the entity {@link Order}
 * with DAO level. This class implements common and its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractService
 * @see OrderDAOImpl
 * @see OrderService
 */
public class OrderServiceImpl extends AbstractService<Order, OrderDAOImpl>
        implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    /**
     * Filling an order list with information
     *
     * @param orders list of orders
     * @throws ServiceException exception of service level
     */
    private void fillWithInfo(List<Order> orders) throws ServiceException {
        UserService userService = new UserServiceImpl();
        OrderInfoService orderInfoService = new OrderInfoServiceImpl();

        for (Order order : orders) {
            order.setUser(userService.findFullInfoById(order.getUser().getId()));

            List<OrderInfo> orderInfoList =
                    orderInfoService.findFullInfoByOrderId(order.getId());
            order.setOrderInfoList(orderInfoList);
        }

        LOGGER.info("Successful fill a list of orders with info");
    }

    @Override
    protected OrderDAOImpl getObjectDAOImpl() {
        return new OrderDAOImpl();
    }

    @Override
    public int countArchive() throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countArchive();
            LOGGER.info("Successful count archive orders");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when counting archive orders: ", e);
            throw new ServiceException("Error in counting archive orders", e);
        }

        return count;
    }

    @Override
    public int countArchive(int userId) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countArchive(userId);
            LOGGER.info("Successful count user's archive orders");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when counting user's archive orders: ", e);
            throw new ServiceException("Error in counting user's archive orders", e);
        }

        return count;
    }

    @Override
    public int countOpen() throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countOpen();
            LOGGER.info("Successful count open orders");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when counting open orders: ", e);
            throw new ServiceException("Error in counting open orders", e);
        }

        return count;
    }

    @Override
    public int countOpen(int userId) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        int count;

        try {
            count = orderDAO.countOpen(userId);
            LOGGER.info("Successful count user's open orders");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when counting user's open orders: ", e);
            throw new ServiceException("Error in counting user's open orders", e);
        }

        return count;
    }

    @Override
    public boolean isBasketExist(int userId) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        boolean isBasketExist;

        try {
            isBasketExist = orderDAO.isBasketExist(userId);
            LOGGER.info("Successful check the existence of a basket");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when checking the existence of a basket: ", e);
            throw new ServiceException("Error in checking the existence of a basket", e);
        }

        return isBasketExist;
    }

    @Override
    public List<Order> findArchive(int offset, int limit) throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders;

        try {
            orders = orderDAO.findArchive(offset, limit);
            LOGGER.info("Successful search archive orders");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when searching archive orders: ", e);
            throw new ServiceException("Error in searching archive orders", e);
        }

        return orders;
    }

    @Override
    public List<Order> findFullInfoArchive(int offset, int limit)
            throws ServiceException {
        List<Order> orders = findArchive(offset, limit);

        if (!orders.isEmpty()) {
            fillWithInfo(orders);
        }

        LOGGER.info("Successful search list of archive orders with full info");
        return orders;
    }

    @Override
    public List<Order> findArchive(int userId, int offset, int limit)
            throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders;

        try {
            orders = orderDAO.findArchive(userId, offset, limit);
            LOGGER.info("Successful search user's archive orders");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when searching user's archive orders: ", e);
            throw new ServiceException("Error in searching user's archive orders", e);
        }

        return orders;
    }

    @Override
    public List<Order> findFullInfoArchive(int userId, int offset, int limit)
            throws ServiceException {
        List<Order> orders = findArchive(userId, offset, limit);

        if (!orders.isEmpty()) {
            fillWithInfo(orders);
        }

        LOGGER.info("Successful search list of user's archive orders with full info");
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
            LOGGER.info("Successful search open orders");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when searching open orders: ", e);
            throw new ServiceException("Error in searching open orders", e);
        }

        return orders;
    }

    @Override
    public List<Order> findFullInfoOpen(int offset, int limit) throws ServiceException {
        List<Order> orders = findOpen(offset, limit);

        if (!orders.isEmpty()) {
            fillWithInfo(orders);
        }

        LOGGER.info("Successful search list of open orders with full info");
        return orders;
    }

    @Override
    public List<Order> findOpen(int userId, int offset, int limit)
            throws ServiceException {
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders;

        try {
            orders = orderDAO.findOpen(userId, offset, limit);
            LOGGER.info("Successful search user's open orders");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when searching user's open orders: ", e);
            throw new ServiceException("Error in searching user's open orders", e);
        }

        return orders;
    }

    @Override
    public List<Order> findFullInfoOpen(int userId, int offset, int limit)
            throws ServiceException {
        List<Order> orders = findOpen(userId, offset, limit);

        if (!orders.isEmpty()) {
            fillWithInfo(orders);
        }

        LOGGER.info("Successful search list of user's open orders with full info");
        return orders;
    }

}