package com.gmail.herman.uladzimir.service.impl;

import com.gmail.herman.uladzimir.dao.ProductDAO;
import com.gmail.herman.uladzimir.dao.impl.ProductDAOImpl;
import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.exception.DAOException;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductServiceImpl extends AbstractService<Product, ProductDAOImpl>
        implements ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

    @Override
    public ProductDAOImpl getObjectDAOImpl() {
        return new ProductDAOImpl();
    }

    @Override
    public int countForSale() throws ServiceException {
        ProductDAO productDAO = new ProductDAOImpl();
        int count;

        try {
            count = productDAO.countForSale();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return count;
    }

    @Override
    public List<Product> findForSale(int offset, int limit) throws ServiceException {
        ProductDAO productDAO = new ProductDAOImpl();
        List<Product> products;

        try {
            products = productDAO.findForSale(offset, limit);
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when finding products for sale: ", e);
            throw new ServiceException(e);
        }

        return products;
    }
}