package com.gmail.herman.uladzimir.route;

import static com.gmail.herman.uladzimir.route.ResponsePath.REDIRECT_TO_LOGIN_PAGE;

/**
 * Class {@link Route} defines the answer type and page address.
 *
 * @author Uladzimir Herman
 * @see ResponseType
 * @see ResponsePath
 */
public class Route {

    private ResponseType responseType;
    private String responsePath;

    /**
     * Default constructor, which initializes the route with default values
     */
    public Route() {
        responseType = ResponseType.REDIRECT;
        responsePath = REDIRECT_TO_LOGIN_PAGE;
    }

    /**
     * Additional constructor, which initializes the route with particular value
     *
     * @param responseType answer type
     * @param responsePath page address
     */
    public Route(ResponseType responseType, String responsePath) {
        this.responseType = responseType;
        this.responsePath = responsePath;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getResponsePath() {
        return responsePath;
    }

    public void setResponsePath(String responsePath) {
        this.responsePath = responsePath;
    }

}