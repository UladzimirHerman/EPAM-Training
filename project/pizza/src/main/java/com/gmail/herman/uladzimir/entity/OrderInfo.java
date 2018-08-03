package com.gmail.herman.uladzimir.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class {@link OrderInfo} is used to store information about order elements.
 *
 * @author Uladzimir Herman
 */
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = -6282872318989170000L;

    private int id;
    private Order order;
    private Product product;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInfo orderInfo = (OrderInfo) o;
        return id == orderInfo.id &&
                quantity == orderInfo.quantity &&
                Objects.equals(order, orderInfo.order) &&
                Objects.equals(product, orderInfo.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, quantity);
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }

}