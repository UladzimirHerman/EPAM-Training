package com.epam.herman.uladzimir.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;

/**
 * Class {@link RequestWrapper} is a wrapper for request. It gets and keeps
 * needed attributes and parameters from controller.
 *
 * @author Uladzimir Herman
 * @see Controller
 */
public class RequestWrapper {

    private static final String EMPTY = "";
    private static final String REFERER = "referer";
    private static final String SKIPPED_START_ADDRESS = "http://localhost:8080";

    private Map<String, Object> sessionAttributes = new HashMap<>();
    private Map<String, Object> requestAttributes = new HashMap<>();
    private Map<String, String[]> requestParameters;
    private List<Part> parts;
    private String applicationPath;
    private String referer;

    /**
     * Getting necessary information and initializing data members
     *
     * @param request request from controller
     */
    public void initialize(HttpServletRequest request) {
        Object object;
        String name;

        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();
        while (sessionAttributeNames.hasMoreElements()) {
            name = sessionAttributeNames.nextElement();
            object = session.getAttribute(name);
            sessionAttributes.put(name, object);
        }

        Enumeration<String> requestAttributeNames = request.getAttributeNames();
        while (requestAttributeNames.hasMoreElements()) {
            name = requestAttributeNames.nextElement();
            object = request.getAttribute(name);
            requestAttributes.put(name, object);
        }

        requestParameters = new HashMap<>(request.getParameterMap());

        try {
            parts = new ArrayList<>(request.getParts());
        } catch (IOException | ServletException e) {
            parts = null;
        }

        applicationPath = request.getServletContext().getRealPath(EMPTY);

        referer = request.getHeader(REFERER);
    }

    /**
     * Adding session attribute by key
     *
     * @param key    key of attribute
     * @param object object for putting
     */
    public void putSessionAttribute(String key, Object object) {
        removeSessionAttribute(key);
        sessionAttributes.put(key, object);
    }

    /**
     * Getting session attribute by key
     *
     * @param key key of attribute
     * @return particular object
     */
    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    /**
     * Removing session attribute by key
     *
     * @param key key of attribute
     */
    public void removeSessionAttribute(String key) {
        sessionAttributes.put(key, null);
    }

    /**
     * Adding request attribute by key
     *
     * @param key    key of attribute
     * @param object object for putting
     */
    public void putRequestAttribute(String key, Object object) {
        removeRequestAttribute(key);
        requestAttributes.put(key, object);
    }

    /**
     * Getting request attribute by key
     *
     * @param key key of attribute
     * @return particular object
     */
    public Object getRequestAttribute(String key) {
        return requestAttributes.get(key);
    }

    /**
     * Removing request attribute by key
     *
     * @param key key of attribute
     */
    private void removeRequestAttribute(String key) {
        requestAttributes.put(key, null);
    }

    /**
     * Getting request parameter by key
     *
     * @param key key for searching
     * @return parameter if exists or empty string if parameter doesn't exist
     */
    public String getRequestParameter(String key) {
        return requestParameters.containsKey(key) ?
                requestParameters.get(key)[0] : EMPTY;
    }

    /**
     * Updating request from wrapper
     *
     * @param request request from controller
     */
    public void update(HttpServletRequest request) {

        for (Map.Entry<String, Object> attribute : requestAttributes.entrySet()) {
            request.setAttribute(attribute.getKey(), attribute.getValue());
        }

        for (Map.Entry<String, Object> attribute : sessionAttributes.entrySet()) {
            request.getSession().setAttribute(attribute.getKey(), attribute.getValue());
        }

    }

    /**
     * Getting the parts of the input file
     *
     * @return parts
     */
    public List<Part> getParts() {
        return parts;
    }

    /**
     * Getting application path
     *
     * @return application path
     */
    public String getApplicationPath() {
        return applicationPath;
    }

    /**
     * Getting URL of referrer
     *
     * @return particular URL
     */
    public String getReferer() {
        return referer.substring(SKIPPED_START_ADDRESS.length());
    }

}