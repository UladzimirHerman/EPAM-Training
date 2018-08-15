package com.gmail.herman.uladzimir.dao.impl;

import com.gmail.herman.uladzimir.dao.OrderInfoDAO;
import com.gmail.herman.uladzimir.dao.SQLManager;
import com.gmail.herman.uladzimir.database.ConnectionPool;
import com.gmail.herman.uladzimir.entity.Order;
import com.gmail.herman.uladzimir.entity.OrderInfo;
import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.herman.uladzimir.dao.SQLElement.*;

/**
 * Class {@link OrderInfoDAOImpl} is used for interacting the entity
 * {@link OrderInfo} with database. This class implements common and
 * its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractDAO
 * @see OrderInfoDAO
 */
public class OrderInfoDAOImpl extends AbstractDAO<OrderInfo> implements OrderInfoDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderInfoDAOImpl.class);

    @Override
    protected String getFindAllQuery() {
        return SQLManager.getInstance().getSQL(ORDER_INFO_QUERY_FIND_ALL);
    }

    @Override
    protected String getFindByIdQuery() {
        return SQLManager.getInstance().getSQL(ORDER_INFO_QUERY_FIND_BY_ID);
    }

    @Override
    protected String getInsertQuery() {
        return SQLManager.getInstance().getSQL(ORDER_INFO_QUERY_INSERT);
    }

    @Override
    protected String getUpdateQuery() {
        return SQLManager.getInstance().getSQL(ORDER_INFO_QUERY_UPDATE);
    }

    @Override
    protected String getDeleteByIdQuery() {
        return SQLManager.getInstance().getSQL(ORDER_INFO_QUERY_DELETE_BY_ID);
    }

    @Override
    protected String getCountQuery() {
        return SQLManager.getInstance().getSQL(ORDER_INFO_QUERY_COUNT);
    }

    @Override
    protected void getPreparedStatementInsert
            (PreparedStatement preparedStatement, OrderInfo orderInfo) throws DAOException {

        try {
            preparedStatement.setInt(1, orderInfo.getOrder().getId());
            preparedStatement.setInt(2, orderInfo.getProduct().getId());
            preparedStatement.setInt(3, orderInfo.getQuantity());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for inserting: ", e);
            throw new DAOException("Error with PreparedStatementInsert", e);
        }

    }

    @Override
    protected void getPreparedStatementUpdate
            (PreparedStatement preparedStatement, OrderInfo orderInfo) throws DAOException {

        try {
            preparedStatement.setInt(1, orderInfo.getQuantity());
            preparedStatement.setInt(2, orderInfo.getId());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for updating: ", e);
            throw new DAOException("Error with PreparedStatementUpdate", e);
        }

    }

    @Override
    protected List<OrderInfo> parseResult(ResultSet resultSet) throws DAOException {
        List<OrderInfo> orderInfoList = new ArrayList<>();
        OrderInfo orderInfo;

        try {
            while (resultSet.next()) {
                orderInfo = new OrderInfo();
                orderInfo.setId
                        (resultSet.getInt(SQLManager.getInstance().getSQL(ORDER_INFO_FIELD_ID)));

                Order order = new Order();
                order.setId
                        (resultSet.getInt(SQLManager.getInstance().getSQL(ORDER_INFO_FIELD_ORDER_ID)));
                orderInfo.setOrder(order);

                Product product = new Product();
                product.setId
                        (resultSet.getInt(SQLManager.getInstance().getSQL(ORDER_INFO_FIELD_PRODUCT_ID)));
                orderInfo.setProduct(product);

                orderInfo.setQuantity
                        (resultSet.getInt(SQLManager.getInstance().getSQL(ORDER_INFO_FIELD_QUANTITY)));
                orderInfoList.add(orderInfo);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred when parsing the result: ", e);
            throw new DAOException("Error in parsing ResultSet", e);
        }

        return orderInfoList;
    }

    @Override
    public List<OrderInfo> findByOrderId(int orderId) throws DAOException {
        List<OrderInfo> orderInfoList = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                (SQLManager.getInstance().getSQL(ORDER_INFO_QUERY_FIND_BY_ORDER_ID))) {
            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orderInfoList.addAll(parseResult(resultSet));
            }

            LOGGER.info("Successful search order info by order id");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when finding order info by order id: ", e);
            throw new DAOException("Error in searching records by order id", e);
        }

        return orderInfoList;
    }

}