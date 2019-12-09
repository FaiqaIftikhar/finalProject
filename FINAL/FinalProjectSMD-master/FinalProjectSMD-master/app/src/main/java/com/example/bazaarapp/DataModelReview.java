package com.example.bazaarapp;

public class DataModelReview {
    String name_review;
    int id_review;

    public DataModelReview(String name, int id_) {
        this.name_review= name;
        this.id_review = id_;
    }

    public String getName_review() {
        return name_review;
    }


    public int getId() {
        return id_review;
    }
}
