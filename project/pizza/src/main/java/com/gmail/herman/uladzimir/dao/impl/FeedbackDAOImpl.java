package com.gmail.herman.uladzimir.dao.impl;

import com.gmail.herman.uladzimir.dao.FeedbackDAO;
import com.gmail.herman.uladzimir.dao.SQLManager;
import com.gmail.herman.uladzimir.entity.Feedback;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.herman.uladzimir.dao.SQLElement.*;

public class FeedbackDAOImpl extends AbstractDAO<Feedback> implements FeedbackDAO {

    private static final Logger LOGGER = LogManager.getLogger(FeedbackDAOImpl.class);

    @Override
    public String getFindAllQuery() {
        return SQLManager.getInstance().getSQL(FEEDBACK_QUERY_FIND_ALL);
    }

    @Override
    public String getFindByIdQuery() {
        return SQLManager.getInstance().getSQL(FEEDBACK_QUERY_FIND_BY_ID);
    }

    @Override
    public String getInsertQuery() {
        return SQLManager.getInstance().getSQL(FEEDBACK_QUERY_INSERT);
    }

    @Override
    public String getUpdateQuery() {
        return SQLManager.getInstance().getSQL(FEEDBACK_QUERY_UPDATE);
    }

    @Override
    public String getDeleteByIdQuery() {
        return SQLManager.getInstance().getSQL(FEEDBACK_QUERY_DELETE_BY_ID);
    }

    @Override
    public void getPreparedStatementInsert
            (PreparedStatement preparedStatement, Feedback feedback) throws DAOException {

        try {
            preparedStatement.setInt(1, feedback.getUser().getId());
            preparedStatement.setString(2, feedback.getComment());
            preparedStatement.setInt(3, feedback.getRating());
            preparedStatement.setTimestamp(4, new Timestamp(feedback.getDate().getTime()));
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for inserting: ", e);
            throw new DAOException("Error with PreparedStatementInsert", e);
        }

    }

    @Override
    public void getPreparedStatementUpdate
            (PreparedStatement preparedStatement, Feedback feedback) throws DAOException {

        try {
            preparedStatement.setString(1, feedback.getComment());
            preparedStatement.setInt(2, feedback.getRating());
            preparedStatement.setInt(3, feedback.getId());
        } catch (SQLException e) {
            LOGGER.error
                    ("SQLException occurred when working with prepared statement for updating: ", e);
            throw new DAOException("Error with PreparedStatementUpdate", e);
        }

    }

    @Override
    public List<Feedback> parseResult(ResultSet resultSet) throws DAOException {
        List<Feedback> feedbackList = new ArrayList<>();
        Feedback feedback;

        try {
            while (resultSet.next()) {
                feedback = new Feedback();
                feedback.setId
                        (resultSet.getInt(SQLManager.getInstance().getSQL(FEEDBACK_FIELD_ID)));

                User user = new User();
                user.setId
                        (resultSet.getInt(SQLManager.getInstance().getSQL(FEEDBACK_FIELD_USER_ID)));
                feedback.setUser(user);

                feedback.setComment
                        (resultSet.getString(SQLManager.getInstance().getSQL(FEEDBACK_FIELD_COMMENT)));
                feedback.setRating
                        (resultSet.getInt(SQLManager.getInstance().getSQL(FEEDBACK_FIELD_RATING)));
                feedback.setDate(new Date(resultSet.getTimestamp
                        (SQLManager.getInstance().getSQL(FEEDBACK_FIELD_DATE)).getTime()));
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred when parsing the result: ", e);
            throw new DAOException("Error in parsing ResultSet", e);
        }

        return feedbackList;
    }

}