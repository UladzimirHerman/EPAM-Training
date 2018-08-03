package com.gmail.herman.uladzimir.service;

import com.gmail.herman.uladzimir.entity.Feedback;
import com.gmail.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link FeedbackService} contains specific methods
 * for entity {@link Feedback}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface FeedbackService extends GenericService<Feedback> {

    List<Feedback> findAllInfo() throws ServiceException;

    List<Feedback> fillFeedbackWithInfo(List<Feedback> feedbackList)
            throws ServiceException;

}