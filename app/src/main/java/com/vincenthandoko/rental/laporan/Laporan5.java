package com.vincenthandoko.rental.laporan;

import com.google.gson.annotations.SerializedName;

public class Laporan5 {
    @SerializedName("Nama_Customer")
    private String namaCustomer;
    @SerializedName("Jumlah_Transaksi")
    private int jumlahTransaksi;


    public Laporan5(String namaCustomer, int jumlahTransaksi) {
        this.namaCustomer = namaCustomer;
        this.jumlahTransaksi = jumlahTransaksi;

    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }


}
