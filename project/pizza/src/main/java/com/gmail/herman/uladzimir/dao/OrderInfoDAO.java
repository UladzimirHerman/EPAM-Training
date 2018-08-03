package com.gmail.herman.uladzimir.dao;

import com.gmail.herman.uladzimir.entity.OrderInfo;
import com.gmail.herman.uladzimir.exception.DAOException;

import java.util.List;

/**
 * Interface {@link OrderInfoDAO} contains specific methods
 * for entity {@link OrderInfo}.
 *
 * @author Uladzimir Herman
 * @see GenericDAO
 */
public interface OrderInfoDAO extends GenericDAO<OrderInfo> {

    /**
     * Search order information by order's identifier
     * @param id identifier for searching
     * @return list of orders information
     * @throws DAOException exception of database level
     */
    List<OrderInfo> findByOrderId(int id) throws DAOException;

}