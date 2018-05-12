package com.epam.mobile.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Functional class for working with different tariffs
 */
public class Company {

    public ArrayList<Tariff> tariffs = new ArrayList<>();

    public Company() {
    }

    public ArrayList<Tariff> getAllTariff() {
        return tariffs;
    }

    public String showAllTariff() {
        StringBuilder result = new StringBuilder();

        for (Tariff tariff : tariffs) {
            result.append(tariff.toString());
            result.append("\n");
        }

        return result.toString();
    }

    public int countTariff() {
        return tariffs.size();
    }

    public boolean isEmpty() {
        return tariffs.isEmpty();
    }

    public Tariff getByIndex(int index) {
        return tariffs.get(index);
    }

    public void addTariff(Tariff tariff) {
        tariffs.add(tariff);
    }

    public void addTariff(Tariff... tariff) {
        for (int i = 0; i < tariff.length; i++) {
            tariffs.add(tariff[i]);
        }
    }

    public void removeByIndex(int index) {
        tariffs.remove(index);
    }

    public void clear() {
        tariffs.clear();
    }

    public void sortTariff() {
        Collections.sort(tariffs);
    }

}