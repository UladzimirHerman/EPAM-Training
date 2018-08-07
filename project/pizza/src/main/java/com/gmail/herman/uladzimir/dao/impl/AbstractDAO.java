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
import static com.gmail.herman.uladzimir.dao.SQLElement.COMMON_QUERY_PART_LIMIT;

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
    public abstract String getFindAllQuery();

    /**
     * Receive the query for searching object by identifier
     *
     * @return particular query
     */
    public abstract String getFindByIdQuery();

    /**
     * Receive the query for inserting object
     *
     * @return particular query
     */
    public abstract String getInsertQuery();

    /**
     * Receive the query for updating object
     *
     * @return particular query
     */
    public abstract String getUpdateQuery();

    /**
     * Receive the query for deleting object by identifier
     *
     * @return particular query
     */
    public abstract String getDeleteByIdQuery();

    /**
     * Receive the query for records counting
     *
     * @return particular query
     */
    public abstract String getCountQuery();

    /**
     * Define prepared statement for inserting a specific object
     *
     * @param preparedStatement particular prepared statement
     * @param object            specific object
     * @throws DAOException exception of database level
     */
    public abstract void getPreparedStatementInsert
    (PreparedStatement preparedStatement, T object) throws DAOException;

    /**
     * Define prepared statement for updating a specific object
     *
     * @param preparedStatement particular prepared statement
     * @param object            specific object
     * @throws DAOException exception of database level
     */
    public abstract void getPreparedStatementUpdate
    (PreparedStatement preparedStatement, T object) throws DAOException;

    /**
     * Parse the result set
     *
     * @param resultSet result set from database
     * @return list of objects
     * @throws DAOException exception of database level
     */
    public abstract List<T> parseResult(ResultSet resultSet) throws DAOException;

    @Override
    public List<T> findAll() throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        List<T> objects = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getFindAllQuery())) {
            objects.addAll(parseResult(resultSet));
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when searching records: ", e);
            throw new DAOException("Error in searching records", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

        return objects;
    }

    @Override
    public List<T> findAll(int offset, int limit) throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        List<T> objects = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement
                (getFindAllQuery() + " " + SQLManager.getInstance().getSQL(COMMON_QUERY_PART_LIMIT))) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                objects.addAll(parseResult(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when searching records: ", e);
            throw new DAOException("Error in searching records", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

        return objects;
    }

    @Override
    public T findById(int id) throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        List<T> objects = new ArrayList<>();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getFindByIdQuery())) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                objects.addAll(parseResult(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when searching a record by id: ", e);
            throw new DAOException("Error in searching a record by id", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

        if (objects.size() != 1) {
            LOGGER.error("Incorrect result returned when finding by id");
            throw new DAOException("SQL-query return incorrect result");
        }

        return objects.get(0);
    }

    @Override
    public void insert(T object) throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getInsertQuery())) {
            getPreparedStatementInsert(preparedStatement, object);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when inserting a record: ", e);
            throw new DAOException("Error in inserting a record", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

    }

    @Override
    public void update(T object) throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getUpdateQuery())) {
            getPreparedStatementUpdate(preparedStatement, object);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when updating a record: ", e);
            throw new DAOException("Error in updating a record", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

    }

    @Override
    public void deleteById(int id) throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getDeleteByIdQuery())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when deleting a record by id: ", e);
            throw new DAOException("Error in deleting a record", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

    }

    @Override
    public int count() throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        int count = 0;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getCountQuery())) {

            if (resultSet.next()) {
                count = resultSet.getInt(SQLManager.getInstance().getSQL(COMMON_FIELD_TOTAL_ROWS));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when counting records: ", e);
            throw new DAOException("Error in counting records", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

        return count;
    }

}