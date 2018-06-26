package com.epam.mobile.service.parser;

import com.epam.mobile.model.CorporateTariff;
import com.epam.mobile.model.PersonalTariff;
import com.epam.mobile.model.Tariff;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StAXParser implements Parser {

    private static final Logger LOG = Logger.getLogger(StAXParser.class);

    @Override
    public List<Tariff> parse(String xmlPath) throws IOException, XMLStreamException {
        LOG.info("Run parsing XML with StAXParser");

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlPath));

        List<Tariff> tariffs = new ArrayList<>();
        Tariff tariff = null;
        StartElement startElement;
        String element;
        TariffTagName tagName;

        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();

            if (xmlEvent.isStartElement()) {
                startElement = xmlEvent.asStartElement();
                element = startElement.getName().getLocalPart();
                tagName = TariffTagName.valueOf(element.toUpperCase());

                switch (tagName) {
                    case TARIFF:
                        tariff = startElement.
                                getAttributeByName(new QName(TariffTagName.TYPE.name().toLowerCase())).getValue().
                                equalsIgnoreCase(TariffTagName.CORPORATE_TARIFF.name()) ?
                                new CorporateTariff() : new PersonalTariff();
                        break;
                    case NAME:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setName(xmlEvent.asCharacters().getData());
                        break;
                    case DESCRIPTION:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setDescription(xmlEvent.asCharacters().getData());
                        break;
                    case IN_ARCHIVE:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setInArchive(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                        break;
                    case SUBSCRIPTION_FEE:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setSubscriptionFee(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case NUM_SUBSCRIBER:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setNumSubscriber(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        break;
                    case FREE_MINUTE:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setFreeMinute(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        break;
                    case COST_MINUTE:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setCostMinute(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case FREE_SMS:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setFreeSms(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        break;
                    case COST_SMS:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setCostSms(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case FREE_INTERNET:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setFreeInternet(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        break;
                    case COST_INTERNET:
                        xmlEvent = xmlEventReader.nextEvent();
                        tariff.setCostInternet(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case FREE_COLLEAGUE_MINUTE:
                        xmlEvent = xmlEventReader.nextEvent();
                        ((CorporateTariff) tariff).
                                setFreeColleagueMinute(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        break;
                    case COST_COLLEAGUE_MINUTE:
                        xmlEvent = xmlEventReader.nextEvent();
                        ((CorporateTariff) tariff).
                                setCostColleagueMinute(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                    case FREE_INTERNATIONAL_MINUTE:
                        xmlEvent = xmlEventReader.nextEvent();
                        ((PersonalTariff) tariff).
                                setFreeInternationalMinute(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        break;
                    case COST_INTERNATIONAL_MINUTE:
                        xmlEvent = xmlEventReader.nextEvent();
                        ((PersonalTariff) tariff).
                                setCostInternationalMinute(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        break;
                }

            }

            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().
                        equalsIgnoreCase(TariffTagName.TARIFF.name())) {
                    tariffs.add(tariff);
                }
            }
        }

        LOG.info("Successfully parsing XML with StAXParser");
        return tariffs;
    }

}