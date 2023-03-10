package com.kartik.campusBuddy.Models;

public class ServiceModel {
    String name;
    String location;
    String price;
    String imageUrl1;
    String imageUrl2;
    String imageUrl3;

    public ServiceModel(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public ServiceModel(String name, String price, String location, String imageUrl1, String imageUrl2, String imageUrl3) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }
}
