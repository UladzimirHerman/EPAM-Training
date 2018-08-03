package com.gmail.herman.uladzimir.dao;

import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.exception.DAOException;

import java.util.List;

/**
 * Interface {@link ProductDAO} contains specific methods
 * for entity {@link Product}.
 *
 * @author Uladzimir Herman
 * @see GenericDAO
 */
public interface ProductDAO extends GenericDAO<Product> {

    /**
     * Search all products, which are available for sale
     * @return list of products
     * @throws DAOException exception of database level
     */
    List<Product> findAllForSale() throws DAOException;

}