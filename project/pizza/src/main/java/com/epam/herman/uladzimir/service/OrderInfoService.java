package com.epam.herman.uladzimir.service;

import com.epam.herman.uladzimir.entity.OrderInfo;
import com.epam.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link OrderInfoService} contains special methods
 * for entity {@link OrderInfo}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface OrderInfoService extends GenericService<OrderInfo> {

    /**
     * Search order information by order's identifier
     *
     * @param orderId order's identifier for searching
     * @return list of orders information
     * @throws ServiceException exception of service level
     */
    List<OrderInfo> findByOrderId(int orderId) throws ServiceException;

    /**
     * Search full information about the orders information by order's identifier
     *
     * @param orderId order's identifier
     * @return list of orders information
     * @throws ServiceException exception of service level
     */
    List<OrderInfo> findFullInfoByOrderId(int orderId) throws ServiceException;

}