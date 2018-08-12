package com.gmail.herman.uladzimir.service.impl;

import com.gmail.herman.uladzimir.dao.GenericDAO;
import com.gmail.herman.uladzimir.dao.impl.AbstractDAO;
import com.gmail.herman.uladzimir.exception.DAOException;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.GenericService;

import java.util.List;

/**
 * Abstract class {@link AbstractService} is used for working with common
 * service-methods. This class realizes the common methods of service-classes
 * and defines abstract method, which determines a particular DAO-object.
 *
 * @param <T> type of entity
 * @param <E> type of DAO-class
 * @author Uladzimir Herman
 * @see AbstractDAO
 * @see GenericService
 */
public abstract class AbstractService<T, E extends AbstractDAO<T>>
        implements GenericService<T> {

    /**
     * Receive the particular DAO-object
     *
     * @return particular DAO-object
     */
    public abstract E getObjectDAOImpl();

    @Override
    public List<T> findAll(int offset, int limit) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();
        List<T> objects;

        try {
            objects = objectDAO.findAll(offset, limit);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return objects;
    }

    @Override
    public T findById(int id) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();
        T object;

        try {
            object = objectDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return object;
    }

    @Override
    public void insert(T object) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();

        try {
            objectDAO.insert(object);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(T object) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();

        try {
            objectDAO.update(object);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();

        try {
            objectDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int count() throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();
        int count;

        try {
            count = objectDAO.count();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return count;
    }

}