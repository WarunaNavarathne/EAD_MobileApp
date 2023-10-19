package com.example.ticketingapp.models;

public class TrainRoutes {
    private String id;
    private String generateID;
    private String startPlace;
    private String destination;
    private String startTime;
    private String arriveTime;
    private String price;
    private int noOfSeats;

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public String getPrice() {
        return price;
    }
}
