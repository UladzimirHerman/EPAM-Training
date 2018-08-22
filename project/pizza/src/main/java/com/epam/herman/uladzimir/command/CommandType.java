package com.epam.herman.uladzimir.command;

import com.epam.herman.uladzimir.command.admin.*;
import com.epam.herman.uladzimir.command.common.*;
import com.epam.herman.uladzimir.command.user.*;

/**
 * Enum {@link CommandType} contains the application commands
 * available for execution.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public enum CommandType {

    ADMIN_ADD_NEWS(new AdminAddNewsCommand()),
    ADMIN_ADD_PRODUCT(new AdminAddProductCommand()),
    ADMIN_DELETE_NEWS(new AdminDeleteNewsCommand()),
    ADMIN_DELETE_USER(new AdminDeleteUserCommand()),
    ADMIN_FEEDBACK(new FeedbackViewCommand()),
    ADMIN_NEWS(new NewsViewCommand()),
    ADMIN_NEWS_CREATE(new AdminNewsCreateViewCommand()),
    ADMIN_NEWS_DELETE(new AdminNewsDeleteViewCommand()),
    ADMIN_NEWS_EDIT(new AdminNewsEditViewCommand()),
    ADMIN_ORDERS_ARCHIVE(new AdminOrdersArchiveViewCommand()),
    ADMIN_ORDERS_OPEN(new AdminOrdersOpenViewCommand()),
    ADMIN_PRODUCT(new AdminProductViewCommand()),
    ADMIN_PRODUCT_CREATE(new AdminProductCreateViewCommand()),
    ADMIN_PRODUCT_EDIT(new AdminProductEditViewCommand()),
    ADMIN_PROFILE(new ProfileViewCommand()),
    ADMIN_UPDATE_NEWS(new AdminUpdateNewsCommand()),
    ADMIN_UPDATE_ORDER_STATUS(new AdminUpdateOrderStatusCommand()),
    ADMIN_UPDATE_PRODUCT(new AdminUpdateProductCommand()),
    ADMIN_UPDATE_ROLE(new AdminUpdateRoleCommand()),
    ADMIN_UPLOAD(new AdminUploadViewCommand()),
    ADMIN_UPLOAD_PHOTO(new AdminUploadPhotoCommand()),
    ADMIN_USERS(new AdminUsersViewCommand()),
    ADMIN_USERS_DELETE(new AdminUsersDeleteViewCommand()),
    ADMIN_USERS_EDIT(new AdminUsersEditViewCommand()),
    AUTHENTICATION(new AuthenticationCommand()),
    CREATE_ACCOUNT(new CreateAccountCommand()),
    EMPTY(new EmptyCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    PASSWORD_EDIT(new PasswordEditCommand()),
    PROFILE_EDIT(new ProfileEditCommand()),
    REGISTRATION(new RegistrationCommand()),
    SET_LOCALE(new SetLocaleCommand()),
    USER_ADD_FEEDBACK(new UserAddFeedbackCommand()),
    USER_ADD_TO_BASKET(new UserAddToBasketCommand()),
    USER_BASKET(new UserBasketViewCommand()),
    USER_CREATE_ORDER(new UserCreateOrderCommand()),
    USER_DELETE_ORDER_INFO(new UserDeleteOrderInfoCommand()),
    USER_FEEDBACK(new FeedbackViewCommand()),
    USER_FEEDBACK_CREATE(new UserFeedbackCreateViewCommand()),
    USER_FEEDBACK_EDIT(new UserFeedbackEditViewCommand()),
    USER_NEWS(new NewsViewCommand()),
    USER_ORDERS_ARCHIVE(new UserOrdersArchiveViewCommand()),
    USER_ORDERS_OPEN(new UserOrdersOpenViewCommand()),
    USER_PRODUCT(new UserProductViewCommand()),
    USER_PROFILE(new ProfileViewCommand()),
    USER_UPDATE_FEEDBACK(new UserUpdateFeedbackCommand()),
    USER_UPDATE_ORDER_INFO(new UserUpdateOrderInfoCommand());

    private Command command;

    /**
     * Constructor, which initializes the command with particular value
     *
     * @param command particular command
     */
    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}