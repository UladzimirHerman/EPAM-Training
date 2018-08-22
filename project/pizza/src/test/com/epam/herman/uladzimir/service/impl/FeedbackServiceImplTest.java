package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.entity.Feedback;
import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.exception.ServiceException;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class FeedbackServiceImplTest {

    private FeedbackServiceImpl feedbackService;

    public FeedbackServiceImplTest() {
        feedbackService = new FeedbackServiceImpl();
    }

    @Test
    public void findAllTest() throws ServiceException {
        assertEquals(10, feedbackService.findAll(0, 10).size());
    }

    @Test
    public void findByIdTest() throws ServiceException {
        assertNotNull(feedbackService.findById(1));
    }

    @Test
    public void insertTest() throws ServiceException {
        Feedback feedback = new Feedback();
        feedback.setId(12);
        User user = new User();
        user.setId(1);
        feedback.setUser(user);
        feedback.setComment("Correct comment");
        feedback.setRating(4);
        feedback.setDate(new Date());
        feedbackService.insert(feedback);
        Feedback feedbackNew = feedbackService.findById(12);
        feedback.setDate(feedbackNew.getDate());
        assertEquals(feedback, feedbackNew);
    }

    @Test
    public void updateTest() throws ServiceException {
        Feedback feedback = feedbackService.findById(12);
        feedback.setComment("Updated comment");
        feedback.setRating(5);
        feedbackService.update(feedback);
        assertEquals(feedback, feedbackService.findById(12));
    }

    @Test(expected = ServiceException.class)
    public void deleteByIdTest() throws ServiceException {
        feedbackService.deleteById(12);
        feedbackService.findById(12);
    }

    @Test
    public void countTest() throws ServiceException {
        assertEquals(11, feedbackService.count());
    }

    @Test
    public void findFullInfoTest() throws ServiceException {
        assertEquals(4, feedbackService.findFullInfo(0, 4).size());
    }

}