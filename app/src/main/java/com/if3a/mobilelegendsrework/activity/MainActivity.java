package com.if3a.mobilelegendsrework.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.if3a.mobilelegendsrework.R;

public class MainActivity extends AppCompatActivity {
    private Button btnHeroes, btnRoles, btnSpeciallities;
    private ActionBar judulBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHeroes = findViewById(R.id.btn_heroes);
        btnHeroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HeroActivity.class));
            }
        });

        btnRoles = findViewById(R.id.btn_role);
        btnRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RoleActivity.class));
            }
        });

        btnSpeciallities = findViewById(R.id.btn_specially);
        btnSpeciallities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Speciallities.class));
            }
        });
    }
}