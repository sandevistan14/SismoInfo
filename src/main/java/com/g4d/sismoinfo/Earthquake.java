package com.g4d.sismoinfo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    private String identifier;
    private Date date;
    private String time;
    private String name;
    private String epicentralRegion;
    private String shock;
    private double xRGF93L93;
    private double yRGF93L93;
    private double latitudeWGS84;
    private double longitudeWGS84;
    private int epicentralIntensity;
    private String epicentralIntensityQuality;

    public Earthquake(String identifier, String date, String time, String name, String epicentralRegion, String shock,
                      String xRGF93L93, String yRGF93L93, String latitudeWGS84, String longitudeWGS84,
                      String epicentralIntensity, String epicentralIntensityQuality) {
        this.identifier = identifier;
        this.date = parseDate(date);
        this.time = time;
        this.name = name;
        this.epicentralRegion = epicentralRegion;
        this.shock = shock;
        this.xRGF93L93 = Double.parseDouble(xRGF93L93);
        this.yRGF93L93 = Double.parseDouble(yRGF93L93);
        this.latitudeWGS84 = Double.parseDouble(latitudeWGS84);
        this.longitudeWGS84 = Double.parseDouble(longitudeWGS84);
        this.epicentralIntensity = Integer.parseInt(epicentralIntensity);
        this.epicentralIntensityQuality = epicentralIntensityQuality;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getEpicentralRegion() {
        return epicentralRegion;
    }

    public String getShock() {
        return shock;
    }

    public double getxRGF93L93() {
        return xRGF93L93;
    }

    public double getyRGF93L93() {
        return yRGF93L93;
    }

    public double getLatitudeWGS84() {
        return latitudeWGS84;
    }

    public double getLongitudeWGS84() {
        return longitudeWGS84;
    }

    public int getEpicentralIntensity() {
        return epicentralIntensity;
    }

    public String getEpicentralIntensityQuality() {
        return epicentralIntensityQuality;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEpicentralRegion(String epicentralRegion) {
        this.epicentralRegion = epicentralRegion;
    }

    public void setShock(String shock) {
        this.shock = shock;
    }

    public void setxRGF93L93(double xRGF93L93) {
        this.xRGF93L93 = xRGF93L93;
    }

    public void setyRGF93L93(double yRGF93L93) {
        this.yRGF93L93 = yRGF93L93;
    }

    public void setLatitudeWGS84(double latitudeWGS84) {
        this.latitudeWGS84 = latitudeWGS84;
    }

    public void setLongitudeWGS84(double longitudeWGS84) {
        this.longitudeWGS84 = longitudeWGS84;
    }

    public void setEpicentralIntensity(int epicentralIntensity) {
        this.epicentralIntensity = epicentralIntensity;
    }

    public void setEpicentralIntensityQuality(String epicentralIntensityQuality) {
        this.epicentralIntensityQuality = epicentralIntensityQuality;
    }

    private Date parseDate(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}