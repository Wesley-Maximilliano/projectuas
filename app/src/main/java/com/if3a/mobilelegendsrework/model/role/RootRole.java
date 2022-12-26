package com.if3a.mobilelegendsrework.model.role;

import java.util.ArrayList;

public class RootRole {
    public boolean success;
    public int status;
    public int rowCount;
    public String message;
    public ArrayList<Role> role;
    public ArrayList<RoleHero> roleHero;

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

    public ArrayList<Role> getRole() {
        return role;
    }

    public ArrayList<RoleHero> getRoleHero() {
        return roleHero;
    }
}
