package com.example.ticketingapp.models;

public class Reservation {

    private String GenerateID;
    private String Train;
    private String Name;
    private String NIC;
    private String PhoneNo;
    private String ReservationDate;
    private String SeatNumber;

    public String getGenerateID() {
        return GenerateID;
    }

    public void setGenerateID(String generateID) {
        GenerateID = generateID;
    }

    private String CreateDate;

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getSeatNumber() {
        return SeatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.SeatNumber = seatNumber;
    }


    // Getter and Setter for train
    public String getTrain() {
        return Train;
    }

    public void setTrain(String Train) {
        this.Train = Train;
    }

    // Getter and Setter for name
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    // Getter and Setter for reservationDate
    public String getReservationDate() {
        return ReservationDate;
    }

    public void setReservationDate(String ReservationDate) {
        this.ReservationDate = ReservationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "route='" + Train + '\'' +
                ", name='" + Name + '\'' +
                ", nic='" + NIC + '\'' +
                ", phone='" + PhoneNo + '\'' +
                ", date='" + ReservationDate + '\'' +
                ", seat='" + SeatNumber + '\'' +
                '}';
    }
}
