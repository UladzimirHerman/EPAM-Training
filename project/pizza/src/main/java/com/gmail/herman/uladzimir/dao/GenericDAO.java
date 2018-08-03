package com.gmail.herman.uladzimir.dao;

import com.gmail.herman.uladzimir.exception.DAOException;

import java.util.List;

/**
 * Interface {@link GenericDAO} contains common methods
 * for all DAO-classes.
 *
 * @author Uladzimir Herman
 * @param <T> type of entity
 */
public interface GenericDAO<T> {

    /**
     * Search all objects
     * @return list of objects of a particular type
     * @throws DAOException exception of database level
     */
    List<T> findAll() throws DAOException;

    /**
     * Search object by identifier
     * @param id identifier for searching
     * @return object of a particular type
     * @throws DAOException exception of database level
     */
    T findById(int id) throws DAOException;

    /**
     * Insert new object in database
     * @param object new object for inserting
     * @throws DAOException exception of database level
     */
    void insert(T object) throws DAOException;

    /**
     * Update object in database
     * @param object updated object
     * @throws DAOException exception of database level
     */
    void update(T object) throws DAOException;

    /**
     * Delete object from database by identifier
     * @param id object's identifier for deleting
     * @throws DAOException exception of database level
     */
    void deleteById(int id) throws DAOException;

}