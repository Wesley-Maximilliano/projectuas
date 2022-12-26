package com.if3a.mobilelegendsrework.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if3a.mobilelegendsrework.R;
import com.if3a.mobilelegendsrework.model.detail.Gear;
import com.if3a.mobilelegendsrework.model.detail.OutPack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BuildViewAdapter extends RecyclerView.Adapter<BuildViewAdapter.ViewHolder> {
    private List<OutPack> buildList = new ArrayList<>();

    public BuildViewAdapter(List<OutPack> buildList){
        this.buildList = buildList;
    }

    @NonNull
    @Override
    public BuildViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_build,
                        parent, false);
        BuildViewAdapter.ViewHolder holder = new BuildViewAdapter.ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BuildViewAdapter.ViewHolder holder, int position) {
        OutPack e = buildList.get(position);

        holder.tvBuild.setText(e.getEquip().getName());

        String url = "https:" + e.getEquip().getIcon();

        if(e.getEquip().getIcon().isEmpty()){
            holder.ivBuild.setImageResource(R.drawable.ic_person_24);
        }
        else{
            Picasso.get().load(url).into(holder.ivBuild);
        }
    }

    @Override
    public int getItemCount() {
        return buildList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBuild, tvDes;
        private ImageView ivBuild;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBuild = itemView.findViewById(R.id.tv_item);
            tvDes = itemView.findViewById(R.id.tv_des);
            ivBuild = itemView.findViewById(R.id.iv_build);
        }
    }
}
