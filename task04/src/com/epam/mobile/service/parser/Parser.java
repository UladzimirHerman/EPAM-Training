package com.epam.mobile.service.parser;

import com.epam.mobile.model.Tariff;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface Parser {

    List<Tariff> parse(String xmlPath) throws ParserConfigurationException,
            IOException, SAXException, XMLStreamException;

}