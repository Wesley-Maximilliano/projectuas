package com.if3a.mobilelegendsrework.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import com.if3a.mobilelegendsrework.api.APIRequestData;
import com.if3a.mobilelegendsrework.api.RetroServer;
import com.if3a.mobilelegendsrework.model.hero.Hero;
import com.if3a.mobilelegendsrework.model.hero.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroActivity extends AppCompatActivity {
    private RecyclerView rvUser;
    private ProgressBar pbUser;
    private List<Hero> listHero;
    private HeroViewAdapter adapterUser;
    private LinearLayoutManager linearLayoutManager;
    private SearchView svHero;
    private ImageButton btnBack, btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        svHero = findViewById(R.id.sv_hero);
        svHero.clearFocus();
        svHero.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        //setting button
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HeroActivity.this, MainActivity.class));
            }
        });
        btnInfo = findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HeroActivity.this, ProfileActivity.class));
            }
        });


        rvUser = findViewById(R.id.rv_user);
        pbUser = findViewById(R.id.pb_user);

        linearLayoutManager = new LinearLayoutManager(HeroActivity.this);
        rvUser.setLayoutManager(linearLayoutManager);

        retriveUser();

    }

    private void filterList(String newText) {
        List<Hero> filteredList = new ArrayList<>();
        for (Hero hero : listHero){
            if(hero.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(hero);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(this, "Hero not found", Toast.LENGTH_SHORT).show();
        }
        else{
            adapterUser.setFilteredList(filteredList);
        }
    }

    private void retriveUser() {
        pbUser.setVisibility(View.VISIBLE);

        APIRequestData ardData = RetroServer.getRetrofit().create(APIRequestData.class);
        Call<Root> retrieveProcess = ardData.getHero();
        retrieveProcess.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                listHero = response.body().getHero();
                adapterUser = new HeroViewAdapter(HeroActivity.this, listHero);
                rvUser.setAdapter(adapterUser);
                pbUser.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(HeroActivity.this, "Gagal Menghubungi Server : " + t.getMessage(),
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