package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.dao.ProductDAO;
import com.epam.herman.uladzimir.dao.impl.ProductDAOImpl;
import com.epam.herman.uladzimir.entity.Product;
import com.epam.herman.uladzimir.exception.DAOException;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class {@link ProductServiceImpl} is used for interacting the entity
 * {@link Product} with DAO level. This class implements common and
 * its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractService
 * @see ProductDAOImpl
 * @see ProductService
 */
public class ProductServiceImpl extends AbstractService<Product, ProductDAOImpl>
        implements ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

    @Override
    protected ProductDAOImpl getObjectDAOImpl() {
        return new ProductDAOImpl();
    }

    @Override
    public int countForSale() throws ServiceException {
        ProductDAO productDAO = new ProductDAOImpl();
        int count;

        try {
            count = productDAO.countForSale();
            LOGGER.info("Successful count products for sale");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when counting products for sale: ", e);
            throw new ServiceException("Error in counting products for sale", e);
        }

        return count;
    }

    @Override
    public List<Product> findForSale(int offset, int limit) throws ServiceException {
        ProductDAO productDAO = new ProductDAOImpl();
        List<Product> products;

        try {
            products = productDAO.findForSale(offset, limit);
            LOGGER.info("Successful search products for sale");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when searching products for sale: ", e);
            throw new ServiceException("Error in searching products for sale", e);
        }

        return products;
    }

}