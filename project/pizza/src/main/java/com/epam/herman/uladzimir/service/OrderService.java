package com.epam.herman.uladzimir.service;

import com.epam.herman.uladzimir.entity.Order;
import com.epam.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link OrderService} contains special methods for entity {@link Order}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface OrderService extends GenericService<Order> {

    /**
     * Counting archive orders
     *
     * @return quantity of archive orders
     * @throws ServiceException exception of service level
     */
    int countArchive() throws ServiceException;

    /**
     * Counting user's archive orders
     *
     * @param userId user's identifier
     * @return quantity of user's archive orders
     * @throws ServiceException exception of service level
     */
    int countArchive(int userId) throws ServiceException;

    /**
     * Counting open orders
     *
     * @return quantity of open orders
     * @throws ServiceException exception of service level
     */
    int countOpen() throws ServiceException;

    /**
     * Counting user's open orders
     *
     * @param userId user's identifier
     * @return quantity of user's open orders
     * @throws ServiceException exception of service level
     */
    int countOpen(int userId) throws ServiceException;

    /**
     * Check the existence of a user's basket
     *
     * @param userId user's identifier
     * @return true, if basket exists or false, if basket doesn't exist
     * @throws ServiceException exception of service level
     */
    boolean isBasketExist(int userId) throws ServiceException;

    /**
     * Search archive orders
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of archive orders
     * @throws ServiceException exception of service level
     */
    List<Order> findArchive(int offset, int limit) throws ServiceException;

    /**
     * Search full information about archive orders
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of archive orders
     * @throws ServiceException exception of service level
     */
    List<Order> findFullInfoArchive(int offset, int limit) throws ServiceException;

    /**
     * Search user's archive orders
     *
     * @param userId user's identifier
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of user's archive orders
     * @throws ServiceException exception of service level
     */
    List<Order> findArchive(int userId, int offset, int limit) throws ServiceException;

    /**
     * Search full information about user's archive orders
     *
     * @param userId user's identifier
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of user's archive orders
     * @throws ServiceException exception of service level
     */
    List<Order> findFullInfoArchive(int userId, int offset, int limit)
            throws ServiceException;

    /**
     * Search user's basket
     *
     * @param userId user's identifier
     * @return user's basket
     * @throws ServiceException exception of service level
     */
    Order findBasket(int userId) throws ServiceException;

    /**
     * Search open orders
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of open orders
     * @throws ServiceException exception of service level
     */
    List<Order> findOpen(int offset, int limit) throws ServiceException;

    /**
     * Search full information about open orders
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of open orders
     * @throws ServiceException exception of service level
     */
    List<Order> findFullInfoOpen(int offset, int limit) throws ServiceException;

    /**
     * Search user's open orders
     *
     * @param userId user's identifier
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of user's open orders
     * @throws ServiceException exception of service level
     */
    List<Order> findOpen(int userId, int offset, int limit) throws ServiceException;

    /**
     * Search full information about user's open orders
     *
     * @param userId user's identifier
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of user's open orders
     * @throws ServiceException exception of service level
     */
    List<Order> findFullInfoOpen(int userId, int offset, int limit)
            throws ServiceException;

}