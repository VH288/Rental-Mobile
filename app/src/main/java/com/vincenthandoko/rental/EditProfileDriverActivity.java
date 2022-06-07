package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import com.vincenthandoko.rental.models.Customer;
import com.vincenthandoko.rental.models.Driver;
import com.vincenthandoko.rental.models.DriverResponse;
import com.vincenthandoko.rental.models.MessageResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EditProfileDriverActivity extends AppCompatActivity {
    private TextInputLayout etNama, etAlamat, etTgllahir,etPassword, etNotelp;
    private MaterialButton btnBack, btnSave;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;
    private UserPreferences userPreferences;
    private Driver driver;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_driver);
        userPreferences = new UserPreferences(EditProfileDriverActivity.this);
        queue = Volley.newRequestQueue(this);
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etTgllahir = findViewById(R.id.etTgllahir);
        etPassword = findViewById(R.id.etPassword);
        etNotelp = findViewById(R.id.etNotelp);
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        getUserProfile();

        etTgllahir.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(EditProfileDriverActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);

                                driver.setTgllahir(dateFormat.format(newDate.getTime()));
                                etTgllahir.getEditText().setText(dateFormat.format(newDate.getTime()));
                            }
                        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    updateDriver();
                }
            }
        });
    }

    private void updateDriver() {
        String nama = etNama.getEditText().getText().toString();
        String alamat = etAlamat.getEditText().getText().toString();
        String notelp = etNotelp.getEditText().getText().toString();
        String tgllahir = etTgllahir.getEditText().getText().toString();
        String password = etPassword.getEditText().getText().toString();

        driver.setNama(nama);
        driver.setAlamat(alamat);
        driver.setNotelp(notelp);
        driver.setTgllahir(tgllahir);
        driver.setPassword(password);

        StringRequest stringRequest = new StringRequest(POST, RentalApi.UPDATEPROFILEDRIVER + driver.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                MessageResponse messageResponse = gson.fromJson(response,MessageResponse.class);
                Toast.makeText(EditProfileDriverActivity.this, messageResponse.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(EditProfileDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();

                } catch (Exception e){
                    Toast.makeText(EditProfileDriverActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            //Meanmbahkan header pada request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }

            //Menambahkan request body berupa object User
            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                /* Serialisasi data dari java object ProdukResponse
                menjadi JSON string menggunakan Gson */
                String requestBody = gson.toJson(driver);

                return requestBody.getBytes(StandardCharsets.UTF_8);
            }

            // Mendeklarasikan content type dari request body yang ditambahkan
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        queue.add(stringRequest);
    }

    private boolean validateForm() {
        String nama = etNama.getEditText().getText().toString();
        String alamat = etAlamat.getEditText().getText().toString();
        String notelp = etNotelp.getEditText().getText().toString();
        String tgllahir = etTgllahir.getEditText().getText().toString();
        String password = etPassword.getEditText().getText().toString();
        if (nama.isEmpty() || alamat.isEmpty() || notelp.isEmpty() || tgllahir.isEmpty() || password.isEmpty()){
            Toast.makeText(EditProfileDriverActivity.this, "Ada field yang Kosong!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getUserProfile() {
        String id = userPreferences.getUserId();
        StringRequest stringRequest = new StringRequest(GET, RentalApi.PROFILEDRIVER + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                DriverResponse driverResponse = gson.fromJson(response,DriverResponse.class);
                driver = driverResponse.getDriver();
                Toast.makeText(EditProfileDriverActivity.this, driverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(driver != null){
                    etNama.getEditText().setText(driver.getNama());
                    etAlamat.getEditText().setText(driver.getAlamat());
                    etTgllahir.getEditText().setText(driver.getTgllahir());
                    etPassword.getEditText().setText(driver.getPassword());
                    etNotelp.getEditText().setText(driver.getNotelp());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(EditProfileDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(EditProfileDriverActivity.this, e.getMessage(),
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