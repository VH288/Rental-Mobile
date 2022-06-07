package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.vincenthandoko.rental.Preferences.UserPreferences;
import com.vincenthandoko.rental.api.RentalApi;
import com.vincenthandoko.rental.models.Customer;
import com.vincenthandoko.rental.models.CustomerResponse;
import com.vincenthandoko.rental.models.Driver;
import com.vincenthandoko.rental.models.DriverResponse;
import com.vincenthandoko.rental.models.MessageResponse;
import com.vincenthandoko.rental.models.User;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ProfileDriverActivity extends AppCompatActivity {
    private UserPreferences userPreferences;
    private TextView txtDriverName, txtEmail, txtTgllahir, txtNotelp, txtGender,
    txtBahasa, txtHarga,txtAvgrating, txtAvailable;
    private MaterialButton btnUbahStatus, btnRating, btnEditProfile;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_driver);
        userPreferences = new UserPreferences(this);
        queue = Volley.newRequestQueue(this);
        txtDriverName = findViewById(R.id.txtDriverName);
        txtEmail = findViewById(R.id.txtEmail);
        txtTgllahir = findViewById(R.id.txtTgllahir);
        txtGender = findViewById(R.id.txtGender);
        txtNotelp = findViewById(R.id.txtNotelp);
        txtBahasa = findViewById(R.id.txtBahasa);
        txtHarga = findViewById(R.id.txtHarga);
        txtAvgrating = findViewById(R.id.txtAvgrating);
        txtAvailable = findViewById(R.id.txtAvailable);
        btnUbahStatus = findViewById(R.id.btnUbahStatus);
        btnRating = findViewById(R.id.btnRating);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        getUserProfile();
        btnUbahStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatus();
                getUserProfile();
            }
        });
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( ProfileDriverActivity.this, ListRatingActivity.class));
            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( ProfileDriverActivity.this, EditProfileDriverActivity.class));
                getUserProfile();
            }
        });
    }

    private void getUserProfile() {
        String id = userPreferences.getUserId();
        StringRequest stringRequest = new StringRequest(GET, RentalApi.PROFILEDRIVER + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                DriverResponse driverResponse = gson.fromJson(response,DriverResponse.class);
                Driver driver = driverResponse.getDriver();
                Toast.makeText(ProfileDriverActivity.this, driverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(driver != null){
                    txtDriverName.setText(driver.getNama());
                    txtEmail.setText(driver.getEmail());
                    txtGender.setText(driver.getGender());
                    txtNotelp.setText(driver.getNotelp());
                    txtTgllahir.setText(driver.getTgllahir());
                    txtBahasa.setText(driver.getBahasa());
                    txtHarga.setText(String.valueOf(driver.getHarga()));
                    txtAvgrating.setText(String.valueOf(driver.getAvgrating()));
                    if(driver.isAvailable()==1){
                        txtAvailable.setText("Available");
                    }
                    else{
                        txtAvailable.setText("Unavailable");
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(ProfileDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(ProfileDriverActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }
    public void updateStatus(){
        String id = userPreferences.getUserId();
        StringRequest stringRequest = new StringRequest(GET, RentalApi.UPDATESTATUS + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                MessageResponse messageResponse = gson.fromJson(response,MessageResponse.class);
                Toast.makeText(ProfileDriverActivity.this, messageResponse.getMessage(), Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(ProfileDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(ProfileDriverActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }
}