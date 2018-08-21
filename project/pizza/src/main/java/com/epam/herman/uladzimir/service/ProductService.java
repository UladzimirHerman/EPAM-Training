package com.epam.herman.uladzimir.service;

import com.epam.herman.uladzimir.entity.Product;
import com.epam.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link ProductService} contains special methods
 * for entity {@link Product}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface ProductService extends GenericService<Product> {

    /**
     * Counting products, which are available for sale
     *
     * @return quantity of products on sale
     * @throws ServiceException exception of service level
     */
    int countForSale() throws ServiceException;

    /**
     * Search all products, which are available for sale
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of products on sale
     * @throws ServiceException exception of service level
     */
    List<Product> findForSale(int offset, int limit) throws ServiceException;

}