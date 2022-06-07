package com.vincenthandoko.rental.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiwayatTransaksiResponse {
    private String message;
    @SerializedName("transaksi")
    private List<RiwayatTransaksi> riwayatTransaksiList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RiwayatTransaksi> getRiwayatTransaksiList() {
        return riwayatTransaksiList;
    }

    public void setRiwayatTransaksiList(List<RiwayatTransaksi> riwayatTransaksiList) {
        this.riwayatTransaksiList = riwayatTransaksiList;
    }
}
