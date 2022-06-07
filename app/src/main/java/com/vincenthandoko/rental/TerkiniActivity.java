package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import com.vincenthandoko.rental.models.Transaksi;
import com.vincenthandoko.rental.models.TransaksiResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TerkiniActivity extends AppCompatActivity {
    private UserPreferences userPreferences;
    private RequestQueue queue;
    private TextView txtNamaMobil, txtNomorPlat,
    txtTglPemesanan,txtTglMulai,txtTglSelsai,
    txtDurasiDriver,txtDurasiMobil,txtTotal,
    txtStatus;
    private Button btnKembali, btnBayar;
    private Transaksi transaksi;
    private Aset aset;
    private Biaya biaya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terkini);
        userPreferences = new UserPreferences(TerkiniActivity.this);
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

        btnKembali = findViewById(R.id.btnKembali);
        btnBayar = findViewById(R.id.btnBayar);

        getTransaksiTerkini();

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePengembalian();

            }
        });

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePembayaran();

            }
        });
    }
    public void getTransaksiTerkini(){
        String id = userPreferences.getUserId();
        StringRequest stringRequest = new StringRequest(GET, RentalApi.TRANSAKSI_TERKINI + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TransaksiResponse transaksiResponse = gson.fromJson(response, TransaksiResponse.class);
                transaksi = transaksiResponse.getTransaksi();
                biaya = transaksiResponse.getBiaya();
                aset = transaksiResponse.getAset();

                if (Objects.isNull(biaya)){
                    Toast.makeText(TerkiniActivity.this,"Tidak ada transaksi penyewaan",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    if(transaksi.isVerified() == 0){
                        btnKembali.setEnabled(false);
                        btnBayar.setEnabled(false);
                        txtStatus.setText("Transaksi Penyewaan Belum Terverifikasi");
                    }else if(biaya.getTglpengembalian() == null){
                        btnKembali.setEnabled(true);
                        btnBayar.setEnabled(false);
                        txtStatus.setText("Mobil Belum Dikembalikan");
                    }else if(biaya.isPaid() == 0){
                        btnKembali.setEnabled(false);
                        btnBayar.setEnabled(true);
                        txtStatus.setText("Transaksi Belum Di bayar");
                    }else if(biaya.isVerified() == 0){
                        btnKembali.setEnabled(false);
                        btnBayar.setEnabled(false);
                        txtStatus.setText("Transaksi Pembayaran Belum Di Verifikasi");
                    }
                    txtNamaMobil.setText(aset.getNama());
                    txtNomorPlat.setText(aset.getNoplat());
                    txtTglPemesanan.setText(transaksi.getTglpemesanan());
                    txtTglMulai.setText(transaksi.getTglmulai());
                    txtTglSelsai.setText(transaksi.getTglselesai());
                    txtDurasiDriver.setText(String.valueOf(biaya.getDurasiDriver()));
                    txtDurasiMobil.setText(String.valueOf(biaya.getDurasiMobil()));
                    txtTotal.setText(String.valueOf(biaya.getTotal()));
                }

                Toast.makeText(TerkiniActivity.this, transaksiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(TerkiniActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(TerkiniActivity.this, e.getMessage(),
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
    public void updatePembayaran(){
        String id = biaya.getId();
        StringRequest stringRequest = new StringRequest(GET, RentalApi.PEMBAYARAN+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TransaksiResponse transaksiResponse = gson.fromJson(response, TransaksiResponse.class);
                Toast.makeText(TerkiniActivity.this, transaksiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                transaksi = transaksiResponse.getTransaksi();
                biaya = transaksiResponse.getBiaya();
                aset = transaksiResponse.getAset();
                if(biaya.isPaid() == 0){
                    btnKembali.setEnabled(false);
                    btnBayar.setEnabled(true);
                    txtStatus.setText("Transaksi Belum Di bayar");
                }else if(biaya.isVerified() == 0){
                    btnKembali.setEnabled(false);
                    btnBayar.setEnabled(false);
                    txtStatus.setText("Transaksi Pembayaran Belum Di Verifikasi");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(TerkiniActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(TerkiniActivity.this, e.getMessage(),
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
    public void updatePengembalian(){
        String id = biaya.getId();
        StringRequest stringRequest = new StringRequest(GET, RentalApi.PENGEMBALIAN+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TransaksiResponse transaksiResponse = gson.fromJson(response, TransaksiResponse.class);
                Toast.makeText(TerkiniActivity.this, transaksiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                transaksi = transaksiResponse.getTransaksi();
                biaya = transaksiResponse.getBiaya();
                aset = transaksiResponse.getAset();
                if(biaya.getTglpengembalian() == null){
                    btnKembali.setEnabled(true);
                    btnBayar.setEnabled(false);
                    txtStatus.setText("Mobil Belum Dikembalikan");
                }else if(biaya.isPaid() == 0){
                    btnKembali.setEnabled(false);
                    btnBayar.setEnabled(true);
                    txtStatus.setText("Transaksi Belum Di bayar");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(TerkiniActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(TerkiniActivity.this, e.getMessage(),
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