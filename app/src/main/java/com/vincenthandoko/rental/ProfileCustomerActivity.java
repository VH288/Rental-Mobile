package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.vincenthandoko.rental.Preferences.UserPreferences;
import com.vincenthandoko.rental.api.RentalApi;
import com.vincenthandoko.rental.models.Customer;
import com.vincenthandoko.rental.models.CustomerResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ProfileCustomerActivity extends AppCompatActivity {
    private UserPreferences userPreferences;
    private TextView txtCustomerName, txtEmail, txtTgllahir, txtNotelp, txtGender;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_customer);
        userPreferences = new UserPreferences(this);
        queue = Volley.newRequestQueue(this);
        txtCustomerName = findViewById(R.id.txtCustomerName);
        txtEmail = findViewById(R.id.txtEmail);
        txtTgllahir = findViewById(R.id.txtTgllahir);
        txtGender = findViewById(R.id.txtGender);
        txtNotelp = findViewById(R.id.txtNotelp);
        getUserProfile();
    }

    private void getUserProfile() {
        String id = userPreferences.getUserId();
        StringRequest stringRequest = new StringRequest(GET, RentalApi.PROFILECUSTOMER + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CustomerResponse customerResponse = gson.fromJson(response,CustomerResponse.class);
                Customer customer = customerResponse.getCustomer();
                Toast.makeText(ProfileCustomerActivity.this, customerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(customer != null){
                    txtCustomerName.setText(customer.getNama());
                    txtEmail.setText(customer.getEmail());
                    txtGender.setText(customer.getGender());
                    txtNotelp.setText(customer.getNotelp());
                    txtTgllahir.setText(customer.getTgllahir());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(ProfileCustomerActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(ProfileCustomerActivity.this, e.getMessage(),
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