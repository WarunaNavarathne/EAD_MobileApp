package com.example.ticketingapp;

import com.example.ticketingapp.models.TrainRoutes;

import java.util.List;

// This class represents a custom response structure for the Ticketing App
public class MyCustomResponse {
    private List<TrainRoutes> data;

    // Getter method to retrieve the data contained in the response
    public List<TrainRoutes> getData() {
        return data;
    }

    // Setter method to set the data within the response
    public void setData(List<TrainRoutes> data) {
        this.data = data;
    }
}
