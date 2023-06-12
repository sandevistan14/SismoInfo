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

/**
 * The `database` class manages earthquake data in a JavaFX application.
 */
public class database {

    /**
     * Gets the initial earthquake data.
     *
     * @return An ObservableList of Earthquake objects containing the initial data.
     */
    public static ObservableList<Earthquake> getInitialData() {
        return initialData;
    }

    /**
     * Sets the initial earthquake data.
     *
     * @param initialData An ObservableList of Earthquake objects representing the initial data.
     */
    public static void setInitialData(ObservableList<Earthquake> initialData) {
        database.initialData = initialData;
    }

    /**
     * The initial earthquake data.
     */
    private static ObservableList<Earthquake> initialData = FXCollections.observableArrayList();

    /**
     * The filtered earthquake data based on applied filters.
     */
    private static FilteredList<Earthquake> filteredData = new FilteredList<>(initialData);

    /**
     * The filter predicate for filtering earthquakes after a certain date.
     */
    private static Predicate<Earthquake> filterAfterDate = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {
            return true;
        }
    };

    /**
     * The filter predicate for filtering earthquakes before a certain date.
     */
    private static Predicate<Earthquake> filterBeforeDate = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {
            return true;
        }
    };

    /**
     * The filter predicate for filtering earthquakes by minimum intensity.
     */
    private static Predicate<Earthquake> filterByMinIntensity  = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {
            return true;
        }
    };

    /**
     * The filter predicate for filtering earthquakes by maximum intensity.
     */
    private static Predicate<Earthquake> filterByMaxIntensity  = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {
            return true;
        }
    };

    /**
     * The header names of the earthquake data CSV file.
     */
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

    /**
     * Reads earthquake data from a CSV file and populates the initialData list.
     *
     * @param csvFile The CSV file containing earthquake data.
     */
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

    /**
     * Gets the filtered earthquake data.
     *
     * @return A FilteredList of Earthquake objects representing the filtered data.
     */
    public static FilteredList<Earthquake> getFilteredData() {
        return filteredData;
    }

    /**
     * Sets the filtered earthquake data.
     *
     * @param filteredData A FilteredList of Earthquake objects representing the filtered data.
     */
    public static void setFilteredData(FilteredList<Earthquake> filteredData) {
        database.filteredData = filteredData;
    }

    /**
     * Gets the predicate for filtering earthquakes after a certain date.
     *
     * @return The Predicate for filtering earthquakes after a certain date.
     */
    public static Predicate<Earthquake> getFilterAfterDate() {
        return filterAfterDate;
    }

    /**
     * Sets the predicate for filtering earthquakes after a certain date.
     *
     * @param filterAfterDate The Predicate for filtering earthquakes after a certain date.
     */
    public static void setFilterAfterDate(Predicate<Earthquake> filterAfterDate) {
        database.filterAfterDate = filterAfterDate;
    }

    /**
     * Gets the predicate for filtering earthquakes before a certain date.
     *
     * @return The Predicate for filtering earthquakes before a certain date.
     */
    public static Predicate<Earthquake> getFilterBeforeDate() {
        return filterBeforeDate;
    }

    /**
     * Sets the predicate for filtering earthquakes before a certain date.
     *
     * @param filterBeforeDate The Predicate for filtering earthquakes before a certain date.
     */
    public static void setFilterBeforeDate(Predicate<Earthquake> filterBeforeDate) {
        database.filterBeforeDate = filterBeforeDate;
    }

    /**
     * Gets the predicate for filtering earthquakes by minimum intensity.
     *
     * @return The Predicate for filtering earthquakes by minimum intensity.
     */
    public static Predicate<Earthquake> getFilterByMinIntensity() {
        return filterByMinIntensity;
    }

    /**
     * Sets the predicate for filtering earthquakes by minimum intensity.
     *
     * @param filterByMinIntensity The Predicate for filtering earthquakes by minimum intensity.
     */
    public static void setFilterByMinIntensity(Predicate<Earthquake> filterByMinIntensity) {
        database.filterByMinIntensity = filterByMinIntensity;
    }

    /**
     * Gets the predicate for filtering earthquakes by maximum intensity.
     *
     * @return The Predicate for filtering earthquakes by maximum intensity.
     */
    public static Predicate<Earthquake> getFilterByMaxIntensity() {
        return filterByMaxIntensity;
    }

    /**
     * Sets the predicate for filtering earthquakes by maximum intensity.
     *
     * @param filterByMaxIntensity The Predicate for filtering earthquakes by maximum intensity.
     */
    public static void setFilterByMaxIntensity(Predicate<Earthquake> filterByMaxIntensity) {
        database.filterByMaxIntensity = filterByMaxIntensity;
    }

    /**
     * Gets the combined predicate of all filter conditions.
     *
     * @return The Predicate representing the combination of all filter conditions.
     */
    public static Predicate<Earthquake> getAllPredicates() {
        return getFilterAfterDate().and(getFilterBeforeDate()).and(getFilterByMaxIntensity()).and(getFilterByMinIntensity());
    }
}
