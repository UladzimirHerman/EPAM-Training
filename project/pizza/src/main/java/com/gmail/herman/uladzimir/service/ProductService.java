package com.gmail.herman.uladzimir.service;

import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link ProductService} contains specific methods
 * for entity {@link Product}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface ProductService extends GenericService<Product> {

    int countForSale() throws ServiceException;

    List<Product> findForSale(int offset, int limit) throws ServiceException;

}