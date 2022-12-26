package com.if3a.mobilelegendsrework.model.detail;

public class Data{
    public String mag;
    public String phy;
    public String alive;
    public String diff;
    public String type;
    public Skill skill;
    public Gear gear;


    public String getMag() {
        return mag;
    }

    public String getPhy() {
        return phy;
    }

    public String getAlive() {
        return alive;
    }

    public String getDiff() {
        return diff;
    }

    public String getType() {
        return type;
    }

    public Skill getSkill() {
        return skill;
    }

    public Gear getGear() {
        return gear;
    }

    public Counters getCounters() {
        return counters;
    }

    public Counters counters;
}
