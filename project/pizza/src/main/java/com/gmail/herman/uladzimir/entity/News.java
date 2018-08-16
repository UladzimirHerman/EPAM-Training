package com.gmail.herman.uladzimir.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Class {@link News} is used to store information about news.
 *
 * @author Uladzimir Herman
 */
public class News implements Serializable{

    private static final long serialVersionUID = 8152858434406291194L;

    private int id;
    private User user;
    private String title;
    private String content;
    private String photo;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(user, news.user) &&
                Objects.equals(title, news.title) &&
                Objects.equals(content, news.content) &&
                Objects.equals(photo, news.photo) &&
                Objects.equals(date, news.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, content, photo, date);
    }

    @Override
    public String toString() {
        return News.class.getSimpleName() +
                "{id=" + id +
                ", userId=" + user.getId() +
                ", title='" + title +
                ", content='" + content +
                ", photo='" + photo +
                ", date=" + date +
                '}';
    }

}