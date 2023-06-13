package com.g4d.sismoinfo.model.earthquakedata;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
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
import java.util.stream.Collectors;

/**
 * The `database` class manages earthquake data in a JavaFX application.
 */
public class database {

    private static int NUMBER_OF_KILOMETERS_IN_LATITUDE = 111;

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

    public static ObservableList<String> allRegions = FXCollections.emptyObservableList();

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
     * The filter predicate for filtering earthquakes by a Latitude around a certain radius.
     */
    private static Predicate<Earthquake> filterByLatitude  = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {

            return true;
        }
    };

    /**
     * The filter predicate for filtering earthquakes by a Longitude around a certain radius.
     */
    private static Predicate<Earthquake> filterByLongitude  = new Predicate<Earthquake>() {
        @Override
        public boolean test(Earthquake earthquake) {

            return true;
        }
    };

    /**
     * The filter predicate for filtering earthquakes by a selected region.
     */
    private static Predicate<Earthquake> filterByRegion  = new Predicate<Earthquake>() {
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
    public static boolean readCSV(File csvFile) {
        if (csvFile == null) {return false;}
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
            allRegions = initialData.stream()
                    .map(Earthquake::getEpicentralRegion)
                    .distinct()
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            allRegions.add(0,"");
            return true;
        }
        return false;
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
     * Obtient le prédicat de filtre par latitude actuellement utilisé.
     *
     * @return Le prédicat de filtre par latitude actuellement utilisé.
     */
    public static Predicate<Earthquake> getFilterByLatitude() {

        return filterByLatitude;
    }

    /**
     * Définit le prédicat de filtre par latitude.
     *
     * @param filterByLatitude Le prédicat de filtre par latitude à définir.
     */
    public static void setFilterByLatitude(Predicate<Earthquake> filterByLatitude) {
        database.filterByLatitude = filterByLatitude;
    }

    /**
     * Génère un prédicat de filtre par latitude en fonction des valeurs de latitude et de rayon spécifiées.
     *
     * @param latitudeText Le texte représentant la latitude.
     * @param radiusText   Le texte représentant le rayon.
     * @return Le prédicat de filtre par latitude généré.
     */
    public static Predicate<Earthquake> generateFiltersLatitude(String latitudeText,String radiusText){
        return new Predicate<Earthquake>() {
            @Override
            public boolean test(Earthquake earthquake) {
                double radius = Double.parseDouble(radiusText);
                double latitude = Double.parseDouble(latitudeText);
                return earthquake.getLatitude() >= latitude-(radius/NUMBER_OF_KILOMETERS_IN_LATITUDE) && earthquake.getLatitude() <= latitude+(radius/NUMBER_OF_KILOMETERS_IN_LATITUDE);
            }
        };
    }

    /**
     * Génère un prédicat de filtre par longitude en fonction des valeurs de longitude et de rayon spécifiées.
     *
     * @param longitudeText Le texte représentant la longitude.
     * @param radiusText    Le texte représentant le rayon.
     * @return Le prédicat de filtre par longitude généré.
     */
    public static Predicate<Earthquake> generateFiltersLongitude(String longitudeText,String radiusText){
        return new Predicate<Earthquake>() {
            @Override
            public boolean test(Earthquake earthquake) {
                double radius = Double.parseDouble(radiusText);
                double longitude = Double.parseDouble(longitudeText);
                return earthquake.getLongitude() >= longitude-(radius/111) && earthquake.getLatitude() <= longitude+(radius/111);
            }
        };
    }

    /**
     * Obtient le prédicat de filtre par longitude actuellement utilisé.
     *
     * @return Le prédicat de filtre par longitude actuellement utilisé.
     */
    public static Predicate<Earthquake> getFilterByLongitude() {

        return filterByLongitude;
    }

    /**
     * Définit le prédicat de filtre par longitude.
     *
     * @param filterByLongitude Le prédicat de filtre par longitude à définir.
     */
    public static void setFilterByLongitude(Predicate<Earthquake> filterByLongitude) {
        database.filterByLongitude = filterByLongitude;
    }

    /**
     * Obtient le prédicat de filtre par région actuellement utilisé.
     *
     * @return Le prédicat de filtre par région actuellement utilisé.
     */
    public static Predicate<Earthquake> getFilterByRegion() {

        return filterByRegion;
    }

    /**
     * Définit le prédicat de filtre par région.
     *
     * @param filterByRegion Le prédicat de filtre par région à définir.
     */
    public static void setFilterByRegion(Predicate<Earthquake> filterByRegion) {
        database.filterByRegion = filterByRegion;
    }

    /**
     * Gets the combined predicate of all filter conditions.
     *
     * @return The Predicate representing the combination of all filter conditions.
     */
    public static Predicate<Earthquake> getAllPredicates() {
        return getFilterAfterDate().and(getFilterBeforeDate()).and(getFilterByMaxIntensity()).and(getFilterByMinIntensity()).and(getFilterByLatitude()).and(getFilterByLongitude()).and(getFilterByRegion());
    }
}
