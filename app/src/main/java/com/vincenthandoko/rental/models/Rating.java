package com.vincenthandoko.rental.models;

import com.google.gson.annotations.SerializedName;

public class Rating {
    private String id;
    @SerializedName("id_driver")
    private String idDriver;
    @SerializedName("id_customer")
    private String idCustomer;
    private int rate;
    private String comment;

    public Rating(String idDriver, String idCustomer, int rate, String comment) {
        this.idDriver = idDriver;
        this.idCustomer = idCustomer;
        this.rate = rate;
        this.comment = comment;
    }

    public Rating(int rate, String comment) {
        this.rate = rate;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
