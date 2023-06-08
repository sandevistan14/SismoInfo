package com.g4d.sismoinfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.csv.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    public static void main(String[] args) {
        // List<Earthquake> earthquakes = loadEarthquakesFromCSV("/amuhome/l21207728/Downloads/SisFrance_seismes_20230604151458.csv");

        // // Use the retrieved data as desired
        // for (Earthquake earthquake : earthquakes) {
        //     System.out.println(earthquake.getName() + " - " + earthquake.getDate());
        // }
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/g4d/sismoinfo/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Recherche sismiques");
        stage.setScene(scene);
        stage.show();
    }

    // private static List<Earthquake> loadEarthquakesFromCSV(String filePath) {
    //     List<Earthquake> earthquakes = new ArrayList<>();

    //     try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    //         String line;
    //         boolean firstLine = true; // To ignore the CSV file header

    //         while ((line = br.readLine()) != null) {
    //             if (firstLine) {
    //                 firstLine = false;
    //                 continue;
    //             }

    //             String[] data = line.split(";");

    //             String identifier = data[0];
    //             String date = data[1];
    //             String time = data[2];
    //             String name = data[3];
    //             String epicentralRegion = data[4];
    //             String shock = data[5];
    //             String xRGF93L93 = data[6];
    //             String yRGF93L93 = data[7];
    //             String latitudeWGS84 = data[8];
    //             String longitudeWGS84 = data[9];
    //             String epicentralIntensity = data[10];
    //             String epicentralIntensityQuality = data[11];

    //             Earthquake earthquake = new Earthquake(identifier, date, time, name, epicentralRegion, shock,
    //                     xRGF93L93, yRGF93L93, latitudeWGS84, longitudeWGS84, epicentralIntensity,
    //                     epicentralIntensityQuality);
    //             earthquakes.add(earthquake);
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     return earthquakes;
    // }
}
