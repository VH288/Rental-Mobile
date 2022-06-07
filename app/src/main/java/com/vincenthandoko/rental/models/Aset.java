package com.vincenthandoko.rental.models;

import com.google.gson.annotations.SerializedName;

public class Aset {
    private String id;
    @SerializedName("id_customer")
    private String idCustomer;
    private String nama;
    private String tipe;
    private String transmisi;
    private String bbm;
    private String volume;
    private String warna;
    private String kapasitas;
    private String noplat;
    private String nostnk;
    @SerializedName("fitur_ac")
    private int fiturAc;
    @SerializedName("fitur_multimedia")
    private int fiturMultimedia;
    @SerializedName("fitur_airbag")
    private int fiturAirbag;
    private String kategori;
    @SerializedName("periode_mulai")
    private String periodeMulai;
    @SerializedName("periode_selesai")
    private String periodeSelesai;
    private String tglservis;
    private int harga;
    private int available;

    public Aset(String idCustomer, String nama, String tipe, String transmisi, String bbm, String volume, String warna, String kapasitas, String noplat, String nostnk, int fiturAc, int fiturMultimedia, int fiturAirbag, String kategori, String periodeMulai, String periodeSelesai, String tglservis, int harga, int available) {
        this.idCustomer = idCustomer;
        this.nama = nama;
        this.tipe = tipe;
        this.transmisi = transmisi;
        this.bbm = bbm;
        this.volume = volume;
        this.warna = warna;
        this.kapasitas = kapasitas;
        this.noplat = noplat;
        this.nostnk = nostnk;
        this.fiturAc = fiturAc;
        this.fiturMultimedia = fiturMultimedia;
        this.fiturAirbag = fiturAirbag;
        this.kategori = kategori;
        this.periodeMulai = periodeMulai;
        this.periodeSelesai = periodeSelesai;
        this.tglservis = tglservis;
        this.harga = harga;
        this.available = available;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getTransmisi() {
        return transmisi;
    }

    public void setTransmisi(String transmisi) {
        this.transmisi = transmisi;
    }

    public String getBbm() {
        return bbm;
    }

    public void setBbm(String bbm) {
        this.bbm = bbm;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getNoplat() {
        return noplat;
    }

    public void setNoplat(String noplat) {
        this.noplat = noplat;
    }

    public String getNostnk() {
        return nostnk;
    }

    public void setNostnk(String nostnk) {
        this.nostnk = nostnk;
    }

    public int isFiturAc() {
        return fiturAc;
    }

    public void setFiturAc(int fiturAc) {
        this.fiturAc = fiturAc;
    }

    public int isFiturMultimedia() {
        return fiturMultimedia;
    }

    public void setFiturMultimedia(int fiturMultimedia) {
        this.fiturMultimedia = fiturMultimedia;
    }

    public int isFiturAirbag() {
        return fiturAirbag;
    }

    public void setFiturAirbag(int fiturAirbag) {
        this.fiturAirbag = fiturAirbag;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getPeriodeMulai() {
        return periodeMulai;
    }

    public void setPeriodeMulai(String periodeMulai) {
        this.periodeMulai = periodeMulai;
    }

    public String getPeriodeSelesai() {
        return periodeSelesai;
    }

    public void setPeriodeSelesai(String periodeSelesai) {
        this.periodeSelesai = periodeSelesai;
    }

    public String getTglservis() {
        return tglservis;
    }

    public void setTglservis(String tglservis) {
        this.tglservis = tglservis;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int isAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
