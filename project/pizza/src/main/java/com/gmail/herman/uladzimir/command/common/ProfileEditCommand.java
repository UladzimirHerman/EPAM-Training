package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserInfo;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.UserInfoService;
import com.gmail.herman.uladzimir.service.impl.UserInfoServiceImpl;
import com.gmail.herman.uladzimir.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.*;
import static com.gmail.herman.uladzimir.command.AttributeName.NOTE;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_PROFILE_PAGE;

public class ProfileEditCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ProfileEditCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        UserInfo userInfo = new UserInfo();
        userInfo.setName(requestWrapper.getRequestParameter(NAME));
        userInfo.setSurname(requestWrapper.getRequestParameter(SURNAME));
        userInfo.setPhone(requestWrapper.getRequestParameter(PHONE));
        userInfo.setCity(requestWrapper.getRequestParameter(CITY));
        userInfo.setStreet(requestWrapper.getRequestParameter(STREET));
        userInfo.setBuilding(requestWrapper.getRequestParameter(BUILDING));
        userInfo.setHousing(requestWrapper.getRequestParameter(HOUSING));
        userInfo.setApartment(requestWrapper.getRequestParameter(APARTMENT));
        userInfo.setNote(requestWrapper.getRequestParameter(NOTE));

        UserValidator userValidator = new UserValidator();
        UserInfoService userInfoService = new UserInfoServiceImpl();

        try {

            if (userValidator.isUserInfoCorrect(userInfo)) {
                User user = (User) requestWrapper.getSessionAttribute(USER);
                userInfo.setId(user.getId());
                userInfoService.update(userInfo);
                route.setResponsePath(requestWrapper.getReferer());
            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);
                requestWrapper.putRequestAttribute(USER_INFO, userInfo);
                route.setResponseType(ResponseType.FORWARD);
                route.setResponsePath(FORWARD_TO_PROFILE_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}