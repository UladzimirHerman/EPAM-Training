package com.epam.info.handling.model.entity;

public class Component implements Content {

    private String component;

    public Component(String component) {
        this.component = component;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public String build() {
        return component;
    }

}