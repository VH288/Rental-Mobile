package com.vincenthandoko.rental.laporan;

import com.google.gson.annotations.SerializedName;

public class Laporan1 {
    @SerializedName("Tipe_Aset")
    private String tipeAset;
    @SerializedName("Nama_Aset")
    private String namaAset;
    @SerializedName("Jumlah_Transaksi")
    private int jumlahTransaksi;
    @SerializedName("Total_Harga")
    private int totalHarga;

    public Laporan1(String tipeAset, String namaAset, int jumlahTransaksi, int totalHarga) {
        this.tipeAset = tipeAset;
        this.namaAset = namaAset;
        this.jumlahTransaksi = jumlahTransaksi;
        this.totalHarga = totalHarga;
    }

    public String getTipeAset() {
        return tipeAset;
    }

    public void setTipeAset(String tipeAset) {
        this.tipeAset = tipeAset;
    }

    public String getNamaAset() {
        return namaAset;
    }

    public void setNamaAset(String namaAset) {
        this.namaAset = namaAset;
    }

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
