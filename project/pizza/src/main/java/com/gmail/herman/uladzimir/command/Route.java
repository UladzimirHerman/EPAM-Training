package com.gmail.herman.uladzimir.command;

import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_LOGIN_PAGE;

public class Route {

    private ResponseType responseType;
    private String responsePath;

    public Route() {
        responseType = ResponseType.REDIRECT;
        responsePath = REDIRECT_TO_LOGIN_PAGE;
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