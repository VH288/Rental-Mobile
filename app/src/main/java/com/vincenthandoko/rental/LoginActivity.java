package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.vincenthandoko.rental.Preferences.UserPreferences;
import com.vincenthandoko.rental.api.RentalApi;
import com.vincenthandoko.rental.models.LoginResponse;
import com.vincenthandoko.rental.models.User;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout etEmail, etPassword;
    private MaterialButton btnLogin;
    private UserPreferences userPreferences;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        queue= Volley.newRequestQueue(this);
        userPreferences = new UserPreferences(LoginActivity.this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);

        checkLogin();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inEmail = etEmail.getEditText().getText().toString();
                String inPassword = etPassword.getEditText().getText().toString();

                if(validateForm(inEmail,inPassword)){
                    loginUser();
                    checkLogin();
                }

            }
        });

    }
    public void loginUser(){
        final String email = etEmail.getEditText().getText().toString();
        final String password = etPassword.getEditText().getText().toString();
        User user = new User(email,password);
        StringRequest stringRequest = new StringRequest(POST, RentalApi.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
                        String userId = loginResponse.getUserId();
                        String role = loginResponse.getRole();
                        userPreferences.setLogin(userId, role);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(LoginActivity.this,
                            errors.getString("message"), Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    Toast.makeText(LoginActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }){
            //Header
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }

            //Body
            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                /* Serialisasi data dari java object
                menjadi JSON string menggunakan Gson */
                String requestBody = gson.toJson(user);

                return requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        queue.add(stringRequest);
    }
    public void checkLogin(){
        if(userPreferences.checkLogin()){
            if (userPreferences.getRole().equals("Manager")){
                startActivity(new Intent(LoginActivity.this, ManagerActivity.class));
                finish();
            }else if(userPreferences.getRole().equals("Customer")){
                startActivity(new Intent(LoginActivity.this, CustomerActivity.class));
                finish();
            }else{
                startActivity(new Intent(LoginActivity.this, DriverActivity.class));
                finish();
            }

        }
    }
    private boolean validateForm(String email, String password){
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Email atau Password Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}