package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.vincenthandoko.rental.Preferences.UserPreferences;
import com.vincenthandoko.rental.adapters.RatingAdapter;
import com.vincenthandoko.rental.api.RentalApi;
import com.vincenthandoko.rental.models.RatingResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListRatingActivity extends AppCompatActivity {
    private RatingAdapter adapter;
    private SwipeRefreshLayout srRating;
    private RequestQueue queue;
    private RecyclerView rvRating;
    private UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rating);
        userPreferences = new UserPreferences(this);
        queue = Volley.newRequestQueue(this);
        srRating = findViewById(R.id.sr_rating);
        srRating.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllRating();
            }
        });
        rvRating = findViewById(R.id.rv_rating);
        adapter = new RatingAdapter(new ArrayList<>(),this);
        rvRating.setLayoutManager(new LinearLayoutManager(ListRatingActivity.this,LinearLayoutManager.VERTICAL,false));
        rvRating.setAdapter(adapter);
        getAllRating();
    }

    private void getAllRating() {
        srRating.setRefreshing(true);
        String id = userPreferences.getUserId();
        StringRequest stringRequest = new StringRequest(GET, RentalApi.LISTRATING + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RatingResponse ratingResponse = gson.fromJson(response,RatingResponse.class);
                adapter.setRatingList(ratingResponse.getRatingList());
                rvRating.setAdapter(adapter);
                Toast.makeText(ListRatingActivity.this, ratingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                srRating.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                srRating.setRefreshing(false);
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(ListRatingActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(ListRatingActivity.this, e.getMessage(),
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