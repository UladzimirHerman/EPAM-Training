package com.epam.herman.uladzimir.dao;

import com.epam.herman.uladzimir.entity.Order;
import com.epam.herman.uladzimir.exception.DAOException;

import java.util.List;

/**
 * Interface {@link OrderDAO} contains special methods
 * for entity {@link Order}.
 *
 * @author Uladzimir Herman
 * @see GenericDAO
 */
public interface OrderDAO extends GenericDAO<Order> {

    /**
     * Counting archive orders
     *
     * @return quantity of archive orders
     * @throws DAOException exception of database level
     */
    int countArchive() throws DAOException;

    /**
     * Counting user's archive orders
     *
     * @param userId user's identifier
     * @return quantity of user's archive orders
     * @throws DAOException exception of database level
     */
    int countArchive(int userId) throws DAOException;

    /**
     * Counting open orders
     *
     * @return quantity of open orders
     * @throws DAOException exception of database level
     */
    int countOpen() throws DAOException;

    /**
     * Counting user's open orders
     *
     * @param userId user's identifier
     * @return quantity of user's open orders
     * @throws DAOException exception of database level
     */
    int countOpen(int userId) throws DAOException;

    /**
     * Check the existence of a user's basket
     *
     * @param userId user's identifier
     * @return true, if basket exists or false, if basket doesn't exist
     * @throws DAOException exception of database level
     */
    boolean isBasketExist(int userId) throws DAOException;

    /**
     * Search archive orders
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of archive orders
     * @throws DAOException exception of database level
     */
    List<Order> findArchive(int offset, int limit) throws DAOException;

    /**
     * Search user's archive orders
     *
     * @param userId user's identifier
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of user's archive orders
     * @throws DAOException exception of database level
     */
    List<Order> findArchive(int userId, int offset, int limit) throws DAOException;

    /**
     * Search user's basket
     *
     * @param userId user's identifier
     * @return user's basket
     * @throws DAOException exception of database level
     */
    Order findBasket(int userId) throws DAOException;

    /**
     * Search open orders
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of open orders
     * @throws DAOException exception of database level
     */
    List<Order> findOpen(int offset, int limit) throws DAOException;

    /**
     * Search user's open orders
     *
     * @param userId user's identifier
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of user's open orders
     * @throws DAOException exception of database level
     */
    List<Order> findOpen(int userId, int offset, int limit) throws DAOException;

}