package com.example.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticketingapp.models.LoginRequest;
import com.example.ticketingapp.models.Traveler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

// This class represents the login activity of the Ticketing App
public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailLogin);
        passwordEditText = findViewById(R.id.emailPassword);
        loginButton = findViewById(R.id.loginButton);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eadpvtltd.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ApiService
        apiService = retrofit.create(ApiService.class);

        // Set a click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input
                String mail = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Create a LoginRequest object
                LoginRequest loginRequest = new LoginRequest(mail, password);

                // Make the API call with the LoginRequest object as the request body
                Call<List<Traveler>> call = apiService.loginTraveler(loginRequest);
                call.enqueue(new Callback<List<Traveler>>() {
                    @Override
                    public void onResponse(Call<List<Traveler>> call, Response<List<Traveler>> response) {
                        // Handle the API response
                        Log.d("Response Code", String.valueOf(response.code()));
                        Log.d("Response Message", response.message());
                        if (response.isSuccessful()) {
                            List<Traveler> travelers = response.body();
                            if (travelers != null && !travelers.isEmpty()) {
                                Traveler traveler = travelers.get(0);
                                String nic = traveler.getNic();

                                // Log the traveler object

                                Log.d("Traveler Data", "Name: " + traveler.getName());
                                Log.d("Traveler Data", "NIC: " + traveler.getNic());
                                Log.d("Traveler Data", "Mail: " + traveler.getEmail());
                                Log.d("Traveler Data", "Phone Number: " + traveler.getPhone());

//                                Log.d("NIC", "NIC value: " + nic);
                                showToast("Login successful!");
                                // Pass user data to the main activity or save it in shared preferences
                                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                                startActivity(intent);
                            } else {
                                // Handle the case where the response is successful but the list is empty
                                showToast("Login failed. Please check your credentials.");
                            }
                        } else {
                            // Handle the case where the response is not successful
                            showToast("Response is not successful");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Traveler>> call, Throwable t) {
                        // Handle the case where the API call failed
                        showToast("Login failed due to an error. Please try again later.");
                    }
                });
            }
        });
    }

    // Helper method to display a toast message
    private void showToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}