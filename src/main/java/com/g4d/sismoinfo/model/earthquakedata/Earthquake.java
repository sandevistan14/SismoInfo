package com.g4d.sismoinfo.model.earthquakedata;
import com.g4d.sismoinfo.model.dataFormatting;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The `Earthquake` class represents an earthquake event.
 */
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

    /**
     * Constructs an `Earthquake` object with the provided values.
     *
     * @param id                         The identifier of the earthquake.
     * @param date                       The date of the earthquake.
     * @param time                       The time of the earthquake.
     * @param localisation               The localization of the earthquake.
     * @param epicentralRegion           The epicentral region of the earthquake.
     * @param shock                      The shock associated with the earthquake.
     * @param xRGF93                     The X coordinate in the RGF93/L93 coordinate system.
     * @param yRGF93                     The Y coordinate in the RGF93/L93 coordinate system.
     * @param latitude                   The latitude in the WGS 84 coordinate system.
     * @param longitude                  The longitude in the WGS 84 coordinate system.
     * @param epicentralIntensity        The epicentral intensity of the earthquake.
     * @param epicentralIntensityQuality The quality of the epicentral intensity.
     */
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

    /**
     * Gets the identifier of the earthquake.
     *
     * @return The identifier of the earthquake.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the identifier of the earthquake.
     *
     * @param id The identifier of the earthquake.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the date of the earthquake.
     *
     * @return The date of the earthquake.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the earthquake.
     *
     * @param date The date of the earthquake.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the time of the earthquake.
     *
     * @return The time of the earthquake.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time of the earthquake.
     *
     * @param time The time of the earthquake.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Gets the localization of the earthquake.
     *
     * @return The localization of the earthquake.
     */
    public String getLocalisation() {
        return localisation;
    }

    /**
     * Sets the localization of the earthquake.
     *
     * @param localisation The localization of the earthquake.
     */
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    /**
     * Gets the epicentral region of the earthquake.
     *
     * @return The epicentral region of the earthquake.
     */
    public String getEpicentralRegion() {
        return epicentralRegion;
    }

    /**
     * Sets the epicentral region of the earthquake.
     *
     * @param epicentralRegion The epicentral region of the earthquake.
     */
    public void setEpicentralRegion(String epicentralRegion) {
        this.epicentralRegion = epicentralRegion;
    }

    /**
     * Gets the shock associated with the earthquake.
     *
     * @return The shock associated with the earthquake.
     */
    public Shock getShock() {
        return shock;
    }

    /**
     * Sets the shock associated with the earthquake.
     *
     * @param shock The shock associated with the earthquake.
     */
    public void setShock(Shock shock) {
        this.shock = shock;
    }

    /**
     * Gets the X coordinate in the RGF93/L93 coordinate system.
     *
     * @return The X coordinate in the RGF93/L93 coordinate system.
     */
    public double getxRGF93() {
        return xRGF93;
    }

    /**
     * Sets the X coordinate in the RGF93/L93 coordinate system.
     *
     * @param xRGF93 The X coordinate in the RGF93/L93 coordinate system.
     */
    public void setxRGF93(double xRGF93) {
        this.xRGF93 = xRGF93;
    }

    /**
     * Gets the Y coordinate in the RGF93/L93 coordinate system.
     *
     * @return The Y coordinate in the RGF93/L93 coordinate system.
     */
    public double getyRGF93() {
        return yRGF93;
    }

    /**
     * Sets the Y coordinate in the RGF93/L93 coordinate system.
     *
     * @param yRGF93 The Y coordinate in the RGF93/L93 coordinate system.
     */
    public void setyRGF93(double yRGF93) {
        this.yRGF93 = yRGF93;
    }

    /**
     * Gets the latitude in the WGS 84 coordinate system.
     *
     * @return The latitude in the WGS 84 coordinate system.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude in the WGS 84 coordinate system.
     *
     * @param latitude The latitude in the WGS 84 coordinate system.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude in the WGS 84 coordinate system.
     *
     * @return The longitude in the WGS 84 coordinate system.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude in the WGS 84 coordinate system.
     *
     * @param longitude The longitude in the WGS 84 coordinate system.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the epicentral intensity of the earthquake.
     *
     * @return The epicentral intensity of the earthquake.
     */
    public double getEpicentralIntensity() {
        return epicentralIntensity;
    }

    /**
     * Sets the epicentral intensity of the earthquake.
     *
     * @param epicentralIntensity The epicentral intensity of the earthquake.
     */
    public void setEpicentralIntensity(double epicentralIntensity) {
        this.epicentralIntensity = epicentralIntensity;
    }

    /**
     * Gets the quality of the epicentral intensity.
     *
     * @return The quality of the epicentral intensity.
     */
    public epicentralCode getEpicentralIntensityQuality() {
        return epicentralIntensityQuality;
    }

    /**
     * Sets the quality of the epicentral intensity.
     *
     * @param epicentralIntensityQuality The quality of the epicentral intensity.
     */
    public void setEpicentralIntensityQuality(epicentralCode epicentralIntensityQuality) {
        this.epicentralIntensityQuality = epicentralIntensityQuality;
    }

    /**
     * Returns a string representation of the `Earthquake` object.
     *
     * @return A string representation of the `Earthquake` object.
     */
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