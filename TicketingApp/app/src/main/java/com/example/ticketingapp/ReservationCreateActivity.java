package com.example.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticketingapp.models.Reservation;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationCreateActivity extends AppCompatActivity {
    private ApiService apiService;
    private AutoCompleteTextView routeDropdown;
    private List<Route> routeList = new ArrayList<>();
    private EditText nameEditText;
    private EditText nicEditText;
    private EditText phoneEditText;
    private EditText dateEditText;
    private Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_create);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Initialize Retrofit with your base URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eadpvtltd.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of your ApiService interface
        apiService = retrofit.create(ApiService.class);

        // Initialize UI components
        routeDropdown = findViewById(R.id.dropdownField);
        nameEditText = findViewById(R.id.nameReservation);
        nicEditText = findViewById(R.id.nicReservation);
        phoneEditText = findViewById(R.id.phoneReservation);
        dateEditText = findViewById(R.id.dateReservation);
        reserveButton = findViewById(R.id.reserveButton);

        Call<List<Route>> call = apiService.getAllRoutes();
        call.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    routeList = response.body();

                    // Populate the dropdown with route names
                    ArrayAdapter<Route> adapter = new ArrayAdapter<>(ReservationCreateActivity.this,
                            android.R.layout.simple_dropdown_item_1line, routeList);
                    routeDropdown.setAdapter(adapter);
                } else {
                    // Handle API error
                    Toast.makeText(ReservationCreateActivity.this, "Failed to fetch routes.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {
                // Handle API call failure
                Toast.makeText(ReservationCreateActivity.this, "API call failed.", Toast.LENGTH_SHORT).show();
            }
        });

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String selectedRoute = routeDropdown.getText().toString();
                String name = nameEditText.getText().toString();
                String nic = nicEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String date = dateEditText.getText().toString();

                // Create a Reservation object
                Reservation reservation = new Reservation(selectedRoute, name, nic, phone, date);

                // Call the API to create a reservation
                Call<Void> reservationCall = apiService.createReservation(reservation);
                reservationCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // Reservation successful
                            Toast.makeText(ReservationCreateActivity.this, "Reservation successful!", Toast.LENGTH_SHORT).show();
                            finish(); // Finish this activity (optional)
                        } else {
                            // Reservation failed
                            Toast.makeText(ReservationCreateActivity.this, "Reservation failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Handle API call failure
                        Toast.makeText(ReservationCreateActivity.this, "API call failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}