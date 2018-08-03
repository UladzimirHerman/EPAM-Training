package com.gmail.herman.uladzimir.service;

import com.gmail.herman.uladzimir.entity.OrderInfo;
import com.gmail.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link OrderInfoService} contains specific methods
 * for entity {@link OrderInfo}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface OrderInfoService extends GenericService<OrderInfo> {

    List<OrderInfo> findByOrderId(int id) throws ServiceException;

    List<OrderInfo> fillOrderInfoWithInfo(List<OrderInfo> orderInfoList)
            throws ServiceException;

}