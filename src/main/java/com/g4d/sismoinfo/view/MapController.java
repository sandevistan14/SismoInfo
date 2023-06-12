package com.g4d.sismoinfo.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class MapController implements Initializable {
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    private final double WINDOW_WIDTH = bounds.getWidth(); // Largeur de la fenêtre
    private final double WINDOW_HEIGHT = bounds.getHeight()-20; // Hauteur de la fenêtre

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

    @FXML
    VBox ZoneMap = new VBox();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        MapView mapView = new MapView();

        MapPoint centre = new MapPoint(46.227638, 2.213749);
        mapView.flyTo(0, centre, 0.1);

        mapView.setCenter(35.91466, 10.312499);
        mapView.setZoom(5);


        /*
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



        ZoneMap.getChildren().add(mapView);
    }
}
