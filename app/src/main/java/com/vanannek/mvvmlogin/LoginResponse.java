package com.vanannek.mvvmlogin;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
