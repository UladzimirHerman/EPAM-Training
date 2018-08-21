package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.dao.OrderInfoDAO;
import com.epam.herman.uladzimir.dao.impl.OrderInfoDAOImpl;
import com.epam.herman.uladzimir.entity.OrderInfo;
import com.epam.herman.uladzimir.exception.DAOException;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.OrderInfoService;
import com.epam.herman.uladzimir.service.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class {@link OrderInfoServiceImpl} is used for interacting the entity
 * {@link OrderInfo} with DAO level. This class implements common and
 * its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractService
 * @see OrderInfoDAOImpl
 * @see OrderInfoService
 */
public class OrderInfoServiceImpl extends AbstractService<OrderInfo, OrderInfoDAOImpl>
        implements OrderInfoService {

    private static final Logger LOGGER = LogManager.getLogger(OrderInfoServiceImpl.class);

    /**
     * Filling an order information list with information
     *
     * @param orderInfoList order information list
     * @throws ServiceException exception of service level
     */
    private void fillWithInfo(List<OrderInfo> orderInfoList) throws ServiceException {
        ProductService productService = new ProductServiceImpl();

        for (OrderInfo orderInfo : orderInfoList) {
            orderInfo.setProduct(productService.findById(orderInfo.getProduct().getId()));
        }

        LOGGER.info("Successful fill a list of order info with info");
    }

    @Override
    protected OrderInfoDAOImpl getObjectDAOImpl() {
        return new OrderInfoDAOImpl();
    }

    @Override
    public List<OrderInfo> findByOrderId(int orderId) throws ServiceException {
        OrderInfoDAO orderInfoDAO = new OrderInfoDAOImpl();
        List<OrderInfo> orderInfoList;

        try {
            orderInfoList = orderInfoDAO.findByOrderId(orderId);
            LOGGER.info("Successful search list of order info by order id");
        } catch (DAOException e) {
            LOGGER.error
                    ("DAOException occurred when searching list of order info by order id: ", e);
            throw new ServiceException("Error in searching records", e);
        }

        return orderInfoList;
    }

    @Override
    public List<OrderInfo> findFullInfoByOrderId(int orderId) throws ServiceException {
        List<OrderInfo> orderInfoList = findByOrderId(orderId);

        if (!orderInfoList.isEmpty()) {
            fillWithInfo(orderInfoList);
        }

        LOGGER.info("Successful search a list of feedback with full info");
        return orderInfoList;
    }

}