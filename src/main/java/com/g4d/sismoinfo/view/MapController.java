package com.g4d.sismoinfo.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import com.g4d.sismoinfo.model.earthquakedata.Earthquake;


public class MapController implements Initializable {
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    private final double WINDOW_WIDTH = bounds.getWidth(); // Largeur de la fenêtre
    private final double WINDOW_HEIGHT = bounds.getHeight() - 20; // Hauteur de la fenêtre

    private List<String> filteredDataList;

    @FXML
    VBox ZoneMap;

    @FXML
    private void handleMenuAction(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        String menuText = menuItem.getText();

        Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();

        if (menuText.equals("Accueil")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/home.fxml"));
            Parent homeRoot = fxmlLoader.load();
            Scene scene = new Scene(homeRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
            stage.setScene(scene);
        } else if (menuText.equals("Carte")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/map.fxml"));
            Parent mapRoot = fxmlLoader.load();
            Scene scene = new Scene(mapRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
            stage.setScene(scene);
        } else if (menuText.equals("Dashboard")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/dashboard.fxml"));
            Parent dashboardRoot = fxmlLoader.load();
            Scene scene = new Scene(dashboardRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
            stage.setScene(scene);
        }
        stage.show();
    }

    private MapView mapView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.setProperty("javafx.platform", "desktop");

        mapView = new MapView();  // Initialize the MapView
        mapView.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        MapPoint centre = new MapPoint(46.227638, 2.213749);
        mapView.setZoom(5);
        mapView.flyTo(0, centre, 0.1);
        ZoneMap.getChildren().add(mapView);
    }

    public void setFilteredDataList(List<String> filteredDataList) {
        this.filteredDataList = filteredDataList;
    }

    public Color getColorForMagnitude(double magnitude) {
        Color color;

        if (magnitude < 2.0) {
            color = Color.WHITE;
        } else if (magnitude < 3.0) {
            color = Color.DARKGREEN;
        } else if (magnitude < 4.0) {
            color = Color.LIGHTGREEN;
        } else if (magnitude < 5.0) {
            color = Color.YELLOW;
        } else if (magnitude < 6.0) {
            color = Color.DARKGOLDENROD;
        } else if (magnitude < 7.0) {
            color = Color.ORANGE;
        } else if (magnitude < 8.0) {
            color = Color.LIGHTCORAL;
        } else if (magnitude < 9.0) {
            color = Color.RED;
        } else {
            color = Color.DARKRED;
        }

        return color;
    }
/*
    private double getMagnitudeForCoordinates(double latitude, double longitude, List<Earthquake> earthquakeData) {
        for (Earthquake earthquake : earthquakeData) {
            double earthquakeLatitude = earthquake.getLatitude();
            double earthquakeLongitude = earthquake.getLongitude();

            // Assuming the latitude and longitude values are doubles, you can use a small delta for comparison
            double delta = 0.0001; // Adjust the delta value based on your data precision

            // Check if the latitude and longitude values are within a small delta of the earthquake coordinates
            if (Math.abs(latitude - earthquakeLatitude) < delta && Math.abs(longitude - earthquakeLongitude) < delta) {
                return earthquake.getEpicentralIntensity();
            }
        }
        return 0.0; // or any default value if no match is found
    }
*/
    public static double calculateDistance(double filterLat, double filterLon, double quakeLat, double quakeLon) {
        int earthRadius = 6371;

        double latDistance = Math.toRadians(quakeLat - filterLat);
        double lonDistance = Math.toRadians(quakeLon - filterLon);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(filterLat)) * Math.cos(Math.toRadians(quakeLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        System.out.println(earthRadius * c);
        return earthRadius * c; // distance in kilometers

    }

    public List<CoordinateWithMagnitude> filterEarthquakesByDistance(List<Earthquake> earthquakes) {
        String[] data = filteredDataList.get(0).split(",");
        double filterLat = Double.parseDouble(data[0]);
        double filterLon = Double.parseDouble(data[1]);
        double filterRadius = Double.parseDouble(data[2]);

        List<CoordinateWithMagnitude> filteredEarthquakes = new ArrayList<>();

        for (Earthquake earthquake : earthquakes) {
            double quakeLat = earthquake.getLatitude();
            double quakeLon = earthquake.getLongitude();

            double distance = calculateDistance(filterLat, filterLon, quakeLat, quakeLon);
            if (distance <= filterRadius) {
                double magnitude = earthquake.getEpicentralIntensity();
                CoordinateWithMagnitude coordinate = new CoordinateWithMagnitude(quakeLat, quakeLon, magnitude);
                filteredEarthquakes.add(coordinate);
                System.out.println(coordinate);
            }
        }

        return filteredEarthquakes;
    }

    public void updateMap(List<Earthquake> earthquakes) {
        List<CoordinateWithMagnitude> filteredCoordinates = filterEarthquakesByDistance(earthquakes);

        for (CoordinateWithMagnitude coordinate : filteredCoordinates) {
            Color color = getColorForMagnitude(coordinate.getMagnitude());

            MapPoint mapPoint = new MapPoint(coordinate.getLatitude(), coordinate.getLongitude());
            CustomPinLayer pinLayer = new CustomPinLayer(mapPoint, color);

            System.out.println(pinLayer);
            mapView.addLayer(pinLayer);
        }
    }
}


