package com.vincenthandoko.rental.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AsetResponse {
    private String message;
    @SerializedName("aset")
    private List<Aset> asetList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Aset> getAsetList() {
        return asetList;
    }

    public void setAsetList(List<Aset> asetList) {
        this.asetList = asetList;
    }
}
