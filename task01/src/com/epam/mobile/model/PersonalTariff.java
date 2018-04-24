package com.epam.mobile.model;

import java.util.Objects;

public class PersonalTariff extends Tariff {

    private int freeInternationalMinute;
    private double costInternationalMinute;

    public PersonalTariff() {
    }

    public PersonalTariff(String name, String description, boolean inArchive,
                          double subscriptionFee, int numSubscriber, int freeMinute,
                          double costMinute, int freeSms, double costSms, int freeInternet,
                          double costInternet, int freeColleagueMinute, double costColleagueMinute) {
        super(name, description, inArchive, subscriptionFee, numSubscriber, freeMinute,
                costMinute, freeSms, costSms, freeInternet, costInternet);
        this.freeInternationalMinute = freeInternationalMinute;
        this.costInternationalMinute = costInternationalMinute;
    }

    public int getFreeInternationalMinute() {
        return freeInternationalMinute;
    }

    public void setFreeInternationalMinute(int freeInternationalMinute) {
        this.freeInternationalMinute = freeInternationalMinute;
    }

    public double getCostInternationalMinute() {
        return costInternationalMinute;
    }

    public void setCostInternationalMinute(double costInternationalMinute) {
        this.costInternationalMinute = costInternationalMinute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonalTariff that = (PersonalTariff) o;
        return freeInternationalMinute == that.freeInternationalMinute &&
                Double.compare(that.costInternationalMinute, costInternationalMinute) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), freeInternationalMinute, costInternationalMinute);
    }

    @Override
    public String toString() {
        StringBuilder personal = new StringBuilder("TARIFF: ");
        personal.append(super.getName());
        personal.append(", TYPE: Personal, IN ARCHIVE: ");
        personal.append(super.isInArchive());
        personal.append(", PRICE: $");
        personal.append(super.getSubscriptionFee());
        personal.append(", SUBSCRIBERS: ");
        personal.append(super.getNumSubscriber());
        personal.append("\nDETAILS: $");
        personal.append(super.getCostMinute());
        personal.append(" per min + ");
        personal.append(super.getFreeMinute());
        personal.append(" min free, $");
        personal.append(costInternationalMinute);
        personal.append(" per international min + ");
        personal.append(freeInternationalMinute);
        personal.append(" international min free, $");
        personal.append(super.getCostSms());
        personal.append(" per sms + ");
        personal.append(super.getFreeSms());
        personal.append(" sms free, $");
        personal.append(super.getCostInternet());
        personal.append(" for 1MB + ");
        personal.append(super.getFreeInternet());
        personal.append(" MB free!");
        return personal.toString();
    }

}