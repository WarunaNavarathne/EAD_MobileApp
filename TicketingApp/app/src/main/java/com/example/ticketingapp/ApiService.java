package com.example.ticketingapp;
import com.example.ticketingapp.models.Reservation;
import com.example.ticketingapp.models.Traveler;
import com.example.ticketingapp.models.UserProfile;

import java.util.List;

import okhttp3.Route;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/Traveller/Create")
    Call<Void> createTraveler(@Body Traveler traveler);

    @POST("api/Login/TravellerLogin")
    Call<Void> loginTraveler(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/Train/GetAll")
    Call<List<Route>> getAllRoutes();

    @POST("api/Ticket/Create")
    Call<Void> createReservation(@Body Reservation reservation);

    // Get user profile by NIC
    @GET("api/Traveller/GetByNIC/{nic}")
    Call<UserProfile> getUserProfileByNIC(@Path("nic") String nic);

    // Update user profile by NIC
    @PUT("api/Traveller/UpdateByNIC/{nic}")
    Call<Void> updateUserProfileByNIC(@Path("nic") String nic, @Body UserProfile userProfile);


}
