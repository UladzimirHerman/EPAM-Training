package com.gmail.herman.uladzimir.service.impl;

import com.gmail.herman.uladzimir.dao.impl.ProductDAOImpl;
import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.service.ProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProductServiceImpl extends AbstractService<Product, ProductDAOImpl>
        implements ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

    @Override
    public ProductDAOImpl getObjectDAOImpl() {
        return new ProductDAOImpl();
    }

}