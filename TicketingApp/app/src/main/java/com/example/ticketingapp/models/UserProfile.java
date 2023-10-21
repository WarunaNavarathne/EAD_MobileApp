package com.example.ticketingapp.models;

import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("Id")
    private String Id;
    @SerializedName("Name")
    private String Name;

    @SerializedName("NIC")
    private String NIC;

    @SerializedName("Mail")
    private String Mail;

    @SerializedName("PhoneNo")
    private String PhoneNo;

    @SerializedName("Password")
    private String Password;

    @SerializedName("JoinDate")
    private String JoinDate;

    @SerializedName("IsActive")
    private boolean IsActive;

    public UserProfile(String id, String name, String NIC, String mail, String phoneNo, String password, String joinDate, boolean isActive) {
        Id = id;
        Name = name;
        this.NIC = NIC;
        Mail = mail;
        PhoneNo = phoneNo;
        Password = password;
        JoinDate = joinDate;
        IsActive = isActive;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean isActive) {
        IsActive = isActive;
    }
}
