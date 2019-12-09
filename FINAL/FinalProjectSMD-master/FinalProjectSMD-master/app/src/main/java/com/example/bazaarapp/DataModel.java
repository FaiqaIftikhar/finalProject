package com.example.bazaarapp;

public class DataModel {
    String heading;
    String contet;
    int id_;
    int image;

    public DataModel(String name, String version, int id_, int image) {
        this.heading = name;
        this.contet = version;
        this.id_ = id_;
        this.image=image;
    }

    public String getHeading() {
        return heading;
    }

    public String getContet() {
        return contet;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}
