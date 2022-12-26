package com.if3a.mobilelegendsrework.model.role;

public class RoleHero {
    public String hero_name;
    public String hero_avatar;
    public String hero_role;
    public String hero_specially;
    public RoleHeroOverview hero_overview;

    public RoleHero(String name, String avatar, String role, String specially) {
        this.hero_name = name;
        this.hero_avatar = avatar;
        this.hero_role = role;
        this.hero_specially = specially;
    }

    public String getHero_name() {
        return hero_name;
    }

    public String getHero_avatar() {
        return hero_avatar;
    }

    public String getHero_role() {
        return hero_role;
    }

    public String getHero_specially() {
        return hero_specially;
    }

    public RoleHeroOverview getHero_overview() {
        return hero_overview;
    }
}
