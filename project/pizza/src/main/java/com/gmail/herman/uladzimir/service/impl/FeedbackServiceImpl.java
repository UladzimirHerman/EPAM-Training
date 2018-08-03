package com.gmail.herman.uladzimir.service.impl;

import com.gmail.herman.uladzimir.dao.impl.FeedbackDAOImpl;
import com.gmail.herman.uladzimir.entity.Feedback;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.FeedbackService;
import com.gmail.herman.uladzimir.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class FeedbackServiceImpl extends AbstractService<Feedback, FeedbackDAOImpl>
        implements FeedbackService {

    private static final Logger LOGGER = LogManager.getLogger(FeedbackServiceImpl.class);

    @Override
    public FeedbackDAOImpl getObjectDAOImpl() {
        return new FeedbackDAOImpl();
    }


    @Override
    public List<Feedback> findAllInfo() throws ServiceException {
        List<Feedback> feedbackList = findAll();
        feedbackList = fillFeedbackWithInfo(feedbackList);
        return feedbackList;
    }

    @Override
    public List<Feedback> fillFeedbackWithInfo(List<Feedback> feedbackList)
            throws ServiceException {
        UserService userService = new UserServiceImpl();

        for (Feedback feedback : feedbackList) {
            feedback.setUser(userService.findUserAndUserInfoById(feedback.getUser().getId()));
        }

        return feedbackList;
    }

}