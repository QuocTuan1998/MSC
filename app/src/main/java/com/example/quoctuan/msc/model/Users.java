package com.example.quoctuan.msc.model;

/**
 * Created by Nguyen Van Tung on 4/26/2018.
 */

public class Users {
    private int id;
    private String email;
    private String password;
    private boolean role; //nếu admin==true, user==false
    private boolean status; //nếu visible==true, disable==false
    private String token;

    public Users(int id, String email, String password, boolean role, boolean status, String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.token = token;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
