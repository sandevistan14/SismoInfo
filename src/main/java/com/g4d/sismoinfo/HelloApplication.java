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

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void HelloApplication(String[] args) {
        List<Earthquake> earthquakes = loadEarthquakesFromCSV("/amuhome/l21207728/Téléchargements/SisFrance_seismes_20230604151458.csv");

        // Utilisez les données récupérées comme vous le souhaitez
        for (Earthquake earthquake : earthquakes) {
            System.out.println(earthquake.getNom() + " - " + earthquake.getDate());
        }
    }

    private static List<Earthquake> loadEarthquakesFromCSV(String filePath) {
        List<Earthquake> earthquakes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true; // Pour ignorer l'en-tête du fichier CSV

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(";");

                String identifiant = data[0];
                String date = data[1];
                String heure = data[2];
                String nom = data[3];
                String regionEpicentrale = data[4];
                String choc = data[5];
                String xRGF93L93 = data[6];
                String yRGF93L93 = data[7];
                String latitudeWGS84 = data[8];
                String longitudeWGS84 = data[9];
                String intensiteEpicentrale = data[10];
                String qualiteIntensiteEpicentrale = data[11];

                Earthquake earthquake = new Earthquake(identifiant, date, heure, nom, regionEpicentrale, choc,
                        xRGF93L93, yRGF93L93, latitudeWGS84, longitudeWGS84, intensiteEpicentrale,
                        qualiteIntensiteEpicentrale);
                earthquakes.add(earthquake);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return earthquakes;
    }
}