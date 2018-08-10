package com.gmail.herman.uladzimir.filter;

import com.gmail.herman.uladzimir.entity.UserRole;

import java.util.HashSet;

import static com.gmail.herman.uladzimir.command.ResponsePath.*;

public class AccessURL {

    private static final AccessURL accessURL = new AccessURL();
    private HashSet<String> adminURL;
    private HashSet<String> userURL;

    private AccessURL() {
        adminURL = new HashSet<>();
        adminURL.add(REDIRECT_TO_ADMIN_FEEDBACK_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_NEWS_CREATE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_NEWS_DELETE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_NEWS_EDIT_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_NEWS_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_ORDERS_ARCHIVE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_ORDERS_OPEN_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_PRODUCT_CREATE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_PRODUCT_EDIT_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_PRODUCT_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_PROFILE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_UPLOAD_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_USERS_DELETE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_USERS_EDIT_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_USERS_PAGE);

        userURL = new HashSet<>();
        userURL.add(REDIRECT_TO_USER_BASKET_PAGE);
        userURL.add(REDIRECT_TO_USER_FEEDBACK_CREATE_PAGE);
        userURL.add(REDIRECT_TO_USER_FEEDBACK_EDIT_PAGE);
        userURL.add(REDIRECT_TO_USER_FEEDBACK_PAGE);
        userURL.add(REDIRECT_TO_USER_NEWS_PAGE);
        userURL.add(REDIRECT_TO_USER_ORDERS_ARCHIVE_PAGE);
        userURL.add(REDIRECT_TO_USER_ORDERS_OPEN_PAGE);
        userURL.add(REDIRECT_TO_USER_PRODUCT_PAGE);
        userURL.add(REDIRECT_TO_USER_PROFILE_PAGE);
    }

    public static AccessURL getInstance() {
        return accessURL;
    }

    public boolean isCorrectAccess(UserRole userRole, String url) {
        return (userRole == UserRole.ADMIN && adminURL.contains(url)) ||
                (userRole == UserRole.USER && userURL.contains(url));
    }

}