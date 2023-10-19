package com.example.ticketingapp;


// Create a generic class called ApiResponse with a type parameter T
public class ApiResponse<T> {
    private T data;

    public T getData() {
        return data;
    }
}
