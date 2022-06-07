package com.vincenthandoko.rental.laporan;

import com.google.gson.annotations.SerializedName;

public class Laporan2 {
    @SerializedName("Nama_Customer")
    private String namaCustomer;
    @SerializedName("Nama_Aset")
    private String namaAset;
    @SerializedName("Jumlah_Transaksi")
    private int jumlahTransaksi;
    @SerializedName("Total_Harga")
    private int totalHarga;

    public Laporan2(String namaCustomer, String namaAset, int jumlahTransaksi, int totalHarga) {
        this.namaCustomer = namaCustomer;
        this.namaAset = namaAset;
        this.jumlahTransaksi = jumlahTransaksi;
        this.totalHarga = totalHarga;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
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
