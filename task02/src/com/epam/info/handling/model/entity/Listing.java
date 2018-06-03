package com.epam.info.handling.model.entity;

public class Listing extends TextPart {

    private String listing;

    public Listing(String listing) {
        this.listing = listing;
    }

    public String getListing() {
        return listing;
    }

    public void setListing(String listing) {
        this.listing = listing;
    }

    @Override
    public String build() {
        return listing;
    }

}