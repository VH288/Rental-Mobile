package com.vincenthandoko.rental.laporan;

import java.util.List;

public class Laporan2Response {
    private String message;
    private List<Laporan2> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Laporan2> getData() {
        return data;
    }

    public void setData(List<Laporan2> data) {
        this.data = data;
    }
}
