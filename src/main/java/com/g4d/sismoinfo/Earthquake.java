package com.g4d.sismoinfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    private String identifiant;
    private Date date;
    private String heure;
    private String nom;
    private String regionEpicentrale;
    private String choc;
    private double xRGF93L93;
    private double yRGF93L93;
    private double latitudeWGS84;
    private double longitudeWGS84;
    private int intensiteEpicentrale;
    private String qualiteIntensiteEpicentrale;

    public Earthquake(String identifiant, String date, String heure, String nom, String regionEpicentrale, String choc,
                   String xRGF93L93, String yRGF93L93, String latitudeWGS84, String longitudeWGS84,
                   String intensiteEpicentrale, String qualiteIntensiteEpicentrale) {
        this.identifiant = identifiant;
        this.date = parseDate(date);
        this.heure = heure;
        this.nom = nom;
        this.regionEpicentrale = regionEpicentrale;
        this.choc = choc;
        this.xRGF93L93 = Double.parseDouble(xRGF93L93);
        this.yRGF93L93 = Double.parseDouble(yRGF93L93);
        this.latitudeWGS84 = Double.parseDouble(latitudeWGS84);
        this.longitudeWGS84 = Double.parseDouble(longitudeWGS84);
        this.intensiteEpicentrale = Integer.parseInt(intensiteEpicentrale);
        this.qualiteIntensiteEpicentrale = qualiteIntensiteEpicentrale;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public Date getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getNom() {
        return nom;
    }

    public String getRegionEpicentrale() {
        return regionEpicentrale;
    }

    public String getChoc() {
        return choc;
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

    public int getIntensiteEpicentrale() {
        return intensiteEpicentrale;
    }

    public String getQualiteIntensiteEpicentrale() {
        return qualiteIntensiteEpicentrale;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRegionEpicentrale(String regionEpicentrale) {
        this.regionEpicentrale = regionEpicentrale;
    }

    public void setChoc(String choc) {
        this.choc = choc;
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

    public void setIntensiteEpicentrale(int intensiteEpicentrale) {
        this.intensiteEpicentrale = intensiteEpicentrale;
    }

    public void setQualiteIntensiteEpicentrale(String qualiteIntensiteEpicentrale) {
        this.qualiteIntensiteEpicentrale = qualiteIntensiteEpicentrale;
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
