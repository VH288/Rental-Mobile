package com.vincenthandoko.rental.laporan;

import com.google.gson.annotations.SerializedName;

public class Laporan3 {
    private String id;
    @SerializedName("Nama_Driver")
    private String namaDriver;
    @SerializedName("Jumlah_Transaksi")
    private int jumlahTransaksi;

    public Laporan3(String id, String namaDriver, int jumlahTransaksi) {
        this.id = id;
        this.namaDriver = namaDriver;
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

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }
}
