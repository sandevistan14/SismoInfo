package com.g4d.sismoinfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.csv.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H' h'[' 'm' min']");

        String heure1 = "18 h";
        String heure2 = "6 h 50 min";
        String heure3 = "";
        String date1 = "2021/05/12";

        // Conversion des chaînes d'heures en LocalTime
        LocalTime localTime1 = dataFormatting.timeParsing(heure1);
        LocalTime localTime2 = dataFormatting.timeParsing(heure2);
        LocalTime localTime3 = dataFormatting.timeParsing(heure3);
        LocalDate date = dataFormatting.dateParsing(date1);

        // Affichage des heures converties
        System.out.println(localTime1);
        System.out.println(localTime2);
        System.out.println(localTime3);
        System.out.println(date);
        //        List<Earthquake> earthquakes = loadEarthquakesFromCSV("/amuhome/l21207728/Downloads/SisFrance_seismes_20230604151458.csv");
//
//        // Use the retrieved data as desired
//        for (Earthquake earthquake : earthquakes) {
//            System.out.println(earthquake.getName() + " - " + earthquake.getDate());
//        }
    }

//    private static List<Earthquake> loadEarthquakesFromCSV(String filePath) {
//        List<Earthquake> earthquakes = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            boolean firstLine = true; // To ignore the CSV file header
//
//            while ((line = br.readLine()) != null) {
//                if (firstLine) {
//                    firstLine = false;
//                    continue;
//                }
//
//                String[] data = line.split(";");
//
//                String identifier = data[0];
//                String date = data[1];
//                String time = data[2];
//                String name = data[3];
//                String epicentralRegion = data[4];
//                String shock = data[5];
//                String xRGF93L93 = data[6];
//                String yRGF93L93 = data[7];
//                String latitudeWGS84 = data[8];
//                String longitudeWGS84 = data[9];
//                String epicentralIntensity = data[10];
//                String epicentralIntensityQuality = data[11];
//
//                Earthquake earthquake = new Earthquake(identifier, date, time, name, epicentralRegion, shock,
//                        xRGF93L93, yRGF93L93, latitudeWGS84, longitudeWGS84, epicentralIntensity,
//                        epicentralIntensityQuality);
//                earthquakes.add(earthquake);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return earthquakes;
    }
