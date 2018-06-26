package com.epam.mobile.controller;

import com.epam.mobile.model.Company;
import com.epam.mobile.model.Tariff;
import com.epam.mobile.service.TariffSearcher;
import com.epam.mobile.service.XMLWorker;
import com.epam.mobile.service.parser.*;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class Starter {

    private static final Logger LOG = Logger.getLogger(Starter.class);

    private static final String XML_PATH = "resources\\tariff.xml";
    private static final String XSD_PATH = "resources\\tariff.xsd";

    public static void main(String[] args) {

        try {
            /**
             * Creating company
             * @see Company
             */
            Company company = new Company();
            LOG.info("Successfully creating of the company");

            /**
             * Validation XML by XSD
             */
            XMLWorker.validateXMLSchema(XML_PATH, XSD_PATH);

            /**
             * Three parsers for obtaining data
             */
            Parser parser = new DOMParser();
            //Parser parser = new SAXParser();
            //Parser parser = new StAXParser();

            company.setTariffs(parser.parse(XML_PATH));
            LOG.info("Successfully adding tariffs");

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
            List<Tariff> resultTariff = tariffSearcher.
                    searchPersonalTariffBySubscriptionFeeDiapason(company.getAllTariff(), min, max);
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
        } catch (IOException | SAXException | ParserConfigurationException | XMLStreamException e) {
            LOG.error("An error occurred while running: ", e);
        }

    }
}