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

public class DriverActivity extends AppCompatActivity {
    private UserPreferences userPreferences;
    private MaterialButton btnProfileDriver, btnRiwayatTransaksi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        userPreferences = new UserPreferences(DriverActivity.this);
        btnProfileDriver = findViewById(R.id.btnProfileDriver);
        btnRiwayatTransaksi = findViewById(R.id.btnRiwayatTransaksi);
        btnProfileDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( DriverActivity.this, ProfileDriverActivity.class));
            }
        });
        btnRiwayatTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( DriverActivity.this, RiwayatTransaksiDriverActivity.class));
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

                            Intent logOutintent = new Intent(DriverActivity.this, LoginActivity.class);
                            logOutintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(logOutintent);
                        }
                    })
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}