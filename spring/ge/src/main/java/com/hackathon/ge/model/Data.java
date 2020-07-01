package com.hackathon.ge.model;

import java.util.List;

public class Data {
    public List<Email> data;

    public Data() {
    }

    public Data(List<Email> data) {
        this.data = data;
    }

    public List<Email> getData() {
        return data;
    }

    public void setData(List<Email> data) {
        this.data = data;
    }
}
