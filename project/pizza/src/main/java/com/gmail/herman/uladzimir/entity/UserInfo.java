package com.gmail.herman.uladzimir.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class {@link UserInfo} is used to store additional information about users.
 *
 * @author Uladzimir Herman
 */
public class UserInfo implements Serializable{

    private static final long serialVersionUID = -118611134901120911L;

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String city;
    private String street;
    private String building;
    private String housing;
    private String apartment;
    private String note;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.id &&
                Objects.equals(name, userInfo.name) &&
                Objects.equals(surname, userInfo.surname) &&
                Objects.equals(phone, userInfo.phone) &&
                Objects.equals(city, userInfo.city) &&
                Objects.equals(street, userInfo.street) &&
                Objects.equals(building, userInfo.building) &&
                Objects.equals(housing, userInfo.housing) &&
                Objects.equals(apartment, userInfo.apartment) &&
                Objects.equals(note, userInfo.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phone, city, street, building, housing, apartment, note);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", housing='" + housing + '\'' +
                ", apartment='" + apartment + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

}