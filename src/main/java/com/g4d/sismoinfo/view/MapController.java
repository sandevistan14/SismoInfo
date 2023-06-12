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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.setProperty("javafx.platform", "desktop");

        MapView mapView = new MapView();
        mapView.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        MapPoint centre = new MapPoint(46.227638, 2.213749);
        mapView.setZoom(5);
        /* point when click (marche pas)
        mapView.setOnMouseClicked(e -> {
            MapPoint centerPoint = mapView.getCenter();

            double centerLat = centerPoint.getLatitude();
            double centerLon = centerPoint.getLongitude();
            double zoom = mapView.getZoom();

            double clickX = e.getX() - mapView.getWidth() / 2;
            double clickY = e.getY() - mapView.getHeight() / 2;

            double scale = Math.pow(2, zoom);

            double dLon = clickX / scale;
            double dLat = clickY / scale;

            double newLat = centerLat - dLat;
            double newLon = centerLon + dLon;

            MapPoint clickedPoint = new MapPoint(newLat, newLon);

            System.out.println("Clicked latitude: " + newLat + ", longitude: " + newLon);
            System.out.println("Zoom level: " + zoom);

            MapLayer mapLayer = new CustomPinLayer(clickedPoint);
            mapView.addLayer(mapLayer);
            mapView.flyTo(0, clickedPoint, 0.1);
        });
        */
        mapView.flyTo(0, centre, 0.1);
        ZoneMap.getChildren().add(mapView);
    }

    public void setFilteredDataList(List<String> filteredDataList) {
        this.filteredDataList = filteredDataList;
    }

    private Color getColorForMagnitude(double magnitude) {
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

    public void updateMap(List<Earthquake> earthquakeData) {
        for (String data : filteredDataList) {
            String[] coordinates = data.split(",");
            double latitude = Double.parseDouble(coordinates[0]);
            double longitude = Double.parseDouble(coordinates[1]);
            double radius = Double.parseDouble(coordinates[2]);

            int magnitude = getMagnitudeForCoordinates(latitude, longitude, earthquakeData);

            Color color = getColorForMagnitude(magnitude);

            MapLayer mapLayer = new CustomPinLayer(latitude, longitude, radius, color);

            mapView.addLayer(mapLayer);

            // Déclarez une liste pour stocker les points filtrés
            List<MapPoint> filteredPoints = new ArrayList<>();

            for (Earthquake earthquake : earthquakeData) {
                // Récupérer les coordonnées de chaque séisme
                double earthquakeLatitude = earthquake.getLatitude();
                double earthquakeLongitude = earthquake.getLongitude();

                // Calculer la distance entre les coordonnées spécifiées et les coordonnées du séisme
                double distance = calculateDistance(latitude, longitude, earthquakeLatitude, earthquakeLongitude);

                // Vérifier si la distance est dans le rayon
                if (distance <= radius) {
                    // Les coordonnées du séisme sont dans le rayon, ajoutez-les à la liste des points filtrés
                    MapPoint filteredPoint = new MapPoint(earthquakeLatitude, earthquakeLongitude);
                    filteredPoints.add(filteredPoint);
                }
            }
        }
    }
}