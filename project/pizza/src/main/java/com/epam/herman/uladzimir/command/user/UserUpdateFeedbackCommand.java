package com.epam.herman.uladzimir.command.user;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;
import com.epam.herman.uladzimir.entity.Feedback;
import com.epam.herman.uladzimir.exception.ServiceException;
import com.epam.herman.uladzimir.service.FeedbackService;
import com.epam.herman.uladzimir.service.impl.FeedbackServiceImpl;
import com.epam.herman.uladzimir.validator.FeedbackValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.epam.herman.uladzimir.command.AttributeName.*;
import static com.epam.herman.uladzimir.command.AttributeName.FEEDBACK;
import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_USER_FEEDBACK_EDIT_PAGE;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_USER_FEEDBACK_FIRST_PAGE;

/**
 * This class is used to update the feedback.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class UserUpdateFeedbackCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(UserUpdateFeedbackCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        Feedback feedback = new Feedback();
        feedback.setId(Integer.parseInt(requestWrapper.getRequestParameter(FEEDBACK_ID)));
        feedback.setComment(requestWrapper.getRequestParameter(COMMENT));
        feedback.setRating(Integer.parseInt(requestWrapper.getRequestParameter(RATING)));

        FeedbackValidator feedbackValidator = new FeedbackValidator();

        try {

            if (feedbackValidator.isFeedbackCorrect(feedback)) {
                FeedbackService feedbackService = new FeedbackServiceImpl();
                feedbackService.update(feedback);

                route.setResponseType(ResponseType.REDIRECT);
                route.setResponsePath(REDIRECT_TO_USER_FEEDBACK_FIRST_PAGE);
            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);
                requestWrapper.putRequestAttribute(FEEDBACK, feedback);

                route.setResponsePath(FORWARD_TO_USER_FEEDBACK_EDIT_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}