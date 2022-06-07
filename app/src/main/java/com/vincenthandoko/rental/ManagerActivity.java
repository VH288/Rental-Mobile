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

public class ManagerActivity extends AppCompatActivity {
    private UserPreferences userPreferences;
    private MaterialButton btLaporan1,btLaporan2,btLaporan3,btLaporan4,btLaporan5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        userPreferences = new UserPreferences(ManagerActivity.this);
        btLaporan1 = findViewById(R.id.btnLaporan1);
        btLaporan2 = findViewById(R.id.btnLaporan2);
        btLaporan3 = findViewById(R.id.btnLaporan3);
        btLaporan4 = findViewById(R.id.btnLaporan4);
        btLaporan5 = findViewById(R.id.btnLaporan5);

        btLaporan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerActivity.this, Laporan1Activity.class));
            }
        });
        btLaporan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerActivity.this, Laporans2Activity.class));
            }
        });
        btLaporan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerActivity.this, Laporan3Activity.class));
            }
        });
        btLaporan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerActivity.this, Laporan4Activity.class));
            }
        });
        btLaporan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerActivity.this, Laporan5Activity.class));
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

                            Intent logOutintent = new Intent(ManagerActivity.this, LoginActivity.class);
                            logOutintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(logOutintent);
                        }
                    })
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}