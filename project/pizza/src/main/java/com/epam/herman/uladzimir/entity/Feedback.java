package com.epam.herman.uladzimir.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Class {@link Feedback} is used to store information about users feedback.
 *
 * @author Uladzimir Herman
 */
public class Feedback implements Serializable {

    private static final long serialVersionUID = -5567887442381837815L;

    private int id;
    private User user;
    private String comment;
    private int rating;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
        Feedback feedback = (Feedback) o;
        return id == feedback.id &&
                rating == feedback.rating &&
                Objects.equals(user, feedback.user) &&
                Objects.equals(comment, feedback.comment) &&
                Objects.equals(date, feedback.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, comment, rating, date);
    }

    @Override
    public String toString() {
        return Feedback.class.getSimpleName() +
                "{id=" + id +
                ", userId=" + user.getId() +
                ", comment='" + comment +
                ", rating=" + rating +
                ", date=" + date +
                '}';
    }

}