package com.g4d.sismoinfo.model.earthquakedata;
import com.g4d.sismoinfo.model.dataFormatting;

import java.time.LocalDate;
import java.time.LocalTime;

public class Earthquake {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String localisation;
    private String epicentralRegion;
    private Shock shock;
    private double xRGF93;
    private double yRGF93;
    private double latitude;
    private double longitude;
    private double epicentralIntensity;
    private epicentralCode epicentralIntensityQuality;

    public Earthquake(String id, String date, String time, String localisation, String epicentralRegion, String shock, String xRGF93, String yRGF93, String latitude, String longitude, String epicentralIntensity, String epicentralIntensityQuality) {
        try {
            this.id = Integer.parseInt(id);
        }
        catch (NumberFormatException nfe) {
            return;
        }
        this.date = dataFormatting.dateParsing(date);
        this.time = dataFormatting.timeParsing(time);
        this.localisation = localisation;
        this.epicentralRegion = epicentralRegion;
        this.shock = Shock.fromValue(shock);
        try {
            this.xRGF93 = Double.parseDouble(xRGF93);
        }
        catch (NumberFormatException nfe) {
            this.xRGF93 = 0;
        }
        try {
            this.yRGF93 = Double.parseDouble(yRGF93);
        }
        catch (NumberFormatException nfe) {
            this.yRGF93 = 0;
        }
        try {
            this.latitude = Double.parseDouble(latitude);
        }
        catch (NumberFormatException nfe) {
            this.latitude = 0;
        }
        try {
            this.longitude = Double.parseDouble(longitude);
        }
        catch (NumberFormatException nfe) {
            this.longitude = 0;
        }
        try {
            this.epicentralIntensity = Double.parseDouble(epicentralIntensity);
        }
        catch (NumberFormatException nfe) {
            this.epicentralIntensity = 0;
        }
        this.epicentralIntensityQuality = epicentralCode.fromValue(epicentralIntensityQuality);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getEpicentralRegion() {
        return epicentralRegion;
    }

    public void setEpicentralRegion(String epicentralRegion) {
        this.epicentralRegion = epicentralRegion;
    }

    public Shock getShock() {
        return shock;
    }

    public void setShock(Shock shock) {
        this.shock = shock;
    }

    public double getxRGF93() {
        return xRGF93;
    }

    public void setxRGF93(double xRGF93) {
        this.xRGF93 = xRGF93;
    }

    public double getyRGF93() {
        return yRGF93;
    }

    public void setyRGF93(double yRGF93) {
        this.yRGF93 = yRGF93;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getEpicentralIntensity() {
        return epicentralIntensity;
    }

    public void setEpicentralIntensity(double epicentralIntensity) {
        this.epicentralIntensity = epicentralIntensity;
    }

    public epicentralCode getEpicentralIntensityQuality() {
        return epicentralIntensityQuality;
    }

    public void setEpicentralIntensityQuality(epicentralCode epicentralIntensityQuality) {
        this.epicentralIntensityQuality = epicentralIntensityQuality;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", localisation='" + localisation + '\'' +
                ", epicentralRegion='" + epicentralRegion + '\'' +
                ", shock=" + shock +
                ", xRGF93=" + xRGF93 +
                ", yRGF93=" + yRGF93 +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", epicentralIntensity=" + epicentralIntensity +
                ", epicentralIntensityQuality=" + epicentralIntensityQuality +
                '}';
    }
}