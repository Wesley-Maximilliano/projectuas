package com.if3a.mobilelegendsrework.model.hero;

import java.util.ArrayList;

public class Root{
    public int code;
    public String message;
    public ArrayList<Hero> data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Hero> getHero() {
        return data;
    }
}
