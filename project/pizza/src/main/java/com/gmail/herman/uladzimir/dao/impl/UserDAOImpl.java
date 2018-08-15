package com.gmail.herman.uladzimir.dao.impl;

import com.gmail.herman.uladzimir.manager.SQLManager;
import com.gmail.herman.uladzimir.dao.UserDAO;
import com.gmail.herman.uladzimir.database.ConnectionPool;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserInfo;
import com.gmail.herman.uladzimir.entity.UserRole;
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
 * Class {@link UserDAOImpl} is used for interacting the entity {@link User}
 * with database. This class implements common and its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractDAO
 * @see UserDAO
 */
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    @Override
    protected String getFindAllQuery() {
        return SQLManager.getInstance().getSQL(USER_QUERY_FIND_ALL);
    }

    @Override
    protected String getFindByIdQuery() {
        return SQLManager.getInstance().getSQL(USER_QUERY_FIND_BY_ID);
    }

    @Override
    protected String getInsertQuery() {
        return SQLManager.getInstance().getSQL(USER_QUERY_INSERT);
    }

    @Override
    protected String getUpdateQuery() {
        return SQLManager.getInstance().getSQL(USER_QUERY_UPDATE);
    }

    @Override
    protected String getDeleteByIdQuery() {
        return SQLManager.getInstance().getSQL(USER_QUERY_DELETE_BY_ID);
    }

    @Override
    protected String getCountQuery() {
        return SQLManager.getInstance().getSQL(USER_QUERY_COUNT);
    }

    @Override
    protected void getPreparedStatementInsert
            (PreparedStatement preparedStatement, User user) throws DAOException {

        try {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUserRole().name());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for inserting: ", e);
            throw new DAOException("Error with PreparedStatementInsert", e);
        }

    }

    @Override
    protected void getPreparedStatementUpdate
            (PreparedStatement preparedStatement, User user) throws DAOException {

        try {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUserRole().name());
            preparedStatement.setInt(3, user.getId());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for updating: ", e);
            throw new DAOException("Error with PreparedStatementUpdate", e);
        }

    }

    @Override
    protected List<User> parseResult(ResultSet resultSet) throws DAOException {
        List<User> users = new ArrayList<>();
        User user;

        try {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(SQLManager.getInstance().getSQL(USER_FIELD_ID)));
                user.setLogin(resultSet.getString(SQLManager.getInstance().getSQL(USER_FIELD_LOGIN)));
                user.setPassword
                        (resultSet.getString(SQLManager.getInstance().getSQL(USER_FIELD_PASSWORD)));
                user.setUserRole
                        (UserRole.valueOf(resultSet.getString(SQLManager.getInstance().getSQL(USER_FIELD_ROLE))));
                UserInfo userInfo = new UserInfo();
                userInfo.setId(user.getId());
                user.setUserInfo(userInfo);
                users.add(user);
            }
        } catch (SQLException | IllegalArgumentException e) {
            LOGGER.error("Exception occurred when parsing the result: ", e);
            throw new DAOException("Error in parsing ResultSet", e);
        }

        return users;
    }

    /**
     * This method is override because there is an one-to-one relationship between
     * entities {@link User} and {@link UserInfo}. Thus, the standard implementation
     * of the method doesn't fit.
     *
     * @param user new user for inserting
     * @throws DAOException exception of database level
     */
    @Override
    public void insert(User user) throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        UserInfoDAOImpl userInfoDAO = new UserInfoDAOImpl();

        try (PreparedStatement preparedStatementUser =
                     connection.prepareStatement(getInsertQuery());
             PreparedStatement preparedStatementUserInfo =
                     connection.prepareStatement(userInfoDAO.getInsertQuery())) {
            connection.setAutoCommit(false);

            getPreparedStatementInsert(preparedStatementUser, user);
            preparedStatementUser.executeUpdate();

            userInfoDAO.getPreparedStatementInsert(preparedStatementUserInfo, user.getUserInfo());
            preparedStatementUserInfo.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            LOGGER.info("Successful insert");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when inserting a record: ", e);

            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error("SQLException occurred when trying to do rollback: ", ex);
                throw new DAOException("Error in inserting a record and rollback failed", ex);
            }

            throw new DAOException("Error in inserting a record", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.warn("SQLException occurred when trying to close connection: ", e);
                }
            }
        }

    }

    /**
     * This method is override because there is an one-to-one relationship between
     * entities {@link User} and {@link UserInfo}. Thus, the standard implementation
     * of the method doesn't fit.
     *
     * @param id user's identifier for deleting
     * @throws DAOException exception of database level
     */
    @Override
    public void deleteById(int id) throws DAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        UserInfoDAOImpl userInfoDAO = new UserInfoDAOImpl();

        try (PreparedStatement preparedStatementUser =
                     connection.prepareStatement(getDeleteByIdQuery());
             PreparedStatement preparedStatementUserInfo =
                     connection.prepareStatement(userInfoDAO.getDeleteByIdQuery())) {
            connection.setAutoCommit(false);

            preparedStatementUser.setInt(1, id);
            preparedStatementUser.executeUpdate();

            preparedStatementUserInfo.setInt(1, id);
            preparedStatementUserInfo.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            LOGGER.info("Successful delete");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when deleting a record by id: ", e);

            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error("SQLException occurred when trying to do rollback: ", ex);
                throw new DAOException("Error in deleting a record and rollback failed", ex);
            }

            throw new DAOException("Error in deleting a record", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.warn("SQLException occurred when trying to close connection: ", e);
                }
            }
        }

    }

    @Override
    public boolean isUserExist(String login) throws DAOException {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     (SQLManager.getInstance().getSQL(USER_QUERY_FIND_BY_LOGIN))) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                users.addAll(parseResult(resultSet));
            }

            LOGGER.info("Successful check the existence of a user");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when checking the existence of a user: ", e);
            throw new DAOException("Error in checking the existence of a user", e);
        }

        return !users.isEmpty();
    }

    @Override
    public User findByLogin(String login) throws DAOException {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     (SQLManager.getInstance().getSQL(USER_QUERY_FIND_BY_LOGIN))) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                users.addAll(parseResult(resultSet));
            }

            if (users.size() != 1) {
                LOGGER.error("Incorrect result returned when searching user by login. " +
                        "Record doesn't exist or exist more than one record");
                throw new DAOException("SQL-query return incorrect result");
            }

            LOGGER.info("Successful search user by login");
        } catch (SQLException e) {
            LOGGER.error("SQLException occurred when searching user by login: ", e);
            throw new DAOException("Error in searching user by login", e);
        }

        return users.get(0);
    }

}