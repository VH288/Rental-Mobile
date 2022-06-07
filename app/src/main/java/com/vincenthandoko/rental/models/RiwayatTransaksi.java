package com.vincenthandoko.rental.models;

import com.google.gson.annotations.SerializedName;

public class RiwayatTransaksi {
    private String idt;
    @SerializedName("namad")
    private String namaDriver;
    @SerializedName("namaa")
    private String namaAset;
    private String tglpemesanan;

    public RiwayatTransaksi(String idt, String namaDriver, String namaAset, String tglpemesanan) {
        this.idt = idt;
        this.namaDriver = namaDriver;
        this.namaAset = namaAset;
        this.tglpemesanan = tglpemesanan;
    }

    public String getIdt() {
        return idt;
    }

    public void setIdt(String idt) {
        this.idt = idt;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }

    public String getNamaAset() {
        return namaAset;
    }

    public void setNamaAset(String namaAset) {
        this.namaAset = namaAset;
    }

    public String getTglpemesanan() {
        return tglpemesanan;
    }

    public void setTglpemesanan(String tglpemesanan) {
        this.tglpemesanan = tglpemesanan;
    }
}
