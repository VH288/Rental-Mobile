package com.vincenthandoko.rental.laporan;

import java.util.List;

public class Laporan1Response {
    private String message;
    private List<Laporan1> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Laporan1> getData() {
        return data;
    }

    public void setData(List<Laporan1> data) {
        this.data = data;
    }
}
