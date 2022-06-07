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
import com.vincenthandoko.rental.adapters.RiwayatCustomerAdapter;
import com.vincenthandoko.rental.api.RentalApi;
import com.vincenthandoko.rental.models.RiwayatTransaksi;
import com.vincenthandoko.rental.models.RiwayatTransaksiResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiwayatCustomerActivity extends AppCompatActivity {
    private SwipeRefreshLayout srRiwayatCustomer;
    private RiwayatCustomerAdapter adapter;
    private RequestQueue queue;
    private RecyclerView rvRiwayatCustomer;
    private UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_customer);
        userPreferences = new UserPreferences(this);
        queue = Volley.newRequestQueue(this);
        srRiwayatCustomer = findViewById(R.id.sr_riwayat_customer);
        rvRiwayatCustomer = findViewById(R.id.rv_riwayat_customer);
        adapter = new RiwayatCustomerAdapter(new ArrayList<>());
        rvRiwayatCustomer.setLayoutManager(new LinearLayoutManager(RiwayatCustomerActivity.this, LinearLayoutManager.VERTICAL, false));
        srRiwayatCustomer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllRiwayat();
            }
        });
        getAllRiwayat();
        rvRiwayatCustomer.setAdapter(adapter);
    }

    private void getAllRiwayat() {
        srRiwayatCustomer.setRefreshing(true);
        String id = userPreferences.getUserId();
        StringRequest stringRequest = new StringRequest(GET, RentalApi.TAMPILRIWAYATCUSTOMER+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RiwayatTransaksiResponse riwayatTransaksiResponse = gson.fromJson(response,RiwayatTransaksiResponse.class);
                adapter.setRiwayatTransaksiList(riwayatTransaksiResponse.getRiwayatTransaksiList());
                Toast.makeText(RiwayatCustomerActivity.this, riwayatTransaksiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                rvRiwayatCustomer.setAdapter(adapter);
                srRiwayatCustomer.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                srRiwayatCustomer.setRefreshing(false);

                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(RiwayatCustomerActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(RiwayatCustomerActivity.this, e.getMessage(),
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