package com.vincenthandoko.rental.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingResponse {
    private String message;
    @SerializedName("rating")
    private List<Rating> ratingList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
