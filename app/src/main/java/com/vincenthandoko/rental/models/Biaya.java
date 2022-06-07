package com.vincenthandoko.rental.models;

import com.google.gson.annotations.SerializedName;

public class Biaya {
    private String id;
    @SerializedName("id_transaksi")
    private String idTransaksi;
    @SerializedName("id_pegawai")
    private String idPegawai;
    private String tglpengembalian;
    private int denda;
    @SerializedName("durasi_driver")
    private int durasiDriver;
    @SerializedName("durasi_mobil")
    private int durasiMobil;
    private int total;
    private int paid;
    private int verified;

    public Biaya(String idTransaksi, String idPegawai, String tglpengembalian, int denda, int durasiDriver, int durasiMobil, int total, int paid, int verified) {
        this.idTransaksi = idTransaksi;
        this.idPegawai = idPegawai;
        this.tglpengembalian = tglpengembalian;
        this.denda = denda;
        this.durasiDriver = durasiDriver;
        this.durasiMobil = durasiMobil;
        this.total = total;
        this.paid = paid;
        this.verified = verified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getTglpengembalian() {
        return tglpengembalian;
    }

    public void setTglpengembalian(String tglpengembalian) {
        this.tglpengembalian = tglpengembalian;
    }

    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    public int getDurasiDriver() {
        return durasiDriver;
    }

    public void setDurasiDriver(int durasiDriver) {
        this.durasiDriver = durasiDriver;
    }

    public int getDurasiMobil() {
        return durasiMobil;
    }

    public void setDurasiMobil(int durasiMobil) {
        this.durasiMobil = durasiMobil;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int isPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int isVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }
}
