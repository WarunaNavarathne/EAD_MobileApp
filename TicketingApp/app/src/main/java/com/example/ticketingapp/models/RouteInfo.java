package com.example.ticketingapp.models;

public class RouteInfo {
    private String startTime;
    private String arriveTime;
    private String startPlace;
    private String destination;
    private String price;

    public RouteInfo(String startTime, String arriveTime, String startPlace, String destination, String price) {
        this.startTime = startTime;
        this.arriveTime = arriveTime;
        this.startPlace = startPlace;
        this.destination = destination;
        this.price = price;
    }

    public String getFormattedText() {
        return startTime + " - " + arriveTime + " | " + startPlace + " to " + destination + " | Price: " + price;
    }
}
