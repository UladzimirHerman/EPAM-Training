package com.epam.herman.uladzimir.dao;

import com.epam.herman.uladzimir.entity.Product;
import com.epam.herman.uladzimir.exception.DAOException;

import java.util.List;

/**
 * Interface {@link ProductDAO} contains special methods
 * for entity {@link Product}.
 *
 * @author Uladzimir Herman
 * @see GenericDAO
 */
public interface ProductDAO extends GenericDAO<Product> {

    /**
     * Counting products, which are available for sale
     *
     * @return quantity of products on sale
     * @throws DAOException exception of database level
     */
    int countForSale() throws DAOException;

    /**
     * Search all products, which are available for sale
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of products on sale
     * @throws DAOException exception of database level
     */
    List<Product> findForSale(int offset, int limit) throws DAOException;

}