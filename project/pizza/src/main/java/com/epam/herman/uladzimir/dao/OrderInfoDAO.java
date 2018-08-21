package com.epam.herman.uladzimir.dao;

import com.epam.herman.uladzimir.entity.OrderInfo;
import com.epam.herman.uladzimir.exception.DAOException;

import java.util.List;

/**
 * Interface {@link OrderInfoDAO} contains special methods
 * for entity {@link OrderInfo}.
 *
 * @author Uladzimir Herman
 * @see GenericDAO
 */
public interface OrderInfoDAO extends GenericDAO<OrderInfo> {

    /**
     * Search order information by order's identifier
     *
     * @param orderId order's identifier for searching
     * @return list of orders information
     * @throws DAOException exception of database level
     */
    List<OrderInfo> findByOrderId(int orderId) throws DAOException;

}