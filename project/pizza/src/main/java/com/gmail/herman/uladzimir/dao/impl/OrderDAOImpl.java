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

/**
 * Class {@link OrderDAOImpl} is used for interacting the entity {@link Order}
 * with database. This class implements common and its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractDAO
 * @see OrderDAO
 */
public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);

    /**
     * Count orders by integer-condition
     *
     * @param query     particular query for counting
     * @param condition condition for query
     * @return quantity of records
     * @throws DAOException exception of database level
     */
    private int countByCondition(String query, int condition) throws DAOException {
        int count = 0;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, condition);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(SQLManager.getInstance().getSQL(COMMON_FIELD_TOTAL_ROWS));
                }
            }

            LOGGER.info("Successful count by condition");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when counting by condition: ", e);
            throw new DAOException("Error in counting records", e);
        }

        return count;
    }

    /**
     * Search orders by integer-condition
     *
     * @param query     particular query for searching
     * @param condition condition for query
     * @param offset    offset of the sample beginning
     * @param limit     number of requested records
     * @return list of orders
     * @throws DAOException exception of database level
     */
    private List<Order> findByCondition(String query, int condition, int offset, int limit)
            throws DAOException {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, condition);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orders.addAll(parseResult(resultSet));
            }

            LOGGER.info("Successful search orders by condition");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when searching orders by condition: ", e);
            throw new DAOException("Error in searching records", e);
        }

        return orders;
    }

    @Override
    protected String getFindAllQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_ALL);
    }

    @Override
    protected String getFindByIdQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_BY_ID);
    }

    @Override
    protected String getInsertQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_INSERT);
    }

    @Override
    protected String getUpdateQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_UPDATE);
    }

    @Override
    protected String getDeleteByIdQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_DELETE_BY_ID);
    }

    @Override
    protected String getCountQuery() {
        return SQLManager.getInstance().getSQL(ORDER_QUERY_COUNT);
    }

    @Override
    protected void getPreparedStatementInsert
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
    protected void getPreparedStatementUpdate
            (PreparedStatement preparedStatement, Order order) throws DAOException {

        try {
            preparedStatement.setTimestamp(1, new Timestamp(order.getDate().getTime()));
            preparedStatement.setString(2, order.getOrderStatus().name());
            preparedStatement.setInt(3, order.getId());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for updating: ", e);
            throw new DAOException("Error with PreparedStatementUpdate", e);
        }

    }

    @Override
    protected List<Order> parseResult(ResultSet resultSet) throws DAOException {
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
    public int countArchive() throws DAOException {
        return countObjects(SQLManager.getInstance().getSQL(ORDER_QUERY_COUNT_ARCHIVE));
    }

    @Override
    public int countArchive(int userId) throws DAOException {
        return countByCondition
                (SQLManager.getInstance().getSQL(ORDER_QUERY_COUNT_ARCHIVE_USER), userId);
    }

    @Override
    public int countOpen() throws DAOException {
        return countObjects(SQLManager.getInstance().getSQL(ORDER_QUERY_COUNT_OPEN));
    }

    @Override
    public int countOpen(int userId) throws DAOException {
        return countByCondition
                (SQLManager.getInstance().getSQL(ORDER_QUERY_COUNT_OPEN_USER), userId);
    }

    @Override
    public boolean isBasketExist(int userId) throws DAOException {
        int count = 0;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     (SQLManager.getInstance().getSQL(ORDER_QUERY_COUNT_BASKET))) {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(SQLManager.getInstance().getSQL(COMMON_FIELD_TOTAL_ROWS));
                }
            }

            if (count > 1) {
                LOGGER.error("Incorrect result returned when counting basket by user id. " +
                        "Exist more than one record");
                throw new DAOException("SQL-query return incorrect result");
            }

            LOGGER.info("Successful check the existence of a basket");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when counting basket by user id: ", e);
            throw new DAOException("Error in counting records", e);
        }

        return count == 1;
    }

    @Override
    public List<Order> findArchive(int offset, int limit) throws DAOException {
        return findObjects
                (SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_ARCHIVE), offset, limit);
    }

    @Override
    public List<Order> findArchive(int userId, int offset, int limit) throws DAOException {
        return findByCondition
                (SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_ARCHIVE_USER), userId, offset, limit);
    }

    @Override
    public Order findBasket(int userId) throws DAOException {
        return findObjectByCondition
                (SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_BASKET), userId);
    }

    @Override
    public List<Order> findOpen(int offset, int limit) throws DAOException {
        return findObjects
                (SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_OPEN), offset, limit);
    }

    @Override
    public List<Order> findOpen(int userId, int offset, int limit) throws DAOException {
        return findByCondition
                (SQLManager.getInstance().getSQL(ORDER_QUERY_FIND_OPEN_USER), userId, offset, limit);
    }

}