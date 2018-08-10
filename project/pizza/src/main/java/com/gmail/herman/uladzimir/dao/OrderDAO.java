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

    int countArchive() throws DAOException;

    int countArchive(int userId) throws DAOException;

    int countOpen() throws DAOException;

    int countOpen(int userId) throws DAOException;

    boolean isBasketExist(int userId) throws DAOException;

    List<Order> findArchive(int offset, int limit) throws DAOException;

    List<Order> findArchive(int offset, int limit, int userId) throws DAOException;

    Order findBasket(int userId) throws DAOException;

    List<Order> findOpen(int offset, int limit) throws DAOException;

    List<Order> findOpen(int offset, int limit, int userId) throws DAOException;

}