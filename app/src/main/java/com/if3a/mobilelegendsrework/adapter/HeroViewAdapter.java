package com.if3a.mobilelegendsrework.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.if3a.mobilelegendsrework.R;
import com.if3a.mobilelegendsrework.activity.DetailActivity;
import com.if3a.mobilelegendsrework.model.hero.Hero;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HeroViewAdapter extends RecyclerView.Adapter<HeroViewAdapter.ViewHolder> {
    private List<Hero> heroList = new ArrayList<>();
    Context context;

    public HeroViewAdapter(Context context, List<Hero> listHero) {
        this.heroList = listHero;
        this.context = context;
    }

    public void setFilteredList(List<Hero> filteredList){
        this.heroList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_hero,
                        parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hero hr = heroList.get(position);

        if(position%2==0){
            holder.cvHero.startAnimation(AnimationUtils.loadAnimation(context, R.anim.first_item));
        }
        else{
            holder.cvHero.startAnimation(AnimationUtils.loadAnimation(context, R.anim.second_item));
        }

        holder.tvName.setText(hr.getName());
        String url = "https:" + hr.getKey();

        if(hr.getKey().isEmpty()){
            holder.imageView.setImageResource(R.drawable.background_splash);
        }
        else{
            Picasso.get().load(url).into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "ID User :" + hr.getHeroid(), Toast.LENGTH_SHORT).show();

                String idHero = hr.getHeroid();
                String imageHero = hr.getKey();
                String namaHero = hr.getName();

                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);

                intent.putExtra("varID", idHero);
                intent.putExtra("varImage", imageHero);
                intent.putExtra("varNama", namaHero);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvKey;
        private CardView cvHero;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvHero = itemView.findViewById(R.id.cardview);
            tvName = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.iv_hero);
        }
    }
}
