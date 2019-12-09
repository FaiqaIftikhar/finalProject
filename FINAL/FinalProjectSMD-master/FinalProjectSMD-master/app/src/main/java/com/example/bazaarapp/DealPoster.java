package com.example.bazaarapp;

public class DealPoster {
    private String ImageUrl;
    private String BrandName;
    private String DealName;
    private String description;
    public DealPoster(){

        //required constructor
    }
    public DealPoster(String url,String b,String deal,String description){
        if(deal.trim().equals("")){
            deal="Check Out!";
        }
        this.ImageUrl=url;
        this.BrandName=b;
        this.DealName=deal;
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

    public String getDealName() {
        return DealName;
    }

    public void setDealName(String dealName) {
        DealName = dealName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
