package com.g4d.sismoinfo.earthquakedata;
import com.g4d.sismoinfo.dataFormatting;

import java.time.LocalDate;
import java.time.LocalTime;

public class Earthquake {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String localisation;
    private String epicentralRegion;
    private Shock shock;
    private double latitude;
    private double longitude;
    private int epicentralIntensity;
    private epicentralCode epicentralIntensityQuality;

    public Earthquake(int id, String date, String time, String localisation, String epicentralRegion, String shock, double latitude, double longitude, int epicentralIntensity, String epicentralIntensityQuality) {
        this.id = id;
        this.date = dataFormatting.dateParsing(date);
        this.time = dataFormatting.timeParsing(time);
        this.localisation = localisation;
        this.epicentralRegion = epicentralRegion;
        this.shock = Shock.fromValue(shock);
        this.latitude = latitude;
        this.longitude = longitude;
        this.epicentralIntensity = epicentralIntensity;
        this.epicentralIntensityQuality = epicentralCode.fromValue(epicentralIntensityQuality);
    }
}