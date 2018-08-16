package com.gmail.herman.uladzimir.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class {@link User} is used to store information about user.
 *
 * @author Uladzimir Herman
 */
public class User implements Serializable {

    private static final long serialVersionUID = -566903061613011776L;

    private int id;
    private String login;
    private String password;
    private UserRole userRole;
    private UserInfo userInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                userRole == user.userRole &&
                Objects.equals(userInfo, user.userInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, userRole, userInfo);
    }

    @Override
    public String toString() {
        return User.class.getSimpleName() +
                "{id=" + id +
                ", login='" + login +
                ", password='" + password +
                ", userRole=" + userRole +
                ", userInfoId=" + userInfo.getId() +
                '}';
    }

}