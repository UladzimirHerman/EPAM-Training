package com.gmail.herman.uladzimir.service.impl;

import com.gmail.herman.uladzimir.dao.OrderInfoDAO;
import com.gmail.herman.uladzimir.dao.impl.OrderInfoDAOImpl;
import com.gmail.herman.uladzimir.entity.OrderInfo;
import com.gmail.herman.uladzimir.exception.DAOException;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.OrderInfoService;
import com.gmail.herman.uladzimir.service.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderInfoServiceImpl extends AbstractService<OrderInfo, OrderInfoDAOImpl>
        implements OrderInfoService {

    private static final Logger LOGGER = LogManager.getLogger(OrderInfoServiceImpl.class);

    @Override
    public OrderInfoDAOImpl getObjectDAOImpl() {
        return new OrderInfoDAOImpl();
    }

    @Override
    public List<OrderInfo> findByOrderId(int id) throws ServiceException {
        OrderInfoDAO orderInfoDAO = new OrderInfoDAOImpl();
        List<OrderInfo> orderInfoList;

        try {
            orderInfoList = orderInfoDAO.findByOrderId(id);
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding orders info by order id: ", e);
            throw new ServiceException(e);
        }

        return orderInfoList;
    }

    @Override
    public List<OrderInfo> fillOrderInfoWithInfo(List<OrderInfo> orderInfoList)
            throws ServiceException {
        ProductService productService = new ProductServiceImpl();

        for (OrderInfo orderInfo : orderInfoList) {
            orderInfo.setProduct(productService.findById(orderInfo.getProduct().getId()));
        }

        return orderInfoList;
    }

}