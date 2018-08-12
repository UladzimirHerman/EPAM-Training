package com.gmail.herman.uladzimir.service;

import com.gmail.herman.uladzimir.entity.Order;
import com.gmail.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link OrderService} contains specific methods
 * for entity {@link Order}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface OrderService extends GenericService<Order> {

    /**
     * Search all orders in archive
     *
     * @return list of orders
     * @throws ServiceException exception of service level
     */

    int countArchive() throws ServiceException;

    int countArchive(int userId) throws ServiceException;

    int countOpen() throws ServiceException;

    int countOpen(int userId) throws ServiceException;

    boolean isBasketExist(int userId) throws ServiceException;

    List<Order> findArchive(int offset, int limit) throws ServiceException;

    List<Order> findArchive(int userId, int offset, int limit) throws ServiceException;

    Order findBasket(int userId) throws ServiceException;

    List<Order> findOpen(int offset, int limit) throws ServiceException;

    List<Order> findOpen(int userId, int offset, int limit) throws ServiceException;

    List<Order> fillOrdersWithInfo(List<Order> orders) throws ServiceException;

}