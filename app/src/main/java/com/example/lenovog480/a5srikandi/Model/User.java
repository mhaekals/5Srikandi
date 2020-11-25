package com.example.lenovog480.a5srikandi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("status") String status;
    @SerializedName("email") String email;
    @SerializedName("tipe_user") String tipe;
    @SerializedName("result") List<User> result;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
