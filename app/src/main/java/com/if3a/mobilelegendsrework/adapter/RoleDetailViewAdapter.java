package com.if3a.mobilelegendsrework.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if3a.mobilelegendsrework.R;
import com.if3a.mobilelegendsrework.model.role.RoleHero;
import com.if3a.mobilelegendsrework.model.specially.SpeciallyHero;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RoleDetailViewAdapter extends RecyclerView.Adapter<RoleDetailViewAdapter.ViewHolder> {
    private List<RoleHero> roleList = new ArrayList<>();

    public RoleDetailViewAdapter(List<RoleHero> roleList) {
        this.roleList = roleList;
    }


    @NonNull
    @Override
    public RoleDetailViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_role_hero,
                        parent, false);
        RoleDetailViewAdapter.ViewHolder holder = new RoleDetailViewAdapter.ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoleDetailViewAdapter.ViewHolder holder, int position) {
        RoleHero sp = roleList.get(position);

        holder.tvName.setText(sp.getHero_name());
        holder.tvSpecially.setText(sp.getHero_specially());
        holder.tvRole.setText(sp.getHero_role());

        String url = "https:" + sp.getHero_avatar();

        if(sp.getHero_avatar().isEmpty()){
            holder.ivAvatar.setImageResource(R.drawable.ic_person_24);
        }
        else{
            Picasso.get().load(url).into(holder.ivAvatar);
        }
    }

    @Override
    public int getItemCount() {
        return roleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRole, tvSpecially;
        ImageView ivAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvRole = itemView.findViewById(R.id.tv_role);
            tvSpecially = itemView.findViewById(R.id.tv_specially);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
