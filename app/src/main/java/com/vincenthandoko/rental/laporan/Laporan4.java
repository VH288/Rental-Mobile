package com.vincenthandoko.rental.laporan;

import com.google.gson.annotations.SerializedName;

public class Laporan4 {
    private String id;
    @SerializedName("Nama_Driver")
    private String namaDriver;
    @SerializedName("Rata_Rata")
    private float rataRata;
    @SerializedName("Jumlah_Transaksi")
    private int jumlahTransaksi;

    public Laporan4(String id, String namaDriver, float rataRata, int jumlahTransaksi) {
        this.id = id;
        this.namaDriver = namaDriver;
        this.rataRata = rataRata;
        this.jumlahTransaksi = jumlahTransaksi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }

    public float getRataRata() {
        return rataRata;
    }

    public void setRataRata(float rataRata) {
        this.rataRata = rataRata;
    }

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }
}
