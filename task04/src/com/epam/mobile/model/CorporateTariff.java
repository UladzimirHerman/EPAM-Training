package com.epam.mobile.model;

import java.util.Objects;

/**
 * Entity class describes the corporate tariff
 */
public class CorporateTariff extends Tariff {

    private int freeColleagueMinute;
    private double costColleagueMinute;

    public CorporateTariff() {
    }

    public CorporateTariff(String name, String description, boolean inArchive,
                           double subscriptionFee, int numSubscriber, int freeMinute,
                           double costMinute, int freeSms, double costSms, int freeInternet,
                           double costInternet, int freeColleagueMinute, double costColleagueMinute) {
        super(name, description, inArchive, subscriptionFee, numSubscriber, freeMinute,
                costMinute, freeSms, costSms, freeInternet, costInternet);
        this.freeColleagueMinute = freeColleagueMinute;
        this.costColleagueMinute = costColleagueMinute;
    }

    public int getFreeColleagueMinute() {
        return freeColleagueMinute;
    }

    public void setFreeColleagueMinute(int freeColleagueMinute) {
        this.freeColleagueMinute = freeColleagueMinute;
    }

    public double getCostColleagueMinute() {
        return costColleagueMinute;
    }

    public void setCostColleagueMinute(double costColleagueMinute) {
        this.costColleagueMinute = costColleagueMinute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CorporateTariff tariff = (CorporateTariff) o;
        return freeColleagueMinute == tariff.freeColleagueMinute &&
                Double.compare(tariff.costColleagueMinute, costColleagueMinute) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), freeColleagueMinute, costColleagueMinute);
    }

    @Override
    public String toString() {
        StringBuilder corporate = new StringBuilder("TARIFF: ");
        corporate.append(super.getName());
        corporate.append(", TYPE: Corporate, IN ARCHIVE: ");
        corporate.append(super.isInArchive());
        corporate.append(", PRICE: $");
        corporate.append(super.getSubscriptionFee());
        corporate.append(", SUBSCRIBERS: ");
        corporate.append(super.getNumSubscriber());
        corporate.append("\nDETAILS: $");
        corporate.append(super.getCostMinute());
        corporate.append(" per min + ");
        corporate.append(super.getFreeMinute());
        corporate.append(" min free, $");
        corporate.append(costColleagueMinute);
        corporate.append(" per min with colleagues + ");
        corporate.append(freeColleagueMinute);
        corporate.append(" min free, $");
        corporate.append(super.getCostSms());
        corporate.append(" per sms + ");
        corporate.append(super.getFreeSms());
        corporate.append(" sms free, $");
        corporate.append(super.getCostInternet());
        corporate.append(" for 1MB + ");
        corporate.append(super.getFreeInternet());
        corporate.append(" MB free!");
        return corporate.toString();
    }

}