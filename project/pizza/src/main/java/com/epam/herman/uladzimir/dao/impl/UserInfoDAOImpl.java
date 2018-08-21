package com.epam.herman.uladzimir.dao.impl;

import com.epam.herman.uladzimir.manager.SQLManager;
import com.epam.herman.uladzimir.dao.UserInfoDAO;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.entity.UserInfo;
import com.epam.herman.uladzimir.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.herman.uladzimir.dao.SQLElement.*;

/**
 * Class {@link UserInfoDAOImpl} is used for interacting the entity
 * {@link UserInfo} with database. This class implements common and
 * its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractDAO
 * @see UserInfoDAO
 */
public class UserInfoDAOImpl extends AbstractDAO<UserInfo> implements UserInfoDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserInfoDAOImpl.class);

    @Override
    protected String getFindAllQuery() {
        return SQLManager.getInstance().getSQL(USER_INFO_QUERY_FIND_ALL);
    }

    @Override
    protected String getFindByIdQuery() {
        return SQLManager.getInstance().getSQL(USER_INFO_QUERY_FIND_BY_ID);
    }

    @Override
    protected String getInsertQuery() {
        return SQLManager.getInstance().getSQL(USER_INFO_QUERY_INSERT);
    }

    @Override
    protected String getUpdateQuery() {
        return SQLManager.getInstance().getSQL(USER_INFO_QUERY_UPDATE);
    }

    @Override
    protected String getDeleteByIdQuery() {
        return SQLManager.getInstance().getSQL(USER_INFO_QUERY_DELETE_BY_ID);
    }

    @Override
    protected String getCountQuery() {
        return SQLManager.getInstance().getSQL(USER_INFO_QUERY_COUNT);
    }

    @Override
    protected void getPreparedStatementInsert
            (PreparedStatement preparedStatement, UserInfo userInfo) throws DAOException {

        try {
            preparedStatement.setString(1, userInfo.getName());
            preparedStatement.setString(2, userInfo.getSurname());
            preparedStatement.setString(3, userInfo.getPhone());
            preparedStatement.setString(4, userInfo.getCity());
            preparedStatement.setString(5, userInfo.getStreet());
            preparedStatement.setString(6, userInfo.getBuilding());
            preparedStatement.setString(7, userInfo.getHousing());
            preparedStatement.setString(8, userInfo.getApartment());
            preparedStatement.setString(9, userInfo.getNote());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for inserting: ", e);
            throw new DAOException("Error with PreparedStatementInsert", e);
        }

    }

    @Override
    protected void getPreparedStatementUpdate
            (PreparedStatement preparedStatement, UserInfo userInfo) throws DAOException {

        try {
            preparedStatement.setString(1, userInfo.getName());
            preparedStatement.setString(2, userInfo.getSurname());
            preparedStatement.setString(3, userInfo.getPhone());
            preparedStatement.setString(4, userInfo.getCity());
            preparedStatement.setString(5, userInfo.getStreet());
            preparedStatement.setString(6, userInfo.getBuilding());
            preparedStatement.setString(7, userInfo.getHousing());
            preparedStatement.setString(8, userInfo.getApartment());
            preparedStatement.setString(9, userInfo.getNote());
            preparedStatement.setInt(10, userInfo.getId());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for updating: ", e);
            throw new DAOException("Error with PreparedStatementUpdate", e);
        }

    }

    @Override
    protected List<UserInfo> parseResult(ResultSet resultSet) throws DAOException {
        List<UserInfo> userInfoList = new ArrayList<>();
        UserInfo userInfo;

        try {
            while (resultSet.next()) {
                userInfo = new UserInfo();
                userInfo.setId
                        (resultSet.getInt(SQLManager.getInstance().getSQL(USER_INFO_FIELD_ID)));
                userInfo.setName
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_INFO_FIELD_NAME)));
                userInfo.setSurname
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_INFO_FIELD_SURNAME)));
                userInfo.setPhone
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_INFO_FIELD_PHONE)));
                userInfo.setCity
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_INFO_FIELD_CITY)));
                userInfo.setStreet
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_INFO_FIELD_STREET)));
                userInfo.setBuilding
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_INFO_FIELD_BUILDING)));
                userInfo.setHousing
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_INFO_FIELD_HOUSING)));
                userInfo.setApartment
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_INFO_FIELD_APARTMENT)));
                userInfo.setNote
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_INFO_FIELD_NOTE)));
                userInfoList.add(userInfo);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred when parsing the result: ", e);
            throw new DAOException("Error in parsing ResultSet", e);
        }

        return userInfoList;
    }

    /**
     * This method is override in {@link UserDAOImpl} because there is an one-to-one
     * relationship between entities {@link User} and {@link UserInfo}. Thus, the
     * standard implementation of the method doesn't fit. So, insert-method doesn't
     * supported here.
     */
    @Override
    public void insert(UserInfo userInfo) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /**
     * This method is override in {@link UserDAOImpl} because there is an one-to-one
     * relationship between entities {@link User} and {@link UserInfo}. Thus, the
     * standard implementation of the method doesn't fit. So, delete-method doesn't
     * supported here.
     */
    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Operation not supported");
    }

}