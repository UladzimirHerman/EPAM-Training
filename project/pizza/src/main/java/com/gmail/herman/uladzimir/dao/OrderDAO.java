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

    int countArchiveOrders() throws DAOException;

    int countOpenOrders() throws DAOException;

    List<Order> findArchiveOrders(int offset, int limit) throws DAOException;

    List<Order> findOpenOrders(int offset, int limit) throws DAOException;

}