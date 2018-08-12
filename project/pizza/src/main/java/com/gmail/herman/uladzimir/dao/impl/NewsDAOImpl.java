package com.gmail.herman.uladzimir.dao.impl;

import com.gmail.herman.uladzimir.dao.NewsDAO;
import com.gmail.herman.uladzimir.dao.SQLManager;
import com.gmail.herman.uladzimir.entity.News;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.herman.uladzimir.dao.SQLElement.*;

/**
 * Class {@link NewsDAOImpl} is used for interacting the entity {@link News}
 * with database. This class implements common and its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractDAO
 * @see NewsDAO
 */
public class NewsDAOImpl extends AbstractDAO<News> implements NewsDAO {

    private static final Logger LOGGER = LogManager.getLogger(NewsDAOImpl.class);

    @Override
    protected String getFindAllQuery() {
        return SQLManager.getInstance().getSQL(NEWS_QUERY_FIND_ALL);
    }

    @Override
    protected String getFindByIdQuery() {
        return SQLManager.getInstance().getSQL(NEWS_QUERY_FIND_BY_ID);
    }

    @Override
    protected String getInsertQuery() {
        return SQLManager.getInstance().getSQL(NEWS_QUERY_INSERT);
    }

    @Override
    protected String getUpdateQuery() {
        return SQLManager.getInstance().getSQL(NEWS_QUERY_UPDATE);
    }

    @Override
    protected String getDeleteByIdQuery() {
        return SQLManager.getInstance().getSQL(NEWS_QUERY_DELETE_BY_ID);
    }

    @Override
    protected String getCountQuery() {
        return SQLManager.getInstance().getSQL(NEWS_QUERY_COUNT);
    }

    @Override
    protected void getPreparedStatementInsert
            (PreparedStatement preparedStatement, News news) throws DAOException {

        try {
            preparedStatement.setInt(1, news.getUser().getId());
            preparedStatement.setString(2, news.getTitle());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setString(4, news.getPhoto());
            preparedStatement.setTimestamp(5, new Timestamp(news.getDate().getTime()));
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for inserting: ", e);
            throw new DAOException("Error with PreparedStatementInsert", e);
        }

    }

    @Override
    protected void getPreparedStatementUpdate
            (PreparedStatement preparedStatement, News news) throws DAOException {

        try {
            preparedStatement.setInt(1, news.getUser().getId());
            preparedStatement.setString(2, news.getTitle());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setString(4, news.getPhoto());
            preparedStatement.setInt(5, news.getId());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for updating: ", e);
            throw new DAOException("Error with PreparedStatementUpdate", e);
        }

    }

    @Override
    protected List<News> parseResult(ResultSet resultSet) throws DAOException {
        List<News> newsList = new ArrayList<>();
        News news;

        try {
            while (resultSet.next()) {
                news = new News();
                news.setId(resultSet.getInt(SQLManager.getInstance().getSQL(NEWS_FIELD_ID)));

                User user = new User();
                user.setId(resultSet.getInt(SQLManager.getInstance().getSQL(NEWS_FIELD_USER_ID)));
                news.setUser(user);

                news.setTitle
                        (resultSet.getString(SQLManager.getInstance().getSQL(NEWS_FIELD_TITLE)));
                news.setContent
                        (resultSet.getString(SQLManager.getInstance().getSQL(NEWS_FIELD_CONTENT)));
                news.setPhoto
                        (resultSet.getString(SQLManager.getInstance().getSQL(NEWS_FIELD_PHOTO)));
                news.setDate(new Date(resultSet.getTimestamp
                        (SQLManager.getInstance().getSQL(NEWS_FIELD_DATE)).getTime()));
                newsList.add(news);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred when parsing the result: ", e);
            throw new DAOException("Error in parsing ResultSet", e);
        }

        return newsList;
    }

}