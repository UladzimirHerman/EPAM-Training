package com.gmail.herman.uladzimir.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;

public class RequestWrapper {

    private static final String REFERER = "referer";
    private static final String SKIPPED_START_ADDRESS = "http://localhost:8080";

    private Map<String, String[]> requestParameters;
    private Map<String, Object> requestAttributes = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private List<Part> parts;
    private String applicationPath;
    private String referer;

    public void initialize(HttpServletRequest request) {
        requestParameters = new HashMap<>(request.getParameterMap());

        Object object;
        String name;

        Enumeration<String> requestAttributeNames = request.getAttributeNames();
        while (requestAttributeNames.hasMoreElements()) {
            name = requestAttributeNames.nextElement();
            object = request.getAttribute(name);
            requestAttributes.put(name, object);
        }

        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();
        while (sessionAttributeNames.hasMoreElements()) {
            name = sessionAttributeNames.nextElement();
            object = session.getAttribute(name);
            sessionAttributes.put(name, object);
        }

        try {
            parts = new ArrayList<>(request.getParts());
        } catch (IOException | ServletException e) {
            parts = null;
        }

        applicationPath = request.getServletContext().getRealPath("");

        referer = request.getHeader(REFERER);
    }

    public String getRequestParameter(String key) {
        return requestParameters.containsKey(key) ? requestParameters.get(key)[0] : "";
    }

    public void removeSessionAttribute(String key) {
        sessionAttributes.put(key, null);
    }

    public void putSessionAttribute(String key, Object object) {
        removeSessionAttribute(key);
        sessionAttributes.put(key, object);
    }

    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    public void putRequestAttribute(String key, Object object) {
        removeRequestAttribute(key);
        requestAttributes.put(key, object);
    }

    public Object getRequestAttribute(String key) {
        return requestAttributes.get(key);
    }

    public void removeRequestAttribute(String key) {
        requestAttributes.put(key, null);
    }

    public void update(HttpServletRequest request) {
        for (Map.Entry<String, Object> attribute : requestAttributes.entrySet()) {
            request.setAttribute(attribute.getKey(), attribute.getValue());
        }
        for (Map.Entry<String, Object> attribute : sessionAttributes.entrySet()) {
            request.getSession().setAttribute(attribute.getKey(), attribute.getValue());
        }
    }

    public List<Part> getParts() {
        return parts;
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public String getReferer() {
        return referer.substring(SKIPPED_START_ADDRESS.length());
    }

}