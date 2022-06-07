package com.vincenthandoko.rental.laporan;

import java.util.List;

public class Laporan4Response {
    private String message;
    private List<Laporan4> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Laporan4> getData() {
        return data;
    }

    public void setData(List<Laporan4> data) {
        this.data = data;
    }
}
