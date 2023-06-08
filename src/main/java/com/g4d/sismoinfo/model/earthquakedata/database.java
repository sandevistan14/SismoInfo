package com.g4d.sismoinfo.model.earthquakedata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class database {
    public static ObservableList<Earthquake> initialData = FXCollections.observableArrayList();

    private static ArrayList<Earthquake> filteredData;

    private static List<String> headerNames = Arrays.asList(
            "Identifiant",
            "Date (AAAA/MM/JJ)",
            "Heure",
            "Nom",
            "Région épicentrale",
            "Choc",
            "X RGF93/L93",
            "Y RGF93/L93",
            "Latitude en WGS 84",
            "Longitude en WGS 84",
            "Intensité épicentrale",
            "Qualité intensité épicentrale"
    );

    public static void readCSV(File csvFile) {
        if (csvFile == null) {return;}
        CSVParser parser = null;
        try {
            parser = new CSVParser(new FileReader(csvFile), CSVFormat.DEFAULT.withHeader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        };
        if (parser.getHeaderNames().equals(headerNames)){
            initialData.clear();
            for (CSVRecord record : parser){
                String id = record.get("Identifiant");
                String date = record.get("Date (AAAA/MM/JJ)");
                String hour = record.get("Heure");
                String localisation = record.get("Nom");
                String epicentralRegion = record.get("Région épicentrale");
                String shock = record.get("Choc");
                String xRGF93 = record.get("X RGF93/L93");
                String yRGF93 = record.get("Y RGF93/L93");
                String latitude = record.get("Latitude en WGS 84");
                String longitude = record.get("Longitude en WGS 84");
                String epicentralIntensity = record.get("Intensité épicentrale");
                String epicentralIntensityQuality = record.get("Qualité intensité épicentrale");
                initialData.add(new Earthquake(id,date,hour,localisation,epicentralRegion,shock,xRGF93,yRGF93,latitude,longitude,epicentralIntensity,epicentralIntensityQuality));
            }
        }
        else {
            // RENVOYER ERREUR
        }
    }
}
