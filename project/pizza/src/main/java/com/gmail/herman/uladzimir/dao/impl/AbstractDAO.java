package com.gmail.herman.uladzimir.dao.impl;

import com.gmail.herman.uladzimir.dao.GenericDAO;
import com.gmail.herman.uladzimir.dao.SQLManager;
import com.gmail.herman.uladzimir.database.ConnectionPool;
import com.gmail.herman.uladzimir.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.herman.uladzimir.dao.SQLElement.COMMON_FIELD_TOTAL_ROWS;

/**
 * Abstract class {@link AbstractDAO} is used for working with database.
 * This class realizes the common methods of DAO-classes
 * and defines abstract methods necessary for their execution.
 *
 * @param <T> type of entity
 * @author Uladzimir Herman
 * @see GenericDAO
 */
public abstract class AbstractDAO<T> implements GenericDAO<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    /**
     * Receive the query for searching all objects
     *
     * @return particular query
     */
    protected abstract String getFindAllQuery();

    /**
     * Receive the query for searching object by identifier
     *
     * @return particular query
     */
    protected abstract String getFindByIdQuery();

    /**
     * Receive the query for inserting object
     *
     * @return particular query
     */
    protected abstract String getInsertQuery();

    /**
     * Receive the query for updating object
     *
     * @return particular query
     */
    protected abstract String getUpdateQuery();

    /**
     * Receive the query for deleting object by identifier
     *
     * @return particular query
     */
    protected abstract String getDeleteByIdQuery();

    /**
     * Receive the query for records counting
     *
     * @return particular query
     */
    protected abstract String getCountQuery();

    /**
     * Define prepared statement for inserting a specific object
     *
     * @param preparedStatement particular prepared statement
     * @param object            specific object
     * @throws DAOException exception of database level
     */
    protected abstract void getPreparedStatementInsert
    (PreparedStatement preparedStatement, T object) throws DAOException;

    /**
     * Define prepared statement for updating a specific object
     *
     * @param preparedStatement particular prepared statement
     * @param object            specific object
     * @throws DAOException exception of database level
     */
    protected abstract void getPreparedStatementUpdate
    (PreparedStatement preparedStatement, T object) throws DAOException;

    /**
     * Parse the result set
     *
     * @param resultSet result set from database
     * @return list of objects
     * @throws DAOException exception of database level
     */
    protected abstract List<T> parseResult(ResultSet resultSet) throws DAOException;

    /**
     * Search objects
     *
     * @param query  particular query for searching
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return list of objects
     * @throws DAOException exception of database level
     */
    protected List<T> findObjects(String query, int offset, int limit)
            throws DAOException {
        List<T> objects = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                objects.addAll(parseResult(resultSet));
            }

            LOGGER.info("Successful search records");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when searching records: ", e);
            throw new DAOException("Error in searching records", e);
        }

        return objects;
    }

    /**
     * Search object by integer-condition
     *
     * @param query     particular query for searching
     * @param condition condition for query
     * @return particular object
     * @throws DAOException exception of database level
     */
    protected T findObjectByCondition(String query, int condition) throws DAOException {
        List<T> objects = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, condition);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                objects.addAll(parseResult(resultSet));
            }

            if (objects.size() != 1) {
                LOGGER.error("Incorrect result returned when searching by condition. " +
                        "Record doesn't exist or exist more than one record");
                throw new DAOException("SQL-query return incorrect result");
            }

            LOGGER.info("Successful search by condition");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when searching a record by condition: ", e);
            throw new DAOException("Error in searching a record by condition", e);
        }

        return objects.get(0);
    }

    /**
     * Count objects
     *
     * @param query particular query for counting
     * @return quantity of records
     * @throws DAOException exception of database level
     */
    protected int countObjects(String query) throws DAOException {
        int count = 0;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                count = resultSet.getInt(SQLManager.getInstance().getSQL(COMMON_FIELD_TOTAL_ROWS));
            }

            LOGGER.info("Successful count");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when counting records: ", e);
            throw new DAOException("Error in counting records", e);
        }

        return count;
    }

    @Override
    public List<T> findAll(int offset, int limit) throws DAOException {
        return findObjects(getFindAllQuery(), offset, limit);
    }

    @Override
    public T findById(int id) throws DAOException {
        return findObjectByCondition(getFindByIdQuery(), id);
    }

    @Override
    public void insert(T object) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(getInsertQuery())) {
            getPreparedStatementInsert(preparedStatement, object);
            preparedStatement.executeUpdate();
            LOGGER.info("Successful insert");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when inserting a record: ", e);
            throw new DAOException("Error in inserting a record", e);
        }

    }

    @Override
    public void update(T object) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(getUpdateQuery())) {
            getPreparedStatementUpdate(preparedStatement, object);
            preparedStatement.executeUpdate();
            LOGGER.info("Successful update");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when updating a record: ", e);
            throw new DAOException("Error in updating a record", e);
        }

    }

    @Override
    public void deleteById(int id) throws DAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(getDeleteByIdQuery())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Successful delete");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when deleting a record by id: ", e);
            throw new DAOException("Error in deleting a record", e);
        }

    }

    @Override
    public int count() throws DAOException {
        return countObjects(getCountQuery());
    }

}