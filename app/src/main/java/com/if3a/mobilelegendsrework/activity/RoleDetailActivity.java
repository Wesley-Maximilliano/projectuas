package com.if3a.mobilelegendsrework.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.if3a.mobilelegendsrework.R;
import com.if3a.mobilelegendsrework.adapter.RoleDetailViewAdapter;
import com.if3a.mobilelegendsrework.adapter.SpeciallyDetailViewAdapter;
import com.if3a.mobilelegendsrework.model.role.Role;
import com.if3a.mobilelegendsrework.model.role.RoleHero;
import com.if3a.mobilelegendsrework.model.specially.SpeciallyHero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoleDetailActivity extends AppCompatActivity {
    private RecyclerView rvUser;
    private ProgressBar pbUser;
    private ArrayList<RoleHero> listHero;
    private RoleDetailViewAdapter adapterUser;
    private LinearLayoutManager linearLayoutManager;
    private RequestQueue mRequestQueue;
    private ImageButton btnBack, btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_detail);

        //setting button
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoleDetailActivity.this, RoleActivity.class));
            }
        });
        btnInfo = findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoleDetailActivity.this, ProfileActivity.class));
            }
        });

        rvUser = findViewById(R.id.rv_user);
        pbUser = findViewById(R.id.pb_user);

        linearLayoutManager = new LinearLayoutManager(RoleDetailActivity.this);
        rvUser.setLayoutManager(linearLayoutManager);

        listHero = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }


    private void jsonParse() {
        Intent intent = getIntent();
        String idRole = intent.getStringExtra("varIDRole");

        String url = "https://api.dazelpro.com/mobile-legends/role?roleName=" + idRole;

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

                                listHero.add(new RoleHero(name, avatar, role, specially));
                            }

                            adapterUser= new RoleDetailViewAdapter(listHero);
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
}