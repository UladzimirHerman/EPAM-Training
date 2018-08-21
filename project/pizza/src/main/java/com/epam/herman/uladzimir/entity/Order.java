package com.epam.herman.uladzimir.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class {@link Order} is used to store information about orders.
 *
 * @author Uladzimir Herman
 */
public class Order implements Serializable {

    private static final long serialVersionUID = -5431682380027135516L;

    private int id;
    private Date date;
    private User user;
    private OrderStatus orderStatus;
    private List<OrderInfo> orderInfoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(date, order.date) &&
                Objects.equals(user, order.user) &&
                orderStatus == order.orderStatus &&
                Objects.equals(orderInfoList, order.orderInfoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, user, orderStatus, orderInfoList);
    }

    @Override
    public String toString() {
        return Order.class.getSimpleName() +
                "{id=" + id +
                ", date=" + date +
                ", userId=" + user.getId() +
                ", orderStatus=" + orderStatus +
                ", orderInfoList=" + orderInfoList +
                '}';
    }

}