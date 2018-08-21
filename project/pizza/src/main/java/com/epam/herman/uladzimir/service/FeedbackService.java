package com.epam.herman.uladzimir.service;

import com.epam.herman.uladzimir.entity.Feedback;
import com.epam.herman.uladzimir.exception.ServiceException;

import java.util.List;

/**
 * Interface {@link FeedbackService} contains special methods
 * for entity {@link Feedback}.
 *
 * @author Uladzimir Herman
 * @see GenericService
 */
public interface FeedbackService extends GenericService<Feedback> {

    /**
     * Search full information about the feedback
     *
     * @param offset offset of the sample beginning
     * @param limit  number of requested records
     * @return feedback list
     * @throws ServiceException exception of service level
     */
    List<Feedback> findFullInfo(int offset, int limit) throws ServiceException;

}