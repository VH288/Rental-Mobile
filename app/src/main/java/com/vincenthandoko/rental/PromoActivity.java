package com.vincenthandoko.rental;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.vincenthandoko.rental.api.RentalApi;
import com.vincenthandoko.rental.models.Promo;
import com.vincenthandoko.rental.models.PromoResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class PromoActivity extends AppCompatActivity {
    private TableView tableView;
    private List<Promo> data;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        queue = Volley.newRequestQueue(this);
        tableView = findViewById(R.id.table_promo);
        getPromo();

    }
    private void getPromo(){
        StringRequest stringRequest = new StringRequest(GET, RentalApi.TAMPILPROMOCUSTOMER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                PromoResponse promoResponse = gson.fromJson(response,PromoResponse.class);
                data = promoResponse.getPromoList();
                if(data!=null){
                    setTableAdapter();
                }
                Toast.makeText(PromoActivity.this, promoResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(PromoActivity.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(PromoActivity.this, e.getMessage(),
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
    private void setTableAdapter(){
        String[] header = {"Id","Jenis","Diskon"};
        int x = data.size();
        String[][] body = new String[x][3];
        for(int i = 0; i < x; i++){
            body[i][0] = data.get(i).getId();
            body[i][1] = data.get(i).getJenis();
            body[i][2] = String.valueOf(data.get(i).getDiskon()) + "%";

        }
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this,header));
        tableView.setDataAdapter(new SimpleTableDataAdapter(this,body));
    }
}