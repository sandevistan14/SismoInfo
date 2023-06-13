package com.g4d.sismoinfo.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.g4d.sismoinfo.model.earthquakedata.Earthquake;
import com.g4d.sismoinfo.model.earthquakedata.database;
import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * This class is the controller for the Map view in the application.
 * It implements the Initializable interface for initialization logic.
 */
public class MapController implements Initializable {
    private final MapPoint FRANCE_CENTER = new MapPoint(46.539722,2.430278);
    @FXML
    private MapView mapView;
    @FXML
    private Label idLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label regionLabel;
    @FXML
    private Label intensityLabel;

    private ArrayList<MapLayer> mapLayers = new ArrayList<>();

    /**
     * This method calls the loadView method of ViewLoaders to handle the view change.
     *
     * @param event The ActionEvent object generated when a view change is triggered.
     */
    @FXML
    private void handleViewChange(ActionEvent event) {

        ViewLoaders.loadView(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapView.setZoom(6.5);
        mapView.setCenter(FRANCE_CENTER);
    }
    private void initBlankMap(){
        mapView = new MapView();
        mapView.setZoom(6.5);
        mapView.setCenter(FRANCE_CENTER);
    }

    @FXML
    private void addEarthquakes(){
        for (int i = 0; i < mapLayers.size(); ++i){
            mapView.removeLayer(mapLayers.get(i));
        }
        mapLayers.clear();
        for (int i = 0; i < database.getFilteredData().size(); ++i) {
            Earthquake earthquake = database.getFilteredData().get(i);
            mapLayers.add(new CustomLayerMap(earthquake));
            mapView.addLayer(mapLayers.get(i));
            mapLayers.get(i).setOnMouseClicked(e -> {
                idLabel.textProperty().set(Integer.toString(earthquake.getId()));
                dateLabel.textProperty().set(earthquake.getDate().toString());
                regionLabel.textProperty().set(earthquake.getEpicentralRegion());
                intensityLabel.textProperty().set(Double.toString(earthquake.getEpicentralIntensity()));
            });
        }
    }
}
