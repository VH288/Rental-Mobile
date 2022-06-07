package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
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
import com.vincenthandoko.rental.models.Aset;
import com.vincenthandoko.rental.models.Biaya;
import com.vincenthandoko.rental.models.Customer;
import com.vincenthandoko.rental.models.Driver;
import com.vincenthandoko.rental.models.Transaksi;
import com.vincenthandoko.rental.models.TransaksiResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataRiwayatDriverActivity extends AppCompatActivity {
    private UserPreferences userPreferences;
    private RequestQueue queue;
    private TextView txtNamaMobil, txtNomorPlat,
            txtTglPemesanan,txtTglMulai,txtTglSelsai,
            txtDurasiDriver,txtDurasiMobil,txtTotal,
            txtStatus, txtNamaCustomer;
    private Transaksi transaksi;
    private Aset aset;
    private Biaya biaya;
    private Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_riwayat_driver);
        queue = Volley.newRequestQueue(this);
        txtNamaMobil = findViewById(R.id.txtNamaMobil);
        txtNomorPlat = findViewById(R.id.txtNomorPlat);
        txtTglPemesanan = findViewById(R.id.txtTglPemesanan);
        txtTglMulai = findViewById(R.id.txtTglMulai);
        txtTglSelsai = findViewById(R.id.txtTglSelsai);
        txtDurasiDriver = findViewById(R.id.txtDurasiDriver);
        txtDurasiMobil = findViewById(R.id.txtDurasiMobil);
        txtTotal = findViewById(R.id.txtTotal);
        txtStatus = findViewById(R.id.txtStatus);
        txtNamaCustomer = findViewById(R.id.txtNamaCustomer);
        getDataTransaksi();
    }

    private void getDataTransaksi() {
        String id = getIntent().getStringExtra("id");
        StringRequest stringRequest = new StringRequest(GET, RentalApi.TAMPILDATARIWAYATDRIVER + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TransaksiResponse transaksiResponse = gson.fromJson(response, TransaksiResponse.class);
                transaksi = transaksiResponse.getTransaksi();
                biaya = transaksiResponse.getBiaya();
                aset = transaksiResponse.getAset();
                customer = transaksiResponse.getCustomer();
                if (Objects.isNull(biaya)){
                    Toast.makeText(DataRiwayatDriverActivity.this,"Tidak ada transaksi ditemukan",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    txtNamaMobil.setText(aset.getNama());
                    txtNomorPlat.setText(aset.getNoplat());
                    txtTglPemesanan.setText(transaksi.getTglpemesanan());
                    txtTglMulai.setText(transaksi.getTglmulai());
                    txtTglSelsai.setText(transaksi.getTglselesai());
                    txtDurasiDriver.setText(String.valueOf(biaya.getDurasiDriver()));
                    txtDurasiMobil.setText(String.valueOf(biaya.getDurasiMobil()));
                    txtTotal.setText(String.valueOf(biaya.getTotal()));
                    txtNamaCustomer.setText(customer.getNama());
                }

                Toast.makeText(DataRiwayatDriverActivity.this, transaksiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DataRiwayatDriverActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(DataRiwayatDriverActivity.this, e.getMessage(),
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