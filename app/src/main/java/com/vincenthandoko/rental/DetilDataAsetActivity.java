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
import com.vincenthandoko.rental.models.Aset;
import com.vincenthandoko.rental.models.AsetDetilResponse;
import com.vincenthandoko.rental.models.AsetResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DetilDataAsetActivity extends AppCompatActivity {
    private TextView txtNama, txtTipe, txtTransmisi, txtBbm, txtVolume
            , txtWarna, txtKapasitas, txtFitur, txtNoplat, txtNostnk
            , txtKategori, txtPeriodeMulai, txtPeriodeSelesai, txtTglServis
            , txtHarga, txtAvailable;
    private UserPreferences userPreferences;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_data_aset);

        queue = Volley.newRequestQueue(this);
        txtNama = findViewById(R.id.txtNama);
        txtTipe = findViewById(R.id.txtTipe);
        txtTransmisi = findViewById(R.id.txtTransmisi);
        txtBbm = findViewById(R.id.txtBbm);
        txtVolume = findViewById(R.id.txtVolume);
        txtWarna = findViewById(R.id.txtWarna);
        txtKapasitas = findViewById(R.id.txtKapasitas);
        txtFitur = findViewById(R.id.txtFitur);
        txtNoplat = findViewById(R.id.txtNoplat);
        txtNostnk = findViewById(R.id.txtNostnk);
        txtKategori = findViewById(R.id.txtKategori);
        txtPeriodeMulai = findViewById(R.id.txtPeriodeMulai);
        txtPeriodeSelesai = findViewById(R.id.txtPeriodeSelesai);
        txtTglServis = findViewById(R.id.txtTglservis);
        txtHarga = findViewById(R.id.txtHarga);
        txtAvailable = findViewById(R.id.txtAvailable);
        getDataAset();
    }

    private void getDataAset() {
        String id = getIntent().getStringExtra("id");
        StringRequest stringRequest = new StringRequest(GET, RentalApi.TAMPILDETILASET + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                AsetDetilResponse asetResponse = gson.fromJson(response,AsetDetilResponse.class);
                Aset aset = asetResponse.getAset();
                Toast.makeText(DetilDataAsetActivity.this, asetResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if (aset != null){
                    txtNama.setText(aset.getNama());
                    txtTipe.setText(aset.getTipe());
                    txtTransmisi.setText(aset.getTransmisi());
                    txtBbm.setText(aset.getBbm());
                    txtVolume.setText(aset.getVolume());
                    txtWarna.setText(aset.getWarna());
                    txtKapasitas.setText(aset.getKapasitas());
                    String fitur = "";
                    if(aset.isFiturAc() == 1){
                        fitur = fitur + "Ac, ";
                    }
                    if(aset.isFiturAirbag() == 1){
                        fitur = fitur + "Air Bag, ";
                    }
                    if(aset.isFiturMultimedia() == 1){
                        fitur = fitur + "Multi Media, ";
                    }
                    txtFitur.setText(fitur);
                    txtNoplat.setText(aset.getNoplat());
                    txtNostnk.setText(aset.getNostnk());
                    txtKategori.setText(aset.getKategori());
                    txtPeriodeMulai.setText(aset.getPeriodeMulai());
                    txtPeriodeSelesai.setText(aset.getPeriodeSelesai());
                    txtTglServis.setText(aset.getTglservis());
                    txtHarga.setText(String.valueOf(aset.getHarga()));
                    if(aset.isAvailable() == 1){
                        txtAvailable.setText("Tersedia");
                    }
                    else{
                        txtAvailable.setText("Tidak Tersedia");
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
                    Toast.makeText(DetilDataAsetActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(DetilDataAsetActivity.this, e.getMessage(),
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