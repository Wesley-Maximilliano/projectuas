package com.if3a.mobilelegendsrework.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.if3a.mobilelegendsrework.R;
import com.if3a.mobilelegendsrework.adapter.HeroViewAdapter;
import com.if3a.mobilelegendsrework.adapter.RoleViewAdapter;
import com.if3a.mobilelegendsrework.api.APIRequestData;
import com.if3a.mobilelegendsrework.api.APIRequestData2;
import com.if3a.mobilelegendsrework.api.RetroServer;
import com.if3a.mobilelegendsrework.api.RetroServer2;
import com.if3a.mobilelegendsrework.model.hero.Hero;
import com.if3a.mobilelegendsrework.model.hero.Root;
import com.if3a.mobilelegendsrework.model.role.Role;
import com.if3a.mobilelegendsrework.model.role.RootRole;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoleActivity extends AppCompatActivity {
    private RecyclerView rvRole;
    private List<Role> listRole;
    private ProgressBar pbUser;
    private RoleViewAdapter adapterRole;
    private LinearLayoutManager linearLayoutManager;
    private ImageButton btnInfo, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        //setting button
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoleActivity.this, MainActivity.class));
            }
        });
        btnInfo = findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoleActivity.this, ProfileActivity.class));
            }
        });

        rvRole= findViewById(R.id.rv_role);
        pbUser = findViewById(R.id.pb_role);

        linearLayoutManager = new LinearLayoutManager(RoleActivity.this);
        rvRole.setLayoutManager(linearLayoutManager);

        retriveUser();
    }


    private void retriveUser() {
        pbUser.setVisibility(View.VISIBLE);

        APIRequestData2 ardData = RetroServer2.getRetrofit().create(APIRequestData2.class);
        Call<RootRole> retrieveProcess = ardData.getRole();
        retrieveProcess.enqueue(new Callback<RootRole>() {
            @Override
            public void onResponse(Call<RootRole> call, Response<RootRole> response) {
                listRole = response.body().getRole();
                adapterRole = new RoleViewAdapter(listRole);
                rvRole.setAdapter(adapterRole);
                pbUser.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<RootRole> call, Throwable t) {
                Toast.makeText(RoleActivity.this, "Gagal Menghubungi Server : " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                pbUser.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retriveUser();
    }
}