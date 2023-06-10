package com.g4d.sismoinfo.view;

import com.g4d.sismoinfo.model.earthquakedata.Earthquake;
import com.g4d.sismoinfo.model.earthquakedata.database;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableSetValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
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
import java.util.*;
import java.util.function.Predicate;

public class Test extends GridPane implements Initializable {
    FileChooser csvFileChooser = new FileChooser();
    File csvFile;

    @FXML
    RangeSlider epicentralIntensitySlider;

    @FXML
    DatePicker after;

    @FXML
    DatePicker before;

    @FXML
    Label min;
    @FXML
    Label max;

    @FXML
    LineChart GrapheLineChart;
    @FXML
    PieChart GraphePieChart = new PieChart();
    Map<String, Integer> dictionary = new LinkedHashMap<>();

    ObservableList< XYChart.Data<String, Number> > DataGrapheLineChart = FXCollections.observableArrayList();
    XYChart.Series<String, Number> SeriesGrapheLineChart = new XYChart.Series<>(DataGrapheLineChart);
    Map<String, Integer> DicoDataPie = new LinkedHashMap<>(5);

    ObservableList<PieChart.Data> DataGraphePieChart = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        csvFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        epicentralIntensitySlider.setLowValue(2);
        epicentralIntensitySlider.setHighValue(12);
        after.valueProperty().addListener((observable, oldValue, newValue) -> {
            database.setFilterAfterDate(earthquake -> earthquake.getDate().isAfter(newValue));
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });
        before.valueProperty().addListener((observable, oldValue, newValue) -> {
            database.setFilterBeforeDate(earthquake -> earthquake.getDate().isBefore(newValue));
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });
        epicentralIntensitySlider.lowValueProperty().addListener((observable) -> {
            epicentralIntensitySlider.lowValueProperty().set(epicentralIntensitySlider.lowValueProperty().intValue());
            database.setFilterByMinIntensity(earthquake -> earthquake.getEpicentralIntensity() >= epicentralIntensitySlider.lowValueProperty().intValue());
            database.getFilteredData().setPredicate(database.getAllPredicates());

        });
        epicentralIntensitySlider.highValueProperty().addListener((observable) -> {
            epicentralIntensitySlider.highValueProperty().set(epicentralIntensitySlider.highValueProperty().intValue());
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });
        min.textProperty().bind(epicentralIntensitySlider.lowValueProperty().asString());
        max.textProperty().bind(epicentralIntensitySlider.highValueProperty().asString());
        DicoDataPie.put("A",0);
        DicoDataPie.put("B",0);
        DicoDataPie.put("C",0);
        DicoDataPie.put("K",0);
        DicoDataPie.put("E",0);
        DicoDataPie.put("I",0);
    }

    @FXML
    protected void csvButtonAction (ActionEvent event){
        Button sourceOfEvent = (Button) event.getSource();
        csvFile = csvFileChooser.showOpenDialog(sourceOfEvent.getScene().getWindow());
        database.readCSV(csvFile);
        for (Earthquake quake : database.getFilteredData()){
            System.out.println(quake);
        };
    }

    @FXML
    protected void DateAction (ActionEvent event){
        DatePicker sourceOfEvent = (DatePicker) event.getSource();
        System.out.println(sourceOfEvent.getValue());
    }


    public void AddInGrapheLineChart(List<Earthquake> filteredData){

        dictionary.put(Integer.toString(filteredData.get(0).getDate().getYear()),1);
        for(int i = 0;i < filteredData.size();++i){
            boolean lock = false;
            for(Map.Entry<String, Integer> entry : dictionary.entrySet()) {
                if(Integer.toString(filteredData.get(i).getDate().getYear()).equals(entry.getKey())){
                    entry.setValue(entry.getValue()+1);
                    lock = true;
                }
            }
            if(lock == false){
                dictionary.put(Integer.toString(filteredData.get(i).getDate().getYear()),1);
            }
        }

        for(Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            XYChart.Data donne = new XYChart.Data(entry.getKey(), entry.getValue());
            DataGrapheLineChart.add(donne);
        }
        GrapheLineChart.getData().add(SeriesGrapheLineChart);
    }

    @FXML
    public void testo(){
        AddInGrapheLineChart(database.getFilteredData());
        AddInGraphePieChart(database.getFilteredData());

    }

    @FXML
    public void showFilteredList(){
        for (Earthquake quake : database.getFilteredData()){
            System.out.println(quake);
        };
    }

    public void AddInGraphePieChart(List<Earthquake> filteredData){
        for(int i = 0;i < filteredData.size();++i){
            for(Map.Entry<String, Integer> entry : DicoDataPie.entrySet()) {
                if(filteredData.get(i).getEpicentralIntensityQuality().name().equals(entry.getKey())){
                    entry.setValue(entry.getValue()+1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : DicoDataPie.entrySet()) {
            DataGraphePieChart.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        GraphePieChart.setData(DataGraphePieChart);
    }


}