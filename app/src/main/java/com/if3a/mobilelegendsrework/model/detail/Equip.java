package com.if3a.mobilelegendsrework.model.detail;

import java.util.ArrayList;

public class Equip{
    public String icon;
    public String name;
    public ArrayList<Equip> equip;
    public ArrayList<String> des;

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getDes() {
        return des;
    }

    public ArrayList<Equip> getEquip() {
        return equip;
    }
}
