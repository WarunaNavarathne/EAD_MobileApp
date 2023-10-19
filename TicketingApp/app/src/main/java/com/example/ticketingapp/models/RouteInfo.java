package com.example.ticketingapp.models;

public class RouteInfo {
    private String routeInfo;
    private String timeInfo;
    private String priceInfo;

    public RouteInfo(String routeInfo) {
        this.routeInfo = routeInfo;
    }

    public String getRouteInfo() {
        return routeInfo;
    }

    public String getTimeInfo() {
        return timeInfo;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public String toLogString() {
        return routeInfo;
    }
}
