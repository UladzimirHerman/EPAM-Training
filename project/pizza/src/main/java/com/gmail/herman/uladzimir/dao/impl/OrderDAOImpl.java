package com.gmail.herman.uladzimir.dao.impl;

import com.gmail.herman.uladzimir.dao.OrderDAO;
import com.gmail.herman.uladzimir.dao.SQLManager;
import com.gmail.herman.uladzimir.database.ConnectionPool;
import com.gmail.herman.uladzimir.entity.Order;
import com.gmail.herman.uladzimir.entity.OrderStatus;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.herman.uladzimir.dao.SQLElement.*;

public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);

    @Override
    public String getFindAllQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_ALL);
    }

    @Override
    public String getFindByIdQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_BY_ID);
    }

    @Override
    public String getInsertQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_INSERT);
    }

    @Override
    public String getUpdateQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_UPDATE);
    }

    @Override
    public String getDeleteByIdQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_DELETE_BY_ID);
    }

    @Override
    public String getCountQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_COUNT);
    }

    @Override
    public void getPreparedStatementInsert
            (PreparedStatement preparedStatement, Order order) throws DAOException {

        try {
            preparedStatement.setTimestamp(1, new Timestamp(order.getDate().getTime()));
            preparedStatement.setInt(2, order.getUser().getId());
            preparedStatement.setString(3, order.getOrderStatus().name());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for inserting: ", e);
            throw new DAOException("Error with PreparedStatementInsert", e);
        }

    }

    @Override
    public void getPreparedStatementUpdate
            (PreparedStatement preparedStatement, Order order) throws DAOException {

        try {
            preparedStatement.setString(1, order.getOrderStatus().name());
            preparedStatement.setInt(2, order.getId());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for updating: ", e);
            throw new DAOException("Error with PreparedStatementUpdate", e);
        }

    }

    @Override
    public List<Order> parseResult(ResultSet resultSet) throws DAOException {
        List<Order> orders = new ArrayList<>();
        Order order;

        try {
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt(SQLManager.getInstance().getSQL(ORDER_FIELD_ID)));
                order.setDate(new Date(resultSet.getTimestamp
                        (SQLManager.getInstance().getSQL(ORDER_FIELD_DATE)).getTime()));

                User user = new User();
                user.setId(resultSet.getInt(SQLManager.getInstance().getSQL(ORDER_FIELD_USER_ID)));
                order.setUser(user);

                order.setOrderStatus(OrderStatus.valueOf
                        (resultSet.getString(SQLManager.getInstance().getSQL(ORDER_FIELD_STATUS))));
                orders.add(order);
            }
        } catch (SQLException | IllegalArgumentException e) {
            LOGGER.error("Exception occurred when parsing the result: ", e);
            throw new DAOException("Error in parsing ResultSet", e);
        }

        return orders;
    }

    @Override
    public int countArchiveOrders() throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        int count = 0;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLManager.getInstance().getSQL(ORDER_QUERY_COUNT_ARCHIVE))) {

            if (resultSet.next()) {
                count = resultSet.getInt(SQLManager.getInstance().getSQL(COMMON_FIELD_TOTAL_ROWS));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when counting archive orders: ", e);
            throw new DAOException("Error in counting records", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

        return count;
    }

    @Override
    public int countOpenOrders() throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        int count = 0;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLManager.getInstance().getSQL(ORDER_QUERY_COUNT_OPEN))) {

            if (resultSet.next()) {
                count = resultSet.getInt(SQLManager.getInstance().getSQL(COMMON_FIELD_TOTAL_ROWS));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when counting open orders: ", e);
            throw new DAOException("Error in counting records", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

        return count;
    }

    @Override
    public List<Order> findArchiveOrders(int offset, int limit) throws DAOException {
        List<Order> orders = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement
                (SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_ARCHIVE) + " " +
                        SQLManager.getInstance().getSQL(COMMON_QUERY_PART_LIMIT))) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orders.addAll(parseResult(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when finding archive orders: ", e);
            throw new DAOException("Error in searching records", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

        return orders;
    }

    @Override
    public List<Order> findOpenOrders(int offset, int limit) throws DAOException {
        List<Order> orders = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement
                (SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_OPEN) + " " +
                        SQLManager.getInstance().getSQL(COMMON_QUERY_PART_LIMIT))) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orders.addAll(parseResult(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when finding open orders: ", e);
            throw new DAOException("Error in searching records", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

        return orders;
    }

}