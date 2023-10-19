package com.example.ticketingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketingapp.models.Reservation;
import com.example.ticketingapp.models.RouteInfo;
import com.example.ticketingapp.models.TrainRoutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// This class represents the reservation creation activity of the Ticketing App
public class ReservationCreateActivity extends AppCompatActivity {
    private Spinner routeSpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_reservation_create);

        Spinner routeSpinner = findViewById(R.id.routeSpinner);
        EditText nameEditText = findViewById(R.id.nameReservation);
        EditText nicEditText = findViewById(R.id.nicReservation);
        EditText phoneEditText = findViewById(R.id.phoneReservation);
        EditText dateEditText = findViewById(R.id.dateReservation);
        EditText seatEditText = findViewById(R.id.seatReservation);


        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eadpvtltd.azurewebsites.net/") // Base URL of your API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Make the API call to get train route data
        Call<MyCustomResponse> call = apiService.getAllTrainRoutes();

        call.enqueue(new Callback<MyCustomResponse>() {
            @Override
            public void onResponse(Call<MyCustomResponse> call, Response<MyCustomResponse> response) {
                if (response.isSuccessful()) {
                    MyCustomResponse customResponse = response.body();
                    List<TrainRoutes> routes = customResponse.getData();

                    // Create a list of RouteInfo objects
                    List<String> routeInfoList = new ArrayList<>();
                    for (TrainRoutes route : routes) {
                        String routeInfo = route.getStartPlace() + " - " + route.getDestination();

                        // Create a RouteInfo object for the route
                        RouteInfo routeInfoObject = new RouteInfo(routeInfo);

                        // Add the RouteInfo object to the list
                        routeInfoList.add(routeInfoObject.toLogString());
                    }
                    Log.d("TAG", "API call to get train routes succeeded.");
                    // Populate the Spinner with the RouteInfo objects
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ReservationCreateActivity.this, android.R.layout.simple_spinner_item, routeInfoList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    routeSpinner.setAdapter(adapter);
                } else {
                    Log.e("TAG", "API call to get train routes failed.");
                    Toast.makeText(ReservationCreateActivity.this, "Failed to fetch route data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MyCustomResponse> call, Throwable t) {
                // Handle network or API call failure
                Toast.makeText(ReservationCreateActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        // Create a reservation when the "Reserve" button is clicked
        Button reserveButton = findViewById(R.id.reserveButton);
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected route from the Spinner
                String train = routeSpinner.getSelectedItem().toString();
                String name = nameEditText.getText().toString();
                String nic = nicEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String reservationDate = dateEditText.getText().toString();
                String SeatNumber = seatEditText.getText().toString();

                // Implement reservation creation logic here
                Log.d("TAG", "Creating reservation with route: " + train);
                createReservation(train, name, nic, phone, reservationDate, SeatNumber);
            }
        });

    }

    // Helper method to create a reservation
    private void createReservation(String route, String name, String nic, String phone, String date, String seat) {
        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eadpvtltd.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 24;
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        String result = randomString.toString();

        // Create a Reservation object with the provided data
        Reservation reservation = new Reservation();
        reservation.setTrain(route);
        reservation.setName(name);
        reservation.setNIC(nic);
        reservation.setPhoneNo(phone);
        reservation.setReservationDate(date);
        reservation.setSeatNumber(seat);
        String create_date="";
        reservation.setCreateDate(create_date);
        reservation.setGenerateID(result);

        // Make the API call to create a reservation
        Call<MyApiResponse> call = apiService.createReservation(reservation);

        call.enqueue(new Callback<MyApiResponse>() {
            @Override
            public void onResponse(Call<MyApiResponse> call, Response<MyApiResponse> response) {
                if (response.isSuccessful()) {
                    MyApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.isSuccess()) {
                        // Reservation was successfully created
                        Log.d("TAG", "Reservation created successfully. Message: " + apiResponse.getMessage());
                        Toast.makeText(ReservationCreateActivity.this, "Reservation created successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle API errors
                        Log.e("TAG", "Failed to create reservation. Message: " + apiResponse.getMessage());
                        Toast.makeText(ReservationCreateActivity.this, "Failed to create reservation", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle API errors
                    Log.e("TAG", "Failed to create reservation. Response Code: " + response.code());

                    // Log the response body as a string
                    try {
                        String responseString = response.errorBody().string();
                        Log.e("TAG", "Response Body: " + responseString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(ReservationCreateActivity.this, "Failed to create reservation", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MyApiResponse> call, Throwable t) {
                // Handle network or other errors
                Log.e("TAG", "Network error while creating a reservation.", t);
                Toast.makeText(ReservationCreateActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

