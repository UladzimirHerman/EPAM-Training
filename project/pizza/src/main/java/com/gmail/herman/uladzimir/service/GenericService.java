package com.gmail.herman.uladzimir.service;

import com.gmail.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link GenericService} contains common methods
 * for all service-classes.
 *
 * @param <T> type of entity
 * @author Uladzimir Herman
 */
public interface GenericService<T> {

    /**
     * Search all objects
     *
     * @return list of objects of a particular type
     * @throws ServiceException exception of service level
     */
    List<T> findAll() throws ServiceException;

    /**
     * Search all objects
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of objects of a particular type
     * @throws ServiceException exception of service level
     */
    List<T> findAll(int offset, int limit) throws ServiceException;

    /**
     * Search object by identifier
     *
     * @param id identifier for searching
     * @return object of a particular type
     * @throws ServiceException exception of service level
     */
    T findById(int id) throws ServiceException;

    /**
     * Insert new object
     *
     * @param object new object for inserting
     * @throws ServiceException exception of service level
     */
    void insert(T object) throws ServiceException;

    /**
     * Update object
     *
     * @param object updated object
     * @throws ServiceException exception of service level
     */
    void update(T object) throws ServiceException;

    /**
     * Delete object by identifier
     *
     * @param id object's identifier for deleting
     * @throws ServiceException exception of service level
     */
    void deleteById(int id) throws ServiceException;

    /**
     * Records counting
     *
     * @return quantity of records
     * @throws ServiceException exception of service level
     */
    int count() throws ServiceException;

}