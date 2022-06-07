package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.vincenthandoko.rental.api.RentalApi;
import com.vincenthandoko.rental.laporan.Laporan4;
import com.vincenthandoko.rental.laporan.Laporan4Response;
import com.vincenthandoko.rental.laporan.Laporan5;
import com.vincenthandoko.rental.laporan.Laporan5Response;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class Laporan5Activity extends AppCompatActivity {

    private TableView tableView;
    private Button btnSearch;
    private TextInputLayout etBulan, etTahun;
    private List<Laporan5> data;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan5);

        queue = Volley.newRequestQueue(this);

        tableView = findViewById(R.id.table_laporan5);

        btnSearch = findViewById(R.id.btnSearch);

        etBulan = findViewById(R.id.etBulan);
        etTahun = findViewById(R.id.etTahun);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm(etBulan.getEditText().getText().toString(),etTahun.getEditText().getText().toString())){
                    String bulan;
                    int angka = Integer.valueOf(etBulan.getEditText().getText().toString());
                    if(angka< 10){
                        bulan = "0" + String.valueOf(angka);
                    }else{
                        bulan = etBulan.getEditText().getText().toString();
                    }
                    String tanggal = etTahun.getEditText().getText().toString() + "-" + bulan + "-01";
                    getLaporan5(tanggal);
                    if(data != null){
                        setTableAdapter();
                    }
                }
            }
        });
    }

    private void getLaporan5(String tanggal){
        StringRequest stringRequest = new StringRequest(GET, RentalApi.LAPORAN5 + tanggal, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Laporan5Response laporan5Response = gson.fromJson(response, Laporan5Response.class);
                data = laporan5Response.getData();
                Toast.makeText(Laporan5Activity.this, laporan5Response.getMessage(), Toast.LENGTH_SHORT);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(Laporan5Activity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(Laporan5Activity.this, e.getMessage(),
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
    private boolean validateForm(String bulan, String tahun){
        if (bulan.isEmpty() || bulan.isEmpty()){
            Toast.makeText(Laporan5Activity.this, "Tahun atau Bulan Kosong", Toast.LENGTH_SHORT).show();
            return false;
        } else if( Integer.valueOf(bulan) > 12 || Integer.valueOf(bulan) < 1){
            Toast.makeText(Laporan5Activity.this, "Bulan harus di antara 1 - 12", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void setTableAdapter(){
        String[] header = {"Nama","Jumlah Transaksi"};
        int x = data.size();
        String[][] body = new String[x][2];
        for(int i = 0; i < x; i++){
            body[i][0] = data.get(i).getNamaCustomer();
            body[i][1] = String.valueOf(data.get(i).getJumlahTransaksi());
        }
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this,header));
        tableView.setDataAdapter(new SimpleTableDataAdapter(this,body));
    }
}