package com.example.bazaarapp;

public class categoryData {
    String heading;
    String contet;
    int id_;
    String image1;
    String  image2;

    public categoryData(String name, String version, int id_, String image1,String image2) {
        this.heading = name;
        this.contet = version;
        this.id_ = id_;
        this.image1=image1;
        this.image2=image2;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContet() {
        return contet;
    }

    public void setContet(String contet) {
        this.contet = contet;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
}