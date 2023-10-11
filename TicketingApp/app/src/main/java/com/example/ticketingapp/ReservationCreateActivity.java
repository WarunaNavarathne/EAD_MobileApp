package com.example.ticketingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.Nullable;
import android.widget.Toast;

import com.example.ticketingapp.models.Reservation;
import com.example.ticketingapp.models.RouteInfo;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_create);

        // Simulated JSON response data
        String jsonResponse = "{\"isSuccess\": true, \"message\": \"Data Fetch Successfully\", \"data\": [{\"id\": \"652416775c17263f03e5763a\",\"generateID\": \"2724\",\"startPlace\": \"Colombo\",\"destination\": \"Kandy\",\"startTime\": \"12:00\",\"arriveTime\": \"1:00\",\"price\": \"100\",\"noOfSeats\": 10}]}";

        try {
            JSONObject response = new JSONObject(jsonResponse);
            JSONArray dataArray = response.getJSONArray("data");

            List<RouteInfo> routeInfoList = new ArrayList<>();

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject item = dataArray.getJSONObject(i);
                String startTime = item.getString("startTime");
                String arriveTime = item.getString("arriveTime");
                String startPlace = item.getString("startPlace");
                String destination = item.getString("destination");
                String price = item.getString("price");

                RouteInfo routeInfo = new RouteInfo(startTime, arriveTime, startPlace, destination, price);
                routeInfoList.add(routeInfo);
            }

            // Create the Spinner and set a custom adapter
            Spinner routeSpinner = findViewById(R.id.routeSpinner);
            CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(routeInfoList);
            routeSpinner.setAdapter(spinnerAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class CustomSpinnerAdapter extends ArrayAdapter<RouteInfo> {
        private List<RouteInfo> items;

        public CustomSpinnerAdapter(List<RouteInfo> items) {
            super(ReservationCreateActivity.this, R.layout.spinner_item, items);
            this.items = items;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.spinner_item, parent, false);
            }

            RouteInfo item = items.get(position);
            if (item != null) {
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setText(item.getFormattedText());
            }

            return view;
        }
    }
}