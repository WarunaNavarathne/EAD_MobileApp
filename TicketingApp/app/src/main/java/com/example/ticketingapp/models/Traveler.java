package com.example.ticketingapp.models;

public class Traveler {
    private String Id;
    private String NIC;
    private String Name;
    private String Mail;
    private String PhoneNo;
    private String JoinDate; // You can set this automatically on the server-side
    private String Password;
    private String IsActive;

    // Setter methods
    public void setName(String name) {
        this.Name = name;
    }

    public void setNic(String nic) {
        this.NIC = nic;
    }

    public void setEmail(String email) {
        this.Mail = email;
    }

    public void setPhone(String phone) {
        this.PhoneNo = phone;
    }

    public void setDate(String date) {
        this.JoinDate = date;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    // Getter methods
    public String getName() {
        return Name;
    }

    public String getNic() {
        return NIC;
    }

    public String getEmail() {
        return Mail;
    }

    public String getPhone() {
        return PhoneNo;
    }

    public String getDate() {
        return JoinDate;
    }

    public String getPassword() {
        return Password;
    }
}
