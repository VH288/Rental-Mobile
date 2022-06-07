package com.vincenthandoko.rental.laporan;

import java.util.List;

public class Laporan3Response {
    private String message;
    private List<Laporan3> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Laporan3> getData() {
        return data;
    }

    public void setData(List<Laporan3> data) {
        this.data = data;
    }
}
