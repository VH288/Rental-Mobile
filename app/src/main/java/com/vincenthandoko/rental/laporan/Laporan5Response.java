package com.vincenthandoko.rental.laporan;

import java.util.List;

public class Laporan5Response {
    private String message;
    private List<Laporan5> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Laporan5> getData() {
        return data;
    }

    public void setData(List<Laporan5> data) {
        this.data = data;
    }
}
