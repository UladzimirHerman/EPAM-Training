package com.gmail.herman.uladzimir.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class {@link Product} is used to store information about product.
 *
 * @author Uladzimir Herman
 */
public class Product implements Serializable {

    private static final long serialVersionUID = -103307934290355016L;

    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private String photo;
    private boolean sale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                sale == product.sale &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price) &&
                Objects.equals(photo, product.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, photo, sale);
    }

    @Override
    public String toString() {
        return Product.class.getSimpleName() +
                "{id=" + id +
                ", name='" + name +
                ", description='" + description +
                ", price=" + price +
                ", photo='" + photo +
                ", sale=" + sale +
                '}';
    }

}