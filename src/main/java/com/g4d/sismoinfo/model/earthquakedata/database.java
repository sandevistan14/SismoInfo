package com.g4d.sismoinfo.model.earthquakedata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class database {
    public static ObservableList<Earthquake> getInitialData() {
        return initialData;
    }

    public static void setInitialData(ObservableList<Earthquake> initialData) {
        database.initialData = initialData;
    }

    private static ObservableList<Earthquake> initialData = FXCollections.observableArrayList();

    private static FilteredList<Earthquake> filteredData = new FilteredList<>(initialData);

    private static Predicate<Earthquake> filterAfterDate = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {
            return true;
        }
    };

    private static Predicate<Earthquake> filterBeforeDate = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {
            return true;
        }
    };

    private static Predicate<Earthquake> filterByMinIntensity  = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {
            return true;
        }
    };

    private static Predicate<Earthquake> filterByMaxIntensity  = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {
            return true;
        }
    };

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

    public static FilteredList<Earthquake> getFilteredData() {
        return filteredData;
    }

    public static void setFilteredData(FilteredList<Earthquake> filteredData) {
        database.filteredData = filteredData;
    }

    public static Predicate<Earthquake> getFilterAfterDate() {
        return filterAfterDate;
    }

    public static void setFilterAfterDate(Predicate<Earthquake> filterAfterDate) {
        database.filterAfterDate = filterAfterDate;
    }

    public static Predicate<Earthquake> getFilterBeforeDate() {
        return filterBeforeDate;
    }

    public static void setFilterBeforeDate(Predicate<Earthquake> filterBeforeDate) {
        database.filterBeforeDate = filterBeforeDate;
    }

    public static Predicate<Earthquake> getFilterByMinIntensity() {
        return filterByMinIntensity;
    }

    public static void setFilterByMinIntensity(Predicate<Earthquake> filterByMinIntensity) {
        database.filterByMinIntensity = filterByMinIntensity;
    }

    public static Predicate<Earthquake> getFilterByMaxIntensity() {
        return filterByMaxIntensity;
    }

    public static void setFilterByMaxIntensity(Predicate<Earthquake> filterByMaxIntensity) {
        database.filterByMaxIntensity = filterByMaxIntensity;
    }

    public static Predicate<Earthquake> getAllPredicates() {
        return getFilterAfterDate().and(getFilterBeforeDate()).and(getFilterByMaxIntensity()).and(getFilterByMinIntensity());
    }
}
