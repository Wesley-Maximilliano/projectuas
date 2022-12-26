package com.if3a.mobilelegendsrework.model.detail;

import com.if3a.mobilelegendsrework.model.detail.Item;

import java.util.ArrayList;

public class Skill{
    public ArrayList<Skill> skill;
    public Item item;
    public String name;
    public String icon;
    public String des;
    public String tips;

    public Skill(String name, String icon, String des, String tips) {
        this.name = name;
        this.icon = icon;
        this.des = des;
        this.tips = tips;
    }


    public ArrayList<Skill> getSkillInfo() {
        return skill;
    }

    public Item getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getDes() {
        return des;
    }

    public String getTips() {
        return tips;
    }
}
