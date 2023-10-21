package com.example.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticketingapp.models.Traveler;
import com.example.ticketingapp.models.UserProfile;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfileActivity extends AppCompatActivity {
    private UserResponse fetchedUserResponse;
    private ApiService apiService;
    private EditText nameEditText;
    private EditText nicEditText;
    private EditText mailEditText;
    private EditText phoneEditText;
    private EditText dateEditText;
    private EditText passwordEditText;  // Define passwordEditText
    private EditText isactiveEditText;  // Define isactiveEditText
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_user_profile);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eadpvtltd.azurewebsites.net/") // Replace with your actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        nameEditText = findViewById(R.id.nameProfile);
        nicEditText = findViewById(R.id.nicProfile);
        mailEditText = findViewById(R.id.mailProfile);
        phoneEditText = findViewById(R.id.phoneProfile);
        dateEditText = findViewById(R.id.dateProfile);
//        passwordEditText = findViewById(R.id.passwordProfile);  // Initialize passwordEditText
//        isactiveEditText = findViewById(R.id.isActiveProfile);  // Initialize isactiveEditText
        updateButton = findViewById(R.id.updateButton);

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

    private void fetchUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        String nic = sharedPreferences.getString("nic", "default_value_if_not_found");

        Call<UserResponse> call = apiService.getUserProfileByNIC(nic);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    fetchedUserResponse = response.body();
                    updateUIWithUserData(fetchedUserResponse);
                } else {
                    handleApiError(response);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("API Call Error", t.getMessage());
                Toast.makeText(UserProfileActivity.this, "API call failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUIWithUserData(UserResponse userProfile) {
        if (userProfile != null) {
            nameEditText.setText(userProfile.getData().getName());
            nicEditText.setText(userProfile.getData().getNic());
            mailEditText.setText(userProfile.getData().getMail());
            phoneEditText.setText(userProfile.getData().getPhoneNo());
            dateEditText.setText(userProfile.getData().getJoinDate());
//            passwordEditText.setText(userProfile.getData().getPassword());
//            isactiveEditText.setText(userProfile.getData().isActive());
        } else {
            Log.d("UserProfile", "Response body is null.");
        }
    }

    private void updateUserData() {
        if (fetchedUserResponse != null) {
            String updatedId = fetchedUserResponse.getData().getId();
            String updatedPassword = fetchedUserResponse.getData().getPassword();
            boolean updatedIsActive = fetchedUserResponse.getData().isActive();
            Log.d("UserProfile", updatedId);
            Log.d("UserProfile", updatedPassword);
            Log.d("UserProfile", String.valueOf(updatedIsActive));

            // Get updated data from UI fields
            String updatedName = nameEditText.getText().toString();
            String updatedNIC = nicEditText.getText().toString();
            String updatedMail = mailEditText.getText().toString();
            String updatedPhoneNo = phoneEditText.getText().toString();
            String updatedJoinDate = dateEditText.getText().toString();

            // Create a UserProfile object with updated data
            UserProfile updatedProfile = new UserProfile(updatedId, updatedName, updatedNIC, updatedMail, updatedPhoneNo, updatedPassword, updatedJoinDate, updatedIsActive);

            Call<UserResponse> call = apiService.updateUserProfileByNIC(updatedProfile);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        UserResponse updatedUserResponse = response.body();
                        Toast.makeText(UserProfileActivity.this, "User data updated.", Toast.LENGTH_SHORT).show();
                    } else {
                        handleApiError(response);
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.e("API Call Error", t.getMessage());
                    Toast.makeText(UserProfileActivity.this, "API call failed.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(UserProfileActivity.this, "No user data has been fetched.", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleApiError(Response<UserResponse> response) {
        if (response != null && response.errorBody() != null) {
            try {
                Log.e("API Error", response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(UserProfileActivity.this, "API call failed.", Toast.LENGTH_SHORT).show();
    }
}