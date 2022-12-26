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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.if3a.mobilelegendsrework.R;
import com.if3a.mobilelegendsrework.adapter.HeroViewAdapter;
import com.if3a.mobilelegendsrework.adapter.SkillViewAdapter;
import com.if3a.mobilelegendsrework.adapter.SpeciallyDetailViewAdapter;
import com.if3a.mobilelegendsrework.adapter.SpeciallyViewAdapter;
import com.if3a.mobilelegendsrework.api.APIRequestData;
import com.if3a.mobilelegendsrework.api.APIRequestData2;
import com.if3a.mobilelegendsrework.api.RetroServer;
import com.if3a.mobilelegendsrework.api.RetroServer2;
import com.if3a.mobilelegendsrework.model.hero.Hero;
import com.if3a.mobilelegendsrework.model.hero.Root;
import com.if3a.mobilelegendsrework.model.specially.RootSpecially;
import com.if3a.mobilelegendsrework.model.specially.SpeciallyHero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeciallyDetailActivity extends AppCompatActivity {
    private RecyclerView rvUser;
    private ProgressBar pbUser;
    private ArrayList<SpeciallyHero> listHero;
    private SpeciallyDetailViewAdapter adapterUser;
    private LinearLayoutManager linearLayoutManager;
    private RequestQueue mRequestQueue;
    private ImageButton btnBack, btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specially_detail);

        //setting button
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpeciallyDetailActivity.this, Speciallities.class));
            }
        });
        btnInfo = findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpeciallyDetailActivity.this, ProfileActivity.class));
            }
        });

        rvUser = findViewById(R.id.rv_user);
        pbUser = findViewById(R.id.pb_user);

        linearLayoutManager = new LinearLayoutManager(SpeciallyDetailActivity.this);
        rvUser.setLayoutManager(linearLayoutManager);

        listHero = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

     private void jsonParse() {
        Intent intent = getIntent();
        String idhero = intent.getStringExtra("varIDSpecially");

        String url = "https://api.dazelpro.com/mobile-legends/specially?speciallyName=" + idhero;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hero");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject skill = jsonArray.getJSONObject(i);

                                String name = skill.getString("hero_name");
                                String avatar = skill.getString("hero_avatar");
                                String role  = skill.getString("hero_role");
                                String specially = skill.getString("hero_specially");

                                listHero.add(new SpeciallyHero(name, avatar, role, specially));
                            }

                            adapterUser= new SpeciallyDetailViewAdapter(listHero);
                            rvUser.setAdapter(adapterUser);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    /*private void retriveUser() {
        pbUser.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String idSpecially = intent.getStringExtra("varIdSpecially");

        APIRequestData2 ardData = RetroServer2.getRetrofit().create(APIRequestData2.class);
        Call<RootSpecially> retrieveProcess = ardData.getSpeciallyHero(idSpecially);
        retrieveProcess.enqueue(new Callback<RootSpecially>() {
            @Override
            public void onResponse(Call<RootSpecially> call, Response<RootSpecially> response) {
                listHero = response.body().getSpeciallyHero();
                adapterUser = new SpeciallyDetailViewAdapter(listHero);
                rvUser.setAdapter(adapterUser);
                pbUser.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<RootSpecially> call, Throwable t) {
                Toast.makeText(SpeciallyDetailActivity.this, "Gagal Menghubungi Server : " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                pbUser.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retriveUser();
    }*/

}