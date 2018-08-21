package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.dao.GenericDAO;
import com.epam.herman.uladzimir.dao.impl.AbstractDAO;
import com.epam.herman.uladzimir.exception.DAOException;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.GenericService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Abstract class {@link AbstractService} is used for working with common methods
 * of DAO-level. This class realizes the common methods of service-classes and
 * defines abstract method, which determines a particular DAO-object.
 *
 * @param <T> type of entity
 * @param <E> type of DAO-class
 * @author Uladzimir Herman
 * @see AbstractDAO
 * @see GenericService
 */
public abstract class AbstractService<T, E extends AbstractDAO<T>>
        implements GenericService<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractService.class);

    /**
     * Receive the particular DAO-object
     *
     * @return particular DAO-object
     */
    protected abstract E getObjectDAOImpl();

    @Override
    public List<T> findAll(int offset, int limit) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();
        List<T> objects;

        try {
            objects = objectDAO.findAll(offset, limit);
            LOGGER.info("Successful search records");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when searching records: ", e);
            throw new ServiceException("Error in searching records", e);
        }

        return objects;
    }

    @Override
    public T findById(int id) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();
        T object;

        try {
            object = objectDAO.findById(id);
            LOGGER.info("Successful search by id");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when searching a record by id: ", e);
            throw new ServiceException("Error in searching a record by id", e);
        }

        return object;
    }

    @Override
    public void insert(T object) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();

        try {
            objectDAO.insert(object);
            LOGGER.info("Successful insert");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when inserting a record: ", e);
            throw new ServiceException("Error in inserting a record", e);
        }

    }

    @Override
    public void update(T object) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();

        try {
            objectDAO.update(object);
            LOGGER.info("Successful update");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when updating a record: ", e);
            throw new ServiceException("Error in updating a record", e);
        }

    }

    @Override
    public void deleteById(int id) throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();

        try {
            objectDAO.deleteById(id);
            LOGGER.info("Successful delete");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when deleting a record by id: ", e);
            throw new ServiceException("Error in deleting a record", e);
        }

    }

    @Override
    public int count() throws ServiceException {
        GenericDAO<T> objectDAO = getObjectDAOImpl();
        int count;

        try {
            count = objectDAO.count();
            LOGGER.info("Successful count");
        } catch (DAOException e) {
            LOGGER.error("DAOException occurred when counting records: ", e);
            throw new ServiceException("Error in counting records", e);
        }

        return count;
    }

}