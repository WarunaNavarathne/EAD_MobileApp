package com.example.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class ReservationCreateActivity extends AppCompatActivity {
    private TextInputLayout routeDropdownLayout;
    private AutoCompleteTextView routeDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_create);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Initialize Material UI-style drop-down
        routeDropdownLayout = findViewById(R.id.route_dropdown);
        routeDropdown = findViewById(R.id.dropdownField);

        // Hard-coded values for the drop-down
        String[] routes = {"Route 1", "Route 2", "Route 3", "Route 4", "Route 5"};

        // Create an ArrayAdapter to populate the drop-down with hard-coded values
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, routes);
        routeDropdown.setAdapter(adapter);

        // Handle drop-down item selection
        routeDropdown.setOnItemClickListener((parent, view, position, id) -> {
            String selectedRoute = parent.getItemAtPosition(position).toString();
            // Do something with the selected route
        });

    }
}