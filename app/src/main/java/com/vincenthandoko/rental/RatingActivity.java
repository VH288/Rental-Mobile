package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
import com.vincenthandoko.rental.api.RentalApi;
import com.vincenthandoko.rental.models.MessageResponse;
import com.vincenthandoko.rental.models.Rating;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RatingActivity extends AppCompatActivity {
    private TextInputLayout etRating, etComment;
    private TextView txtNamaDriver;
    private MaterialButton btnSubmit;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        queue = Volley.newRequestQueue(this);
        etRating = findViewById(R.id.etRate);
        etComment = findViewById(R.id.etComment);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtNamaDriver = findViewById(R.id.txtNamaDriver);
        String namaDriver = getIntent().getStringExtra("nama");
        txtNamaDriver.setText(namaDriver);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rate = etRating.getEditText().getText().toString();
                String comment = etComment.getEditText().getText().toString();
                if(validateForm(rate,comment)){
                    tambahRating();
                }
            }
        });
    }
    private boolean validateForm(String rate, String comment){
        if(rate.isEmpty() || comment.isEmpty()){
            Toast.makeText(RatingActivity.this, "Rating dan Comment Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }else if(Integer.valueOf(rate) < 0 || Integer.valueOf(rate) > 5){
            Toast.makeText(RatingActivity.this, "Rating tidak boleh lebih dari 5 dan kecil dari 0", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void tambahRating(){
        String id = getIntent().getStringExtra("id");
        int rate = Integer.valueOf(etRating.getEditText().getText().toString());
        String comment = etComment.getEditText().getText().toString();
        Rating rating = new Rating(rate,comment);
        StringRequest stringRequest = new StringRequest(POST, RentalApi.RATING + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                MessageResponse messageResponse = gson.fromJson(response,MessageResponse.class);
                Toast.makeText(RatingActivity.this, messageResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(RatingActivity.this,
                            errors.getString("message"), Toast.LENGTH_LONG).show();
                    finish();
                } catch (Exception e){
                    Toast.makeText(RatingActivity.this, e.getMessage(),
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
                String requestBody = gson.toJson(rating);

                return requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queue.add(stringRequest);
    }
}