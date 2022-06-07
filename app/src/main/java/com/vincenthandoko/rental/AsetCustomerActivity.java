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
import com.vincenthandoko.rental.adapters.AsetAdapter;
import com.vincenthandoko.rental.adapters.MobilAdapter;
import com.vincenthandoko.rental.api.RentalApi;
import com.vincenthandoko.rental.models.AsetResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AsetCustomerActivity extends AppCompatActivity {
    private AsetAdapter adapter;
    private SwipeRefreshLayout srMobil;
    private RequestQueue queue;
    private RecyclerView rvAset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aset_customer);
        queue = Volley.newRequestQueue(this);
        srMobil = findViewById(R.id.sr_mobil);
        srMobil.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllMobil();
            }
        });
        rvAset = findViewById(R.id.rv_aset);
        adapter = new AsetAdapter(new ArrayList<>(), this);
        rvAset.setLayoutManager(new LinearLayoutManager(AsetCustomerActivity.this, LinearLayoutManager.VERTICAL,false));
        rvAset.setAdapter(adapter);
        getAllMobil();
    }

    private void getAllMobil() {
        UserPreferences userPreferences = new UserPreferences(this);
        String id = userPreferences.getUserId();
        srMobil.setRefreshing(true);
        StringRequest stringRequest = new StringRequest(GET, RentalApi.TAMPILASETCUSTOMER+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                AsetResponse asetResponse = gson.fromJson(response,AsetResponse.class);
                adapter.setAsetList(asetResponse.getAsetList());
                rvAset.setAdapter(adapter);
                Toast.makeText(AsetCustomerActivity.this, asetResponse.getMessage(), Toast.LENGTH_SHORT).show();
                srMobil.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                srMobil.setRefreshing(false);
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(AsetCustomerActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(AsetCustomerActivity.this, e.getMessage(),
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