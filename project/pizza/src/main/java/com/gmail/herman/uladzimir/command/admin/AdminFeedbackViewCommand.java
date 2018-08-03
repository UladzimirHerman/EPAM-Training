package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.Feedback;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.FeedbackService;
import com.gmail.herman.uladzimir.service.impl.FeedbackServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static com.gmail.herman.uladzimir.command.AttributeName.FEEDBACK_LIST;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_FEEDBACK_PAGE;

public class AdminFeedbackViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminFeedbackViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        FeedbackService feedbackService = new FeedbackServiceImpl();

        try {
            List<Feedback> feedbackList = feedbackService.findAllInfo();
            requestWrapper.putRequestAttribute(FEEDBACK_LIST, feedbackList);
            route.setResponseType(ResponseType.FORWARD);
            route.setResponsePath(FORWARD_TO_ADMIN_FEEDBACK_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}