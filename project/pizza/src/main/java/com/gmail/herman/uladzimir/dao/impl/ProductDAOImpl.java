package com.gmail.herman.uladzimir.dao.impl;

import com.gmail.herman.uladzimir.dao.ProductDAO;
import com.gmail.herman.uladzimir.manager.SQLManager;
import com.gmail.herman.uladzimir.entity.Product;
import com.gmail.herman.uladzimir.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.herman.uladzimir.dao.SQLElement.*;

/**
 * Class {@link ProductDAOImpl} is used for interacting the entity {@link Product}
 * with database. This class implements common and its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractDAO
 * @see ProductDAO
 */
public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO {

    private static final Logger LOGGER = LogManager.getLogger(ProductDAOImpl.class);

    @Override
    protected String getFindAllQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_FIND_ALL);
    }

    @Override
    protected String getFindByIdQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_FIND_BY_ID);
    }

    @Override
    protected String getInsertQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_INSERT);
    }

    @Override
    protected String getUpdateQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_UPDATE);
    }

    @Override
    protected String getDeleteByIdQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_DELETE_BY_ID);
    }

    @Override
    protected String getCountQuery() {
        return SQLManager.getInstance().getSQL(PRODUCT_QUERY_COUNT);
    }

    @Override
    protected void getPreparedStatementInsert
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
    protected void getPreparedStatementUpdate
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
    protected List<Product> parseResult(ResultSet resultSet) throws DAOException {
        List<Product> products = new ArrayList<>();
        Product product;

        try {
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt(SQLManager.getInstance().getSQL(PRODUCT_FIELD_ID)));
                product.setName
                        (resultSet.getString(SQLManager.getInstance().getSQL(PRODUCT_FIELD_NAME)));
                product.setDescription
                        (resultSet.getString(SQLManager.getInstance().getSQL(PRODUCT_FIELD_DESCRIPTION)));
                product.setPrice
                        (resultSet.getBigDecimal(SQLManager.getInstance().getSQL(PRODUCT_FIELD_PRICE)));
                product.setPhoto
                        (resultSet.getString(SQLManager.getInstance().getSQL(PRODUCT_FIELD_PHOTO)));
                product.setSale
                        (resultSet.getBoolean(SQLManager.getInstance().getSQL(PRODUCT_FIELD_SALE)));
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred when parsing the result: ", e);
            throw new DAOException("Error in parsing ResultSet", e);
        }

        return products;
    }

    @Override
    public int countForSale() throws DAOException {
        return countObjects(SQLManager.getInstance().getSQL(PRODUCT_QUERY_COUNT_FOR_SALE));
    }

    @Override
    public List<Product> findForSale(int offset, int limit) throws DAOException {
        return findObjects
                (SQLManager.getInstance().getSQL(PRODUCT_QUERY_FIND_FOR_SALE), offset, limit);
    }

}