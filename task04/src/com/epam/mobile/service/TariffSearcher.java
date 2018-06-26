package com.epam.mobile.service;

import com.epam.mobile.model.PersonalTariff;
import com.epam.mobile.model.Tariff;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Functional class for information search
 */
public class TariffSearcher {

    private static final Logger LOG = Logger.getLogger(TariffSearcher.class);

    /**
     * @param tariffs is all company's tariffs
     * @param min     is a minimum value of subscription fee
     * @param max     is a maximum value of subscription fee
     * @return all found personal tariffs
     * @see PersonalTariff
     */
    public List<Tariff> searchPersonalTariffBySubscriptionFeeDiapason
    (List<Tariff> tariffs, double min, double max) {
        ArrayList<Tariff> listTariff = new ArrayList<>();

        for (Tariff tariff : tariffs) {
            if (tariff instanceof PersonalTariff) {
                if (tariff.getSubscriptionFee() >= min &&
                        tariff.getSubscriptionFee() <= max) {
                    listTariff.add(tariff);
                }
            }
        }

        LOG.info("Successful completion of searchPersonalTariffBySubscriptionFeeDiapason");
        return listTariff;
    }

    /**
     * @param tariffs is all company's tariffs
     * @return total number of clients
     */
    public int countClients(List<Tariff> tariffs) {
        int total = 0;

        for (Tariff tariff : tariffs) {
            total += tariff.getNumSubscriber();
        }

        LOG.info("Successful completion of countClients");
        return total;
    }

}