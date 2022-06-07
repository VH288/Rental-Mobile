package com.vincenthandoko.rental.models;public class Promo {
    private String id;
    private String jenis;
    private String keterangan;
    private int diskon;

    public Promo(String jenis, String keterangan, int diskon) {
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.diskon = diskon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }
}
