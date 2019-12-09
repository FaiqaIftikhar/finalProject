package com.example.bazaarapp;

public class reviewClass {
   private String userEmail;
   private float rating;
   private String brandName;
   reviewClass(){

   }
   reviewClass(String email,float rate,String brandName){
       userEmail=email;
       rating=rate;
       this.brandName=brandName;
   }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
