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
import com.if3a.mobilelegendsrework.activity.SpeciallyDetailActivity;
import com.if3a.mobilelegendsrework.model.hero.Hero;
import com.if3a.mobilelegendsrework.model.specially.Specially;

import java.util.ArrayList;
import java.util.List;

public class SpeciallyViewAdapter extends RecyclerView.Adapter<SpeciallyViewAdapter.ViewHolder> {
    private List<Specially> speciallyList = new ArrayList<>();

    public SpeciallyViewAdapter(List<Specially> speciallyList) {
        this.speciallyList = speciallyList;
    }

    @NonNull
    @Override
    public SpeciallyViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_specially,
                        parent, false);
        SpeciallyViewAdapter.ViewHolder holder = new SpeciallyViewAdapter.ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciallyViewAdapter.ViewHolder holder, int position) {
        Specially sp = speciallyList.get(position);

        holder.tvName.setText(sp.getSpecially_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Specially name : " + sp.getSpecially_name(), Toast.LENGTH_SHORT).show();

                String idSpecially = sp.getSpecially_name();

                Intent intent = new Intent(holder.itemView.getContext(), SpeciallyDetailActivity.class);

                intent.putExtra("varIDSpecially", idSpecially);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return speciallyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_specially);
        }
    }
}
