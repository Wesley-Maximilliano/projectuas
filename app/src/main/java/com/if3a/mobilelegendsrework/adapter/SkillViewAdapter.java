package com.if3a.mobilelegendsrework.adapter;

import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.if3a.mobilelegendsrework.R;
import com.if3a.mobilelegendsrework.model.detail.Skill;
import com.if3a.mobilelegendsrework.model.specially.SpeciallyHero;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SkillViewAdapter extends RecyclerView.Adapter<SkillViewAdapter.ViewHolder> {
    private List<Skill> skillList = new ArrayList<>();

    public SkillViewAdapter(List<Skill> skillList){
        this.skillList = skillList;
    }

    @NonNull
    @Override
    public SkillViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_heroskill,
                        parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Skill sk = skillList.get(position);

        holder.tvSkill.setText(sk.getName());
        holder.tvDes.setText("Description \n" + Html.fromHtml(sk.getDes()));
        holder.tvTips.setText("Tips" + "\n" + sk.getTips());

        String url = "https:" + sk.getIcon();

        if(sk.getIcon().isEmpty()){
            holder.ivSkill.setImageResource(R.drawable.background_splash);
        }
        else{
            Picasso.get().load(url).into(holder.ivSkill);
        }
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSkill, tvDes, tvTips;
        private ImageView ivSkill;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSkill = itemView.findViewById(R.id.tv_skill);
            tvDes = itemView.findViewById(R.id.tv_des);
            ivSkill = itemView.findViewById(R.id.iv_skill);
            tvTips = itemView.findViewById(R.id.tv_tips);
        }
    }
}
