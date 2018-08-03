package com.gmail.herman.uladzimir.dao;

import com.gmail.herman.uladzimir.entity.Order;
import com.gmail.herman.uladzimir.exception.DAOException;

import java.util.List;

/**
 * Interface {@link OrderDAO} contains specific methods
 * for entity {@link Order}.
 *
 * @author Uladzimir Herman
 * @see GenericDAO
 */
public interface OrderDAO extends GenericDAO<Order> {

    /**
     * Search all orders in archive
     * @return list of orders
     * @throws DAOException exception of database level
     */
    List<Order> findArchiveOrders() throws DAOException;

    List<Order> findOpenOrders() throws DAOException;

}