package com.example.android.readusers.entities;

/**
 * Created by Android on 6/12/2017.
 */

public class UserInfo {

    private String imageUrl;
    private String name;
    private String address;
    private String email;
    private long id;

    //empty constructor for testing
    public UserInfo() {
    }

    public UserInfo(String image, String name, String address, String email) {
        this.imageUrl = image;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
