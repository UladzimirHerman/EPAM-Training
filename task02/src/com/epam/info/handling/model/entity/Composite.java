package com.epam.info.handling.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Content {

    private List<Content> contentList;

    public Composite() {
        contentList = new ArrayList<>();
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public void add(Content item) {
        contentList.add(item);
    }

    public void remove(Content item) {
        contentList.remove(item);
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Content content : contentList) {
            stringBuilder.append(content.build());
        }

        return stringBuilder.toString();
    }

}