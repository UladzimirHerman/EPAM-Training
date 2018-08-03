package com.gmail.herman.uladzimir.command;

import com.gmail.herman.uladzimir.command.admin.*;
import com.gmail.herman.uladzimir.command.common.*;
import com.gmail.herman.uladzimir.command.user.*;

public enum CommandType {

    ADMIN_ADD_NEWS(new AdminAddNewsCommand()),
    ADMIN_ADD_PRODUCT(new AdminAddProductCommand()),
    ADMIN_DELETE_NEWS(new AdminDeleteNewsCommand()),
    ADMIN_DELETE_USER(new AdminDeleteUserCommand()),
    ADMIN_FEEDBACK(new AdminFeedbackViewCommand()),
    ADMIN_NEWS(new AdminNewsViewCommand()),
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
    USER_NEWS(new UserNewsViewCommand()),
    USER_PRODUCT(new UserProductViewCommand()),
    USER_PROFILE(new ProfileViewCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}