package com.if3a.mobilelegendsrework.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if3a.mobilelegendsrework.R;
import com.if3a.mobilelegendsrework.activity.DetailActivity;
import com.if3a.mobilelegendsrework.activity.RoleDetailActivity;
import com.if3a.mobilelegendsrework.activity.SpeciallyDetailActivity;
import com.if3a.mobilelegendsrework.model.hero.Hero;
import com.if3a.mobilelegendsrework.model.role.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleViewAdapter extends RecyclerView.Adapter<RoleViewAdapter.ViewHolder> {
    private List<Role> listRole = new ArrayList<>();

    public RoleViewAdapter(List<Role> listRole) {
        this.listRole = listRole;
    }

    @NonNull
    @Override
    public RoleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_role,
                        parent, false);
        RoleViewAdapter.ViewHolder holder = new RoleViewAdapter.ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewAdapter.ViewHolder holder, int position) {
        Role r = listRole.get(position);

        holder.name.setText(r.getRole_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Role name : " + r.getRole_name(), Toast.LENGTH_SHORT).show();

                String idRole = r.getRole_name();

                Intent intent = new Intent(holder.itemView.getContext(), RoleDetailActivity.class);

                intent.putExtra("varIDRole", idRole);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRole.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_role);
        }
    }
}
