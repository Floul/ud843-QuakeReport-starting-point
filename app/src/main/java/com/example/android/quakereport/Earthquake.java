package com.example.android.quakereport;

public class Earthquake {
    // @param magnitude earth quake magnitude
    private Double magnitude;

    // @param city location of earthquake
    private String location;

    // @param date , the  date of the earthquake
    private Long date;

    private String url;

    public Earthquake(Double magnitude, String location, Long date, String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
        this.url = url;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public Long getDate() {
        return date;
    }

    public String getUrl (){return url;}
}

