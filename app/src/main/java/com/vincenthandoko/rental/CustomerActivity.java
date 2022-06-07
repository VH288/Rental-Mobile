package com.vincenthandoko.rental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.vincenthandoko.rental.Preferences.UserPreferences;

public class CustomerActivity extends AppCompatActivity {
    private UserPreferences userPreferences;
    private MaterialButton btnTransaksiTerkini,btnRiwayatTransaksi,
    btnMobilCustomer, btnPromoCustomer, btnProfileCustomer,
    btnAsetCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        userPreferences = new UserPreferences(CustomerActivity.this);
        btnTransaksiTerkini = findViewById(R.id.btnTransaksiTerkini);
        btnRiwayatTransaksi = findViewById(R.id.btnRiwayatTransaksi);
        btnMobilCustomer = findViewById(R.id.btnMobilCustomer);
        btnAsetCustomer = findViewById(R.id.btnAsetCustomer);
        btnProfileCustomer = findViewById(R.id.btnProfileCustomer);
        btnPromoCustomer = findViewById(R.id.btnPromoCustomer);

        btnTransaksiTerkini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( CustomerActivity.this, TerkiniActivity.class));
            }
        });

        btnRiwayatTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( CustomerActivity.this, RiwayatTransaksiCustomerActivity.class));
            }
        });
        btnMobilCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( CustomerActivity.this, MobilCustomerActivity.class));
            }
        });
        btnPromoCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( CustomerActivity.this, PromoActivity.class));
            }
        });
        btnProfileCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( CustomerActivity.this, ProfileCustomerActivity.class));
            }
        });
        btnAsetCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( CustomerActivity.this, AsetCustomerActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_list_manager,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure want to exit?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            userPreferences.logout();

                            Intent logOutintent = new Intent(CustomerActivity.this, LoginActivity.class);
                            logOutintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(logOutintent);
                        }
                    })
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}