package com.epam.mobile.util;

import com.epam.mobile.model.PersonalTariff;
import com.epam.mobile.model.Tariff;

import java.util.ArrayList;

public class TariffSearcher {

    public ArrayList<Tariff> searchPersonalTariffBySubscriptionFeeDiapason
            (ArrayList<Tariff> tariffs, double min, double max) {
        ArrayList<Tariff> listTariff = new ArrayList<>();

        for (Tariff tariff : tariffs) {
            if (tariff instanceof PersonalTariff) {
                if (tariff.getSubscriptionFee() >= min &&
                        tariff.getSubscriptionFee() <= max) {
                    listTariff.add(tariff);
                }
            }
        }

        return listTariff;
    }

    public int countClients(ArrayList<Tariff> tariffs) {
        int total = 0;

        for (Tariff tariff : tariffs) {
            total += tariff.getNumSubscriber();
        }

        return total;
    }

}