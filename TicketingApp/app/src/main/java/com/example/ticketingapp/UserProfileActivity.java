package com.example.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticketingapp.models.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfileActivity extends AppCompatActivity {

    private ApiService apiService;
    private EditText nameEditText;
    private EditText nicEditText;
    private EditText mailEditText;
    private EditText phoneEditText;
    private EditText dateEditText;
    private Button updateButton;
    private Button activateDeactivateButton;

    // This class represents the user profile activity of the Ticketing App
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_user_profile);

        // Make the API call using Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eadpvtltd.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        nameEditText = findViewById(R.id.nameProfile);
        nicEditText = findViewById(R.id.nicProfile);
        mailEditText = findViewById(R.id.mailProfile);
        phoneEditText = findViewById(R.id.phoneProfile);
        dateEditText = findViewById(R.id.dateProfile);
        updateButton = findViewById(R.id.updateButton);
        activateDeactivateButton = findViewById(R.id.btnActivateDeactivate);

        // Fetch and display user data
        fetchUserData();

        // Set a click listener for the "Update" button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update user data here and make the API call
                updateUserData();
            }
        });


    }

    // Fetch user data and populate UI fields
    private void fetchUserData() {
        // Make an API call to get user data by NIC
        Call<UserProfile> call = apiService.getUserProfileByNIC("user_nic"); // Replace with the actual NIC
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserProfile userProfile = response.body();
                    // Populate the UI fields with user data
                    nameEditText.setText(userProfile.getName());
                    nicEditText.setText(userProfile.getNIC());
                    mailEditText.setText(userProfile.getMail());
                    phoneEditText.setText(userProfile.getPhone());
                    dateEditText.setText(userProfile.getDate());
                } else {
                    // Handle API error
                    Toast.makeText(UserProfileActivity.this, "Failed to fetch user data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                // Handle API call failure
                Toast.makeText(UserProfileActivity.this, "API call failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Update user data
    private void updateUserData() {
        // Get updated user data from UI fields
        String updatedName = nameEditText.getText().toString();
        String updatedNIC = nicEditText.getText().toString();
        String updatedMail = mailEditText.getText().toString();
        String updatedPhone = phoneEditText.getText().toString();
        String updatedDate = dateEditText.getText().toString();

        // Create a UserProfile object with updated data
        UserProfile updatedProfile = new UserProfile(updatedName, updatedNIC, updatedMail, updatedPhone, updatedDate);

        // Make an API call to update user data
        Call<Void> call = apiService.updateUserProfileByNIC(updatedNIC, updatedProfile);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // User data updated successfully
                    Toast.makeText(UserProfileActivity.this, "User data updated.", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle API error
                    Toast.makeText(UserProfileActivity.this, "Failed to update user data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle API call failure
                Toast.makeText(UserProfileActivity.this, "API call failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}