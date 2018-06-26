package com.epam.mobile.service.parser;

import com.epam.mobile.model.CorporateTariff;
import com.epam.mobile.model.PersonalTariff;
import com.epam.mobile.model.Tariff;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXParser implements Parser {

    private static final Logger LOG = Logger.getLogger(SAXParser.class);

    private List<Tariff> tariffs;
    private Tariff tariff;
    private TariffTagName tagName;

    @Override
    public List<Tariff> parse(String xmlPath)
            throws ParserConfigurationException, IOException, SAXException {
        LOG.info("Run parsing XML with SAXParser");

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();

        DefaultHandler defaultHandler = new DefaultHandler() {

            @Override
            public void startElement
                    (String uri, String localName, String qName, Attributes attributes) {

                if (!qName.equalsIgnoreCase(TariffTagName.TARIFFS.name())) {
                    tagName = TariffTagName.valueOf(qName.toUpperCase());
                }

                if (tagName == TariffTagName.TARIFF) {
                    tariff = attributes.getValue(TariffTagName.TYPE.name().toLowerCase()).
                            equalsIgnoreCase(TariffTagName.CORPORATE_TARIFF.name()) ?
                            new CorporateTariff() : new PersonalTariff();

                    if (tariffs == null) {
                        tariffs = new ArrayList<>();
                    }

                }

            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                if (qName.equalsIgnoreCase(TariffTagName.TARIFF.name())) {
                    tariffs.add(tariff);
                }
            }

            @Override
            public void characters(char ch[], int start, int length) {
                if (tagName != null) {
                    switch (tagName) {
                        case NAME:
                            tariff.setName(new String(ch, start, length));
                            break;
                        case DESCRIPTION:
                            tariff.setDescription(new String(ch, start, length));
                            break;
                        case IN_ARCHIVE:
                            tariff.setInArchive(Boolean.parseBoolean(new String(ch, start, length)));
                            break;
                        case SUBSCRIPTION_FEE:
                            tariff.setSubscriptionFee(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case NUM_SUBSCRIBER:
                            tariff.setNumSubscriber(Integer.parseInt(new String(ch, start, length)));
                            break;
                        case FREE_MINUTE:
                            tariff.setFreeMinute(Integer.parseInt(new String(ch, start, length)));
                            break;
                        case COST_MINUTE:
                            tariff.setCostMinute(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case FREE_SMS:
                            tariff.setFreeSms(Integer.parseInt(new String(ch, start, length)));
                            break;
                        case COST_SMS:
                            tariff.setCostSms(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case FREE_INTERNET:
                            tariff.setFreeInternet(Integer.parseInt(new String(ch, start, length)));
                            break;
                        case COST_INTERNET:
                            tariff.setCostInternet(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case FREE_COLLEAGUE_MINUTE:
                            ((CorporateTariff) tariff).
                                    setFreeColleagueMinute(Integer.parseInt(new String(ch, start, length)));
                            break;
                        case COST_COLLEAGUE_MINUTE:
                            ((CorporateTariff) tariff).
                                    setCostColleagueMinute(Double.parseDouble(new String(ch, start, length)));
                            break;
                        case FREE_INTERNATIONAL_MINUTE:
                            ((PersonalTariff) tariff).
                                    setFreeInternationalMinute(Integer.parseInt(new String(ch, start, length)));
                            break;
                        case COST_INTERNATIONAL_MINUTE:
                            ((PersonalTariff) tariff).
                                    setCostInternationalMinute(Double.parseDouble(new String(ch, start, length)));
                            break;
                    }
                    tagName = null;
                }
            }

        };

        saxParser.parse(xmlPath, defaultHandler);

        LOG.info("Successfully parsing XML with SAXParser");
        return tariffs;
    }

}