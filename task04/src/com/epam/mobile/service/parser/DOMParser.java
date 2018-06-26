package com.epam.mobile.service.parser;

import com.epam.mobile.model.CorporateTariff;
import com.epam.mobile.model.PersonalTariff;
import com.epam.mobile.model.Tariff;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser implements Parser {

    private static final Logger LOG = Logger.getLogger(DOMParser.class);

    @Override
    public List<Tariff> parse(String xmlPath)
            throws ParserConfigurationException, IOException, SAXException {
        LOG.info("Run parsing XML with DOMParser");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(xmlPath));

        Element root = document.getDocumentElement();

        NodeList children = root.getChildNodes();

        List<Tariff> tariffs = new ArrayList<>();
        Tariff tariff;
        TariffTagName tagName;

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            NodeList elements = child.getChildNodes();

            if (child instanceof Element) {
                tariff = ((Element) child).getAttribute(TariffTagName.TYPE.name().toLowerCase()).
                        equalsIgnoreCase(TariffTagName.CORPORATE_TARIFF.name()) ?
                        new CorporateTariff() : new PersonalTariff();

                for (int j = 0; j < elements.getLength(); j++) {
                    Node node = elements.item(j);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        tagName = TariffTagName.valueOf(node.getNodeName().toUpperCase());
                        switch (tagName) {
                            case NAME:
                                tariff.setName(node.getTextContent());
                                break;
                            case DESCRIPTION:
                                tariff.setDescription(node.getTextContent());
                                break;
                            case IN_ARCHIVE:
                                tariff.setInArchive(Boolean.parseBoolean(node.getTextContent()));
                                break;
                            case SUBSCRIPTION_FEE:
                                tariff.setSubscriptionFee(Double.parseDouble(node.getTextContent()));
                                break;
                            case NUM_SUBSCRIBER:
                                tariff.setNumSubscriber(Integer.parseInt(node.getTextContent()));
                                break;
                            case FREE_MINUTE:
                                tariff.setFreeMinute(Integer.parseInt(node.getTextContent()));
                                break;
                            case COST_MINUTE:
                                tariff.setCostMinute(Double.parseDouble(node.getTextContent()));
                                break;
                            case FREE_SMS:
                                tariff.setFreeSms(Integer.parseInt(node.getTextContent()));
                                break;
                            case COST_SMS:
                                tariff.setCostSms(Double.parseDouble(node.getTextContent()));
                                break;
                            case FREE_INTERNET:
                                tariff.setFreeInternet(Integer.parseInt(node.getTextContent()));
                                break;
                            case COST_INTERNET:
                                tariff.setCostInternet(Double.parseDouble(node.getTextContent()));
                                break;
                            case FREE_COLLEAGUE_MINUTE:
                                ((CorporateTariff) tariff).
                                        setFreeColleagueMinute(Integer.parseInt(node.getTextContent()));
                                break;
                            case COST_COLLEAGUE_MINUTE:
                                ((CorporateTariff) tariff).
                                        setCostColleagueMinute(Double.parseDouble(node.getTextContent()));
                                break;
                            case FREE_INTERNATIONAL_MINUTE:
                                ((PersonalTariff) tariff).
                                        setFreeInternationalMinute(Integer.parseInt(node.getTextContent()));
                                break;
                            case COST_INTERNATIONAL_MINUTE:
                                ((PersonalTariff) tariff).
                                        setCostInternationalMinute(Double.parseDouble(node.getTextContent()));
                                break;
                        }
                    }

                }

                tariffs.add(tariff);
            }
        }

        LOG.info("Successfully parsing XML with DOMParser");
        return tariffs;
    }

}