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
    List<Order> findArchiveOrders() throws ServiceException;

    List<Order> findOpenOrders() throws ServiceException;

    List<Order> fillOrdersWithInfo(List<Order> orders) throws ServiceException;

}