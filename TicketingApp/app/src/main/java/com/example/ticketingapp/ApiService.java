package com.example.ticketingapp;
import com.example.ticketingapp.models.Reservation;
import com.example.ticketingapp.models.Traveler;

import java.util.List;

import okhttp3.Route;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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


}
