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
import com.if3a.mobilelegendsrework.adapter.SpeciallyViewAdapter;
import com.if3a.mobilelegendsrework.api.APIRequestData;
import com.if3a.mobilelegendsrework.api.APIRequestData2;
import com.if3a.mobilelegendsrework.api.RetroServer;
import com.if3a.mobilelegendsrework.api.RetroServer2;
import com.if3a.mobilelegendsrework.model.hero.Hero;
import com.if3a.mobilelegendsrework.model.hero.Root;
import com.if3a.mobilelegendsrework.model.specially.RootSpecially;
import com.if3a.mobilelegendsrework.model.specially.Specially;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Speciallities extends AppCompatActivity {
    private RecyclerView rvUser;
    private ProgressBar pbUser;
    private List<Specially> listSpecially;
    private SpeciallyViewAdapter adapterUser;
    private LinearLayoutManager linearLayoutManager;
    private ImageButton btnInfo, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciallities);

        //setting button
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Speciallities.this, MainActivity.class));
            }
        });
        btnInfo = findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Speciallities.this, ProfileActivity.class));
            }
        });

        rvUser = findViewById(R.id.rv_specially);
        pbUser = findViewById(R.id.pb_specially);

        linearLayoutManager = new LinearLayoutManager(Speciallities.this);
        rvUser.setLayoutManager(linearLayoutManager);

        retriveUser();
    }

    private void retriveUser() {
        pbUser.setVisibility(View.VISIBLE);

        APIRequestData2 ardData = RetroServer2.getRetrofit().create(APIRequestData2.class);
        Call<RootSpecially> retrieveProcess = ardData.getSpecially();
        retrieveProcess.enqueue(new Callback<RootSpecially>() {
            @Override
            public void onResponse(Call<RootSpecially> call, Response<RootSpecially> response) {
                listSpecially = response.body().getSpecially();
                adapterUser = new SpeciallyViewAdapter(listSpecially);
                rvUser.setAdapter(adapterUser);
                pbUser.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<RootSpecially> call, Throwable t) {
                Toast.makeText(Speciallities.this, "Gagal Menghubungi Server : " + t.getMessage(),
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