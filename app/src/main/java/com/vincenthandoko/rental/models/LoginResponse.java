package com.vincenthandoko.rental.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private String message;
    @SerializedName("user_id")
    private String userId;
    private String role;

    public LoginResponse(String message, String userId, String role) {
        this.message = message;
        this.userId = userId;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
