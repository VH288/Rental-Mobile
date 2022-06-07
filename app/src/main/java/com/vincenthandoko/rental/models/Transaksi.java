package com.vincenthandoko.rental.models;

import com.google.gson.annotations.SerializedName;

public class Transaksi {
    private String id;
    @SerializedName("id_customer")
    private String idCustomer;
    @SerializedName("id_driver")
    private String idDriver;
    @SerializedName("id_promo")
    private String idPromo;
    @SerializedName("id_pegawai")
    private String idPegawai;
    @SerializedName("id_aset")
    private String id_aset;
    private String tglpemesanan;
    private String tglmulai;
    private String tglselesai;
    private int verified;

    public Transaksi(String idCustomer, String idDriver, String idPromo, String idPegawai, String id_aset, String tglpemesanan, String tglmulai, String tglselesai, int verified) {
        this.idCustomer = idCustomer;
        this.idDriver = idDriver;
        this.idPromo = idPromo;
        this.idPegawai = idPegawai;
        this.id_aset = id_aset;
        this.tglpemesanan = tglpemesanan;
        this.tglmulai = tglmulai;
        this.tglselesai = tglselesai;
        this.verified = verified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getId_aset() {
        return id_aset;
    }

    public void setId_aset(String id_aset) {
        this.id_aset = id_aset;
    }

    public String getTglpemesanan() {
        return tglpemesanan;
    }

    public void setTglpemesanan(String tglpemesanan) {
        this.tglpemesanan = tglpemesanan;
    }

    public String getTglmulai() {
        return tglmulai;
    }

    public void setTglmulai(String tglmulai) {
        this.tglmulai = tglmulai;
    }

    public String getTglselesai() {
        return tglselesai;
    }

    public void setTglselesai(String tglselesai) {
        this.tglselesai = tglselesai;
    }

    public int isVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }
}
