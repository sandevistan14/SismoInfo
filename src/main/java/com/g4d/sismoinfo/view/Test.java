package com.g4d.sismoinfo.view;

import com.g4d.sismoinfo.model.earthquakedata.Earthquake;
import com.g4d.sismoinfo.model.earthquakedata.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.spreadsheet.GridBase;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Test extends GridPane implements Initializable {
    FileChooser csvFileChooser = new FileChooser();
    File csvFile;

    @FXML
    RangeSlider epicentralIntensitySlider;

    @FXML
    Label min;
    @FXML
    Label max;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        csvFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        epicentralIntensitySlider.setLowValue(2);
        epicentralIntensitySlider.setHighValue(12);
        epicentralIntensitySlider.lowValueProperty().addListener((obs, oldval, newVal) ->
                epicentralIntensitySlider.setLowValue(epicentralIntensitySlider.lowValueProperty().intValue()));
        epicentralIntensitySlider.highValueProperty().addListener((obs, oldval, newVal) ->
                epicentralIntensitySlider.setHighValue(epicentralIntensitySlider.highValueProperty().intValue()));
        min.textProperty().bind(epicentralIntensitySlider.lowValueProperty().asString());
        max.textProperty().bind(epicentralIntensitySlider.highValueProperty().asString());

    }

    @FXML
    protected void csvButtonAction (ActionEvent event){
        Button sourceOfEvent = (Button) event.getSource();
        csvFile = csvFileChooser.showOpenDialog(sourceOfEvent.getScene().getWindow());
        database.readCSV(csvFile);
        for (Earthquake quake : database.initialData){

        };
    }

    @FXML
    protected void DateAction (ActionEvent event){
        DatePicker sourceOfEvent = (DatePicker) event.getSource();
        System.out.println(sourceOfEvent.getValue());
    }


}