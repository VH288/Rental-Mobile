package com.vincenthandoko.rental.models;public class Driver {
    private String id;
    private String nama;
    private String alamat;
    private String email;
    private String password;
    private String notelp;
    private String tgllahir;
    private String gender;
    private String bahasa;
    private int harga;
    private float avgrating;
    private int countrating;
    private int available;
    private int verified;
    private String sim;
    private String napza;
    private String kesehatan;
    private String skck;
    private String foto;

    public Driver(String nama, String alamat, String email, String password, String notelp, String tgllahir, String gender, String bahasa, int harga, float avgrating, int countrating, int available, int verified) {
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
        this.notelp = notelp;
        this.tgllahir = tgllahir;
        this.gender = gender;
        this.bahasa = bahasa;
        this.harga = harga;
        this.avgrating = avgrating;
        this.countrating = countrating;
        this.available = available;
        this.verified = verified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBahasa() {
        return bahasa;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public float getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(float avgrating) {
        this.avgrating = avgrating;
    }

    public int getCountrating() {
        return countrating;
    }

    public void setCountrating(int countrating) {
        this.countrating = countrating;
    }

    public int isAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int isVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }
}
