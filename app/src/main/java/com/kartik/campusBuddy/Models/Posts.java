package com.kartik.campusBuddy.Models;

public class Posts {
    String userName;
    String imageUrl;

    public Posts(String userName, String imageUrl) {
        this.userName = userName;
        this.imageUrl = imageUrl;
    }
    public Posts(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
