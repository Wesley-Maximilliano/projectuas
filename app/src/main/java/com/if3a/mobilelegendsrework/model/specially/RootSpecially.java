package com.if3a.mobilelegendsrework.model.specially;

import java.util.ArrayList;

public class RootSpecially {
    public boolean success;
    public int status;
    public int rowCount;
    public String message;
    public ArrayList<Specially> specially;
    public ArrayList<SpeciallyHero> speciallyHero;


    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public int getRowCount() {
        return rowCount;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Specially> getSpecially() {
        return specially;
    }

    public ArrayList<SpeciallyHero> getSpeciallyHero() {
        return speciallyHero;
    }
}
