package com.example.ticketingapp.models;

import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("Name")
    private String name;

    @SerializedName("NIC")
    private String NIC;

    @SerializedName("Mail")
    private String mail;

    @SerializedName("Phone")
    private String phone;

    @SerializedName("Date")
    private String date;

    public UserProfile() {
        // Default constructor
    }

    public UserProfile(String name, String NIC, String mail, String phone, String date) {
        this.name = name;
        this.NIC = NIC;
        this.mail = mail;
        this.phone = phone;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
