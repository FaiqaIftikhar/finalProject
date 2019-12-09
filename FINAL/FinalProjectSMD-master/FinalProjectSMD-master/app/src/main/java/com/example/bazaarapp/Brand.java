package com.example.bazaarapp;

import android.content.Intent;
import android.view.View;

import java.util.List;

public class Brand {
    private String BrandName;
    private int rating;
    private String category;
    private String BrandLogo;
    private String SaleDescription;
    ////for location
    private double longitude;
    private double latitude;
    ////for contact number
    private String Timings;
    private List<ReviewByUser> reviews;
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public Brand(){

        //required constructor
    }
    public void backAcivity(View view){
        view.getContext().startActivity(new Intent(view.getContext(), home.class));
    }
    Brand(String Name,String category,String BrandLogo,Double longitude,Double latitude,String timings,String contact){
        this.BrandName=Name;
        this.category=category;
        this.BrandLogo=BrandLogo;
        this.latitude=latitude;
        this.longitude=longitude;
        this.Timings=timings;
        this.rating=0;
        this.contact=contact;
        this.SaleDescription="No sale Just YET!";
        reviews=null;

    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrandLogo() {
        return BrandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        BrandLogo = brandLogo;
    }

    public String getSaleDescription() {
        return SaleDescription;
    }

    public void setSaleDescription(String saleDescription) {
        SaleDescription = saleDescription;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTimings() {
        return Timings;
    }

    public void setTimings(String timings) {
        Timings = timings;
    }

    public List<ReviewByUser> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewByUser> reviews) {
        this.reviews = reviews;
    }
}
