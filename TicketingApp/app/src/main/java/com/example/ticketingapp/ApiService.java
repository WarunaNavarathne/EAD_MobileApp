package com.example.ticketingapp;
import com.example.ticketingapp.models.LoginRequest;
import com.example.ticketingapp.models.Reservation;
import com.example.ticketingapp.models.Traveler;
import com.example.ticketingapp.models.UserProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

// This interface defines the API endpoints for the Ticketing App
public interface ApiService {
    // Create a new traveler
    @POST("api/Traveller/Create")
    Call<Void> createTraveler(@Body Traveler traveler);

    // Login a traveler using a POST request with a LoginRequest
    @POST("api/Login/TravellerLogin")
    @Headers("Content-Type: application/json")
    Call<List<Traveler>> loginTraveler(@Body LoginRequest loginRequest);

    // Get a list of all train routes using a GET request
    @GET("api/Train/GetAll")
    Call<MyCustomResponse> getAllTrainRoutes();

    // Create a new reservation by sending a POST request with Reservation data
    @POST("api/Ticket/Create")
    Call<MyApiResponse> createReservation(@Body Reservation reservation);

    // Get user profile by NIC
    @GET("api/Traveller/GetByNIC/{nic}")
    Call<UserProfile> getUserProfileByNIC(@Path("nic") String nic);

    // Update user profile by NIC
    @PUT("api/Traveller/UpdateByNIC/{nic}")
    Call<Void> updateUserProfileByNIC(@Path("nic") String nic, @Body UserProfile userProfile);
}
