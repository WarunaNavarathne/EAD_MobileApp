package com.example.ticketingapp.models;

public class Reservation {
    private String train;
    private String name;
    private String NIC;
    private String phoneNo;
    private String reservationDate;

    public Reservation(String train, String name, String NIC, String phoneNo, String reservationDate) {
        this.train = train;
        this.name = name;
        this.NIC = NIC;
        this.phoneNo = phoneNo;
        this.reservationDate = reservationDate;
    }

    // Getter and Setter for train
    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for NIC
    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    // Getter and Setter for phoneNo
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    // Getter and Setter for reservationDate
    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
