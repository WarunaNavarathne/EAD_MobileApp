package com.example.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ticketingapp.models.Traveler;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import android.widget.Toast;

// This class represents the user registration activity of the Ticketing App
public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, nicEditText, emailEditText, phoneEditText, passwordEditText;
    private Button registerButton;

    // Define a helper method to show a Toast message
    private void showToast(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_register);

        // Initialize UI elements
        nameEditText = findViewById(R.id.nameRegister);
        nicEditText = findViewById(R.id.nicRegister);
        emailEditText = findViewById(R.id.mailRegister);
        phoneEditText = findViewById(R.id.phoneRegister);
        passwordEditText = findViewById(R.id.passwordRegister);
        registerButton = findViewById(R.id.loginButton);


        // Set a click listener for the registration button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from input fields
                String name = nameEditText.getText().toString();
                String nic = nicEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String date = "";

                // Create a Traveler object
                Traveler traveler = new Traveler();
                traveler.setName(name);
                traveler.setNic(nic);
                traveler.setEmail(email);
                traveler.setPhone(phone);
                traveler.setPassword(password);
                traveler.setDate(date);

                // Make the API call using Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://eadpvtltd.azurewebsites.net/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);

                Call<Void> call = apiService.createTraveler(traveler);

                // Handle the API response
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("Response Code", String.valueOf(response.code()));
                        Log.d("Response Message", response.message());
                        if (response.isSuccessful()) {
                            // Registration successful
                            showToast("Registration is complete!");
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // Registration failed
                            showToast("Registration is Failed!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // API call failed
                        showToast("Server Error!");
                    }
                });
            }
        });
    }

}