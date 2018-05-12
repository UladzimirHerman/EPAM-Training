package com.epam.mobile.util;

import com.epam.mobile.model.PersonalTariff;
import com.epam.mobile.model.Tariff;

import java.util.ArrayList;

/**
 * Functional class for information search
 */
public class TariffSearcher {

    /**
     * @param tariffs is all company's tariffs
     * @param min     is a minimum value of subscription fee
     * @param max     is a maximum value of subscription fee
     * @return all found personal tariffs
     * @see PersonalTariff
     */
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

    /**
     * @param tariffs is all company's tariffs
     * @return total number of clients
     */
    public int countClients(ArrayList<Tariff> tariffs) {
        int total = 0;

        for (Tariff tariff : tariffs) {
            total += tariff.getNumSubscriber();
        }

        return total;
    }

}