package com.gmail.herman.uladzimir.dao.impl;

import com.gmail.herman.uladzimir.dao.ProductDAO;
import com.gmail.herman.uladzimir.dao.SQLManager;
import com.gmail.herman.uladzimir.database.ConnectionPool;
import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.herman.uladzimir.dao.SQLElement.*;

/**
 * Class {@link ProductDAOImpl} is used for working with database.
 * This class realizes the specific methods of Product entity
 * and abstract methods necessary for executing common methods.
 *
 * @author Uladzimir Herman
 * @see AbstractDAO
 * @see ProductDAO
 */
public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO {

    private static final Logger LOGGER = LogManager.getLogger(ProductDAOImpl.class);

    @Override
    public String getFindAllQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_FIND_ALL);
    }

    @Override
    public String getFindByIdQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_FIND_BY_ID);
    }

    @Override
    public String getInsertQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_INSERT);
    }

    @Override
    public String getUpdateQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_UPDATE);
    }

    @Override
    public String getDeleteByIdQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_DELETE_BY_ID);
    }

    @Override
    public void getPreparedStatementInsert
            (PreparedStatement preparedStatement, Product product) throws DAOException {

        try {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setString(4, product.getPhoto());
            preparedStatement.setBoolean(5, product.isSale());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for inserting: ", e);
            throw new DAOException("Error with PreparedStatementInsert", e);
        }

    }

    @Override
    public void getPreparedStatementUpdate
            (PreparedStatement preparedStatement, Product product) throws DAOException {

        try {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setString(4, product.getPhoto());
            preparedStatement.setBoolean(5, product.isSale());
            preparedStatement.setInt(6, product.getId());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for updating: ", e);
            throw new DAOException("Error with PreparedStatementUpdate", e);
        }

    }

    @Override
    public List<Product> parseResult(ResultSet resultSet) throws DAOException {
        List<Product> products = new ArrayList<>();
        Product product;

        try {
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt(SQLManager.getInstance().getSQL(PRODUCT_FIELD_ID)));
                product.setName(resultSet.getString(SQLManager.getInstance().getSQL(PRODUCT_FIELD_NAME)));
                product.setDescription(resultSet.getString(SQLManager.getInstance().getSQL(PRODUCT_FIELD_DESCRIPTION)));
                product.setPrice(resultSet.getBigDecimal(SQLManager.getInstance().getSQL(PRODUCT_FIELD_PRICE)));
                product.setPhoto(resultSet.getString(SQLManager.getInstance().getSQL(PRODUCT_FIELD_PHOTO)));
                product.setSale(resultSet.getBoolean(SQLManager.getInstance().getSQL(PRODUCT_FIELD_SALE)));
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred when parsing the result: ", e);
            throw new DAOException("Error in parsing ResultSet", e);
        }

        return products;
    }

    @Override
    public List<Product> findAllForSale() throws DAOException {
        List<Product> products = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery
                     (SQLManager.getInstance().getSQL(PRODUCT_QUERY_FIND_ALL_FOR_SALE))) {
            products.addAll(parseResult(resultSet));
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when finding products for sale: ", e);
            throw new DAOException("Error in searching records", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().returnConnection(connection);
            }
        }

        return products;
    }

}