package com.epam.mobile.model;

import java.util.Objects;

/**
 * Entity class, has two heirs
 *
 * @see CorporateTariff
 * @see PersonalTariff
 */
public class Tariff implements Comparable<Tariff> {

    private String name;
    private String description;
    private boolean inArchive;
    private double subscriptionFee;
    private int numSubscriber;
    private int freeMinute;
    private double costMinute;
    private int freeSms;
    private double costSms;
    private int freeInternet;
    private double costInternet;

    public Tariff() {
    }

    public Tariff(String name, String description, boolean inArchive, double subscriptionFee,
                  int numSubscriber, int freeMinute, double costMinute, int freeSms,
                  double costSms, int freeInternet, double costInternet) {
        this.name = name;
        this.description = description;
        this.inArchive = inArchive;
        this.subscriptionFee = subscriptionFee;
        this.numSubscriber = numSubscriber;
        this.freeMinute = freeMinute;
        this.costMinute = costMinute;
        this.freeSms = freeSms;
        this.costSms = costSms;
        this.freeInternet = freeInternet;
        this.costInternet = costInternet;
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

    public boolean isInArchive() {
        return inArchive;
    }

    public void setInArchive(boolean inArchive) {
        this.inArchive = inArchive;
    }

    public double getSubscriptionFee() {
        return subscriptionFee;
    }

    public void setSubscriptionFee(double subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    public int getNumSubscriber() {
        return numSubscriber;
    }

    public void setNumSubscriber(int numSubscriber) {
        this.numSubscriber = numSubscriber;
    }

    public int getFreeMinute() {
        return freeMinute;
    }

    public void setFreeMinute(int freeMinute) {
        this.freeMinute = freeMinute;
    }

    public double getCostMinute() {
        return costMinute;
    }

    public void setCostMinute(double costMinute) {
        this.costMinute = costMinute;
    }

    public int getFreeSms() {
        return freeSms;
    }

    public void setFreeSms(int freeSms) {
        this.freeSms = freeSms;
    }

    public double getCostSms() {
        return costSms;
    }

    public void setCostSms(double costSms) {
        this.costSms = costSms;
    }

    public int getFreeInternet() {
        return freeInternet;
    }

    public void setFreeInternet(int freeInternet) {
        this.freeInternet = freeInternet;
    }

    public double getCostInternet() {
        return costInternet;
    }

    public void setCostInternet(double costInternet) {
        this.costInternet = costInternet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Double.compare(tariff.subscriptionFee, subscriptionFee) == 0 &&
                numSubscriber == tariff.numSubscriber &&
                Objects.equals(name, tariff.name) &&
                Objects.equals(description, tariff.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, subscriptionFee, numSubscriber);
    }

    /**
     * Sorting by increasing the cost of a subscription fee
     */
    @Override
    public int compareTo(Tariff o) {
        return Double.compare(subscriptionFee, o.subscriptionFee);
    }

}