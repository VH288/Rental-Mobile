package com.vincenthandoko.rental.models;public class TransaksiResponse {
    private String message;
    private Biaya biaya;
    private Transaksi transaksi;
    private Customer customer;
    private Aset aset;
    private Promo promo;
    private Driver driver;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Biaya getBiaya() {
        return biaya;
    }

    public void setBiaya(Biaya biaya) {
        this.biaya = biaya;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Aset getAset() {
        return aset;
    }

    public void setAset(Aset aset) {
        this.aset = aset;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
