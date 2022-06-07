package com.vincenthandoko.rental.models;
public class Customer {
    private String id;
    private String nama;
    private String email;
    private String password;
    private String notelp;
    private String gender;
    private String tgllahir;
    private String ktp;
    private String ktpengenal;
    private String sim;

    public Customer(String nama, String email, String password, String notelp, String gender, String tgllahir) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.notelp = notelp;
        this.gender = gender;
        this.tgllahir = tgllahir;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }
}
