package com.epam.mobile.controller;

import com.epam.mobile.model.Company;
import com.epam.mobile.model.CorporateTariff;
import com.epam.mobile.model.PersonalTariff;
import com.epam.mobile.model.Tariff;
import com.epam.mobile.util.TariffSearcher;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;

public class Starter {

    static {
        new DOMConfigurator().doConfigure("resources/log4j.xml",
                LogManager.getLoggerRepository());
    }

    private static final Logger LOG = Logger.getLogger(Starter.class);

    public static void main(String[] args) {

        /**
         * Creating company
         * @see Company
         */
        LOG.error("The company doesn't exist");
        Company company = new Company();
        LOG.debug("Successful creation of the company");

        /**
         * Creating and adding tariffs in company
         * @see CorporateTariff
         * @see PersonalTariff
         */
        LOG.error("There is no tariff");
        CorporateTariff corporateTariff = new CorporateTariff();
        corporateTariff.setName("Corporation");
        corporateTariff.setDescription("Maintain communication between colleagues");
        corporateTariff.setInArchive(false);
        corporateTariff.setSubscriptionFee(50);
        corporateTariff.setNumSubscriber(5);
        corporateTariff.setFreeMinute(100);
        corporateTariff.setCostMinute(0.03);
        corporateTariff.setFreeSms(0);
        corporateTariff.setCostSms(0.04);
        corporateTariff.setFreeInternet(1024);
        corporateTariff.setCostInternet(0.01);
        corporateTariff.setFreeColleagueMinute(10000);
        corporateTariff.setCostColleagueMinute(0.05);
        company.addTariff(corporateTariff);

        PersonalTariff personalTariff1 = new PersonalTariff();
        personalTariff1.setName("Comfort");
        personalTariff1.setDescription("For a better life");
        personalTariff1.setInArchive(true);
        personalTariff1.setSubscriptionFee(11.5);
        personalTariff1.setNumSubscriber(7);
        personalTariff1.setFreeMinute(500);
        personalTariff1.setCostMinute(0.07);
        personalTariff1.setFreeSms(50);
        personalTariff1.setCostSms(0.05);
        personalTariff1.setFreeInternet(512);
        personalTariff1.setCostInternet(0.08);
        personalTariff1.setFreeInternationalMinute(10);
        personalTariff1.setCostInternationalMinute(0.95);
        company.addTariff(personalTariff1);

        PersonalTariff personalTariff2 = new PersonalTariff();
        personalTariff2.setName("Online");
        personalTariff2.setDescription("For Internet users");
        personalTariff2.setInArchive(false);
        personalTariff2.setSubscriptionFee(12);
        personalTariff2.setNumSubscriber(14);
        personalTariff2.setFreeMinute(100);
        personalTariff2.setCostMinute(0.08);
        personalTariff2.setFreeSms(0);
        personalTariff2.setCostSms(0.01);
        personalTariff2.setFreeInternet(5120);
        personalTariff2.setCostInternet(0.02);
        personalTariff2.setFreeInternationalMinute(0);
        personalTariff2.setCostInternationalMinute(0.8);
        company.addTariff(personalTariff2);
        LOG.debug("Tariffs successfully added");

        /**
         * View company tariffs
         */
        StringBuilder tariffs = new StringBuilder("COMPANY TARIFFS:\n");
        tariffs.append(company.showAllTariff());
        LOG.info(tariffs);

        /**
         * Company tariffs sorted by subscription fee
         */
        StringBuilder sortedTariffs = new StringBuilder("SORTED COMPANY TARIFFS:\n");
        company.sortTariff();
        sortedTariffs.append(company.showAllTariff());
        LOG.info(sortedTariffs);

        /**
         * Personal tariffs of the company in a given range of subscription fees
         */
        TariffSearcher tariffSearcher = new TariffSearcher();
        double min = 5, max = 11.5;
        ArrayList<Tariff> resultTariff =
                tariffSearcher.searchPersonalTariffBySubscriptionFeeDiapason(company.getAllTariff(), min, max);
        StringBuilder foundTariffs = new StringBuilder("PERSONAL TARIFFS WITH SUBSCRIPTION FEE $");
        foundTariffs.append(min);
        foundTariffs.append(" - $");
        foundTariffs.append(max);
        foundTariffs.append(":\n");
        for (Tariff tariff : resultTariff) {
            foundTariffs.append(tariff.toString());
            foundTariffs.append("\n");
        }
        LOG.info(foundTariffs);

        /**
         * Company clients
         */
        StringBuilder clients = new StringBuilder("TOTAL NUMBER OF CLIENTS: ");
        clients.append(tariffSearcher.countClients(company.getAllTariff()));
        LOG.info(clients);

    }
}