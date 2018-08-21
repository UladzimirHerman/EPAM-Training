package com.epam.herman.uladzimir.filter;

import com.epam.herman.uladzimir.entity.UserRole;
import com.epam.herman.uladzimir.route.ResponsePath;

import java.util.HashSet;

import static com.epam.herman.uladzimir.route.ResponsePath.*;

/**
 * Class {@link AccessURL} is used for controlling access to the URL addresses
 * depending on the role of the user.
 *
 * @author Uladzimir Herman
 * @see ResponsePath
 * @see UserRole
 */
public class AccessURL {

    private static final AccessURL accessURL = new AccessURL();
    private HashSet<String> adminURL;
    private HashSet<String> userURL;

    /**
     * Default constructor, which defines accessible URL addresses
     * for particular role
     */
    private AccessURL() {
        adminURL = new HashSet<>();
        adminURL.add(REDIRECT_TO_ADMIN_FEEDBACK_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_NEWS_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_NEWS_CREATE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_NEWS_DELETE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_NEWS_EDIT_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_ORDERS_ARCHIVE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_ORDERS_OPEN_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_PRODUCT_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_PRODUCT_CREATE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_PRODUCT_EDIT_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_PROFILE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_UPLOAD_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_USERS_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_USERS_DELETE_PAGE);
        adminURL.add(REDIRECT_TO_ADMIN_USERS_EDIT_PAGE);

        userURL = new HashSet<>();
        userURL.add(REDIRECT_TO_USER_BASKET_PAGE);
        userURL.add(REDIRECT_TO_USER_FEEDBACK_PAGE);
        userURL.add(REDIRECT_TO_USER_FEEDBACK_CREATE_PAGE);
        userURL.add(REDIRECT_TO_USER_FEEDBACK_EDIT_PAGE);
        userURL.add(REDIRECT_TO_USER_NEWS_PAGE);
        userURL.add(REDIRECT_TO_USER_ORDERS_ARCHIVE_PAGE);
        userURL.add(REDIRECT_TO_USER_ORDERS_OPEN_PAGE);
        userURL.add(REDIRECT_TO_USER_PRODUCT_PAGE);
        userURL.add(REDIRECT_TO_USER_PROFILE_PAGE);
    }

    public static AccessURL getInstance() {
        return accessURL;
    }

    /**
     * Checking access permissions
     *
     * @param userRole role of the user
     * @param url      URL address
     * @return true, if access is allowed or false, if access isn't allowed
     */
    public boolean isCorrectAccess(UserRole userRole, String url) {
        return (userRole == UserRole.ADMIN && adminURL.contains(url)) ||
                (userRole == UserRole.USER && userURL.contains(url));
    }

}