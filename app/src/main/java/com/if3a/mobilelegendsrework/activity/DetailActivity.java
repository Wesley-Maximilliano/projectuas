package com.if3a.mobilelegendsrework.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.if3a.mobilelegendsrework.R;
import com.if3a.mobilelegendsrework.adapter.BuildViewAdapter;
import com.if3a.mobilelegendsrework.adapter.SkillViewAdapter;
import com.if3a.mobilelegendsrework.api.APIRequestData;
import com.if3a.mobilelegendsrework.api.RetroServer;
import com.if3a.mobilelegendsrework.model.detail.Data;
import com.if3a.mobilelegendsrework.model.detail.Equip;
import com.if3a.mobilelegendsrework.model.detail.Gear;
import com.if3a.mobilelegendsrework.model.detail.OutPack;
import com.if3a.mobilelegendsrework.model.detail.RootDetail;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvMag, tvPhy, tvType, tvDiff, tvAlive, tvResult;
    private TextView tvBestPartner, tvTipsBestPartner, tvCounter, tvTipsCounter, tvCountered, tvTipsCountered;
    private ImageView ivBestPartner, ivCounter, ivCountered;
    private ImageView imageViewHero;

    private RecyclerView rvBuild, rvSkill;

    private Data listBuild;
    private Data listSkill;
    private RecyclerView.Adapter adapterSkill, adapterBuild;
    private LinearLayoutManager linearLayoutManager;

    private ImageButton btnInfo, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        tvNama = findViewById(R.id.tv_nama);
        imageViewHero = findViewById(R.id.iv_hero);

        tvBestPartner = findViewById(R.id.tv_bestpartner);
        tvTipsBestPartner = findViewById(R.id.tv_tipsbestpartner);
        tvCounter = findViewById(R.id.tv_counter);
        tvTipsCounter = findViewById(R.id.tv_tipscounter);
        tvCountered = findViewById(R.id.tv_countered);
        tvTipsCountered = findViewById(R.id.tv_tipscountered);
        ivBestPartner = findViewById(R.id.iv_bestpartner);
        ivCounter = findViewById(R.id.iv_counter);
        ivCountered = findViewById(R.id.iv_countered);

        tvMag = findViewById(R.id.tv_mag);
        tvPhy = findViewById(R.id.tv_phy);
        tvType = findViewById(R.id.tv_type);
        tvAlive = findViewById(R.id.tv_alive);
        tvDiff = findViewById(R.id.tv_diff);

        rvSkill = findViewById(R.id.rv_skill);
        //linearLayoutManager = new LinearLayoutManager(DetailActivity.this);
        rvSkill.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rvBuild = findViewById(R.id.rv_build);
        rvBuild.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        String idhero = intent.getStringExtra("varID");
        String url = "https:" + intent.getStringExtra("varImage");

        tvNama.setText(intent.getStringExtra("varNama"));
        if (intent.getStringExtra("varImage").isEmpty()) {
            imageViewHero.setImageResource(R.drawable.background_splash);
            } else {
                Picasso.get().load(url).into(imageViewHero);
        }

        APIRequestData ardData = RetroServer.getRetrofit().create(APIRequestData.class);
        Call<RootDetail> detailHeroProcess = ardData.getHeroDetail(idhero);
        detailHeroProcess.enqueue(new Callback<RootDetail>() {
            @Override
            public void onResponse(Call<RootDetail> call, Response<RootDetail> response) {
                tvMag.setText("Mag \t\t\t: " + response.body().getData().getMag());
                tvPhy.setText("Phy \t\t\t\t: " + response.body().getData().getPhy());
                tvType.setText("Type \t\t\t: " + response.body().getData().getType());
                tvAlive.setText("Alive \t\t\t: " + response.body().getData().getAlive());
                tvDiff.setText("Diff \t\t\t\t: " + response.body().getData().getDiff());

                tvBestPartner.setText(response.body().getData().getCounters().getBest().getName());
                tvTipsBestPartner.setText( response.body().getData().getCounters().getBest().getBest_mate_tips());
                tvCounter.setText(response.body().getData().getCounters().getCounters().getName());
                tvTipsCounter.setText( response.body().getData().getCounters().getCounters().getRestrain_hero_tips());
                tvCountered.setText( response.body().getData().getCounters().getCountered().getName());
                tvTipsCountered.setText(response.body().getData().getCounters().getCountered().getBy_restrain_tips());

                if (response.body().getData().getCounters().getBest().getIcon().isEmpty()) {
                    ivBestPartner.setImageResource(R.drawable.background_splash);
                } else {
                    Picasso.get().load("https:" + response.body().getData().getCounters().getBest().getIcon()).into(ivBestPartner);
                }

                if (response.body().getData().getCounters().getCounters().getIcon().isEmpty()) {
                    ivCounter.setImageResource(R.drawable.background_splash);
                } else {
                    Picasso.get().load("https:" + response.body().getData().getCounters().getCounters().getIcon()).into(ivCounter);
                }

                if (response.body().getData().getCounters().getCountered().getIcon().isEmpty()) {
                    ivCountered.setImageResource(R.drawable.background_splash);
                } else {
                    Picasso.get().load("https:" + response.body().getData().getCounters().getCountered().getIcon()).into(ivCountered);
                }
            }

            @Override
            public void onFailure(Call<RootDetail> call, Throwable t) {
                t.getMessage();
            }
        });

        //setting button
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, HeroActivity.class));
            }
        });
        btnInfo = findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, ProfileActivity.class));
            }
        });


        retriveUser(idhero);
        retriveBuild(idhero);
    }

    private void retriveUser(String id) {
        APIRequestData ardData = RetroServer.getRetrofit().create(APIRequestData.class);
        Call<RootDetail> retrieveProcess = ardData.getHeroDetail(id);
        retrieveProcess.enqueue(new Callback<RootDetail>() {
            @Override
            public void onResponse(Call<RootDetail> call, Response<RootDetail> response) {
                listSkill = response.body().getData();
                adapterSkill = new SkillViewAdapter(listSkill.getSkill().getSkillInfo());
                rvSkill.setAdapter(adapterSkill);
            }

            @Override
            public void onFailure(Call<RootDetail> call, Throwable t) {

            }
        });
    }

    private void retriveBuild(String id) {
        Toast.makeText(this, "id : " + id, Toast.LENGTH_SHORT).show();
        APIRequestData ardData = RetroServer.getRetrofit().create(APIRequestData.class);
        Call<RootDetail> retrieveProcess = ardData.getHeroDetail(id);
        retrieveProcess.enqueue(new Callback<RootDetail>() {
            @Override
            public void onResponse(Call<RootDetail> call, Response<RootDetail> response) {
                listBuild = response.body().getData();

 //               Toast.makeText(DetailActivity.this, "ss" + listBuild.getGear().getOut_pack().get(0).getEquipment_id(), Toast.LENGTH_SHORT).show();
                adapterBuild = new BuildViewAdapter(listBuild.getGear().getOut_pack());
                rvBuild.setAdapter(adapterBuild);
            }

            @Override
            public void onFailure(Call<RootDetail> call, Throwable t) {

            }
        });
    }
}