package com.epam.herman.uladzimir.service.impl;

import com.epam.herman.uladzimir.dao.impl.FeedbackDAOImpl;
import com.epam.herman.uladzimir.entity.Feedback;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.FeedbackService;
import com.epam.herman.uladzimir.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class {@link FeedbackServiceImpl} is used for interacting the entity
 * {@link Feedback} with DAO level. This class implements common and
 * its special methods.
 *
 * @author Uladzimir Herman
 * @see AbstractService
 * @see FeedbackDAOImpl
 * @see FeedbackService
 */
public class FeedbackServiceImpl extends AbstractService<Feedback, FeedbackDAOImpl>
        implements FeedbackService {

    private static final Logger LOGGER = LogManager.getLogger(FeedbackServiceImpl.class);

    /**
     * Filling a feedback list with information
     *
     * @param feedbackList feedback list
     * @throws ServiceException exception of service level
     */
    private void fillWithInfo(List<Feedback> feedbackList) throws ServiceException {
        UserService userService = new UserServiceImpl();

        for (Feedback feedback : feedbackList) {
            feedback.setUser(userService.findFullInfoById(feedback.getUser().getId()));
        }

        LOGGER.info("Successful fill feedback list with info");
    }

    @Override
    protected FeedbackDAOImpl getObjectDAOImpl() {
        return new FeedbackDAOImpl();
    }

    @Override
    public List<Feedback> findFullInfo(int offset, int limit) throws ServiceException {
        List<Feedback> feedbackList = findAll(offset, limit);

        if (!feedbackList.isEmpty()) {
            fillWithInfo(feedbackList);
        }

        LOGGER.info("Successful search feedback list with full info");
        return feedbackList;
    }

}