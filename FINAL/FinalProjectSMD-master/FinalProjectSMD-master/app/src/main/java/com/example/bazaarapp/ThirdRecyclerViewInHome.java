package com.example.bazaarapp;

public class ThirdRecyclerViewInHome {
    private String ImageUrl;
    private String BrandName;
    private String description;
    public ThirdRecyclerViewInHome(){

        //required constructor
    }
    public ThirdRecyclerViewInHome(String url,String b,String description){
        this.ImageUrl=url;
        this.BrandName=b;
        this.description=description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
