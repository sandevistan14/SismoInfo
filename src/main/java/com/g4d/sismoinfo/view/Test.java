package com.g4d.sismoinfo.view;

import com.g4d.sismoinfo.model.earthquakedata.Earthquake;
import com.g4d.sismoinfo.model.earthquakedata.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.spreadsheet.GridBase;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class Test extends GridPane implements Initializable {
    FileChooser csvFileChooser = new FileChooser();
    File csvFile;

    @FXML
    RangeSlider epicentralIntensitySlider;

    @FXML
    Label min;
    @FXML
    Label max;

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










        InitPieGraphData();
    }




    @FXML
    LineChart GrapheLineChart;
    @FXML
    PieChart GraphePieChart = new PieChart();
    @FXML
    BarChart GrapheBarChart;
    @FXML
    ScatterChart GrapheScatterChart;

    Map<String, Integer> DicoDataLine = new LinkedHashMap<>();

    ObservableList< XYChart.Data<String, Number> > DataGrapheLineChart = FXCollections.observableArrayList();
    XYChart.Series<String, Number> SeriesGrapheLineChart = new XYChart.Series<>(DataGrapheLineChart);

    Map<String, Integer> DicoDataPie = new LinkedHashMap<>(5);
    ObservableList<PieChart.Data> DataGraphePieChart = FXCollections.observableArrayList();

    Map<String, Integer> DicoDataBar = new LinkedHashMap<>();
    ObservableList< XYChart.Data<String, Number> > DataGrapheBarChart = FXCollections.observableArrayList();
    XYChart.Series<String, Number> SeriesGrapheBarChart = new XYChart.Series<>(DataGrapheBarChart);

    ObservableList< XYChart.Data<Number, Number> > DataGrapheScatterChart = FXCollections.observableArrayList();
    //XYChart.Series<Number, Number> SeriesGrapheScatterChart = new XYChart.Series<>(DataGrapheScatterChart);
    XYChart.Series SeriesGrapheScatterChart = new XYChart.Series();

    public void InitPieGraphData(){
        DicoDataPie.put("A",0);
        DicoDataPie.put("B",0);
        DicoDataPie.put("C",0);
        DicoDataPie.put("K",0);
        DicoDataPie.put("E",0);
        DicoDataPie.put("I",0);
    }

    public void AddInGrapheLineChart(ArrayList<Earthquake> filteredData){
        DicoDataLine.put(Integer.toString(filteredData.get(0).getDate().getYear()),1);
        for(int i = 0;i < filteredData.size();++i){
            boolean lock = false;
            for(Map.Entry<String, Integer> entry : DicoDataLine.entrySet()) {
                if(Integer.toString(filteredData.get(i).getDate().getYear()).equals(entry.getKey())){
                    entry.setValue(entry.getValue()+1);
                    lock = true;
                }
            }
            if(lock == false){
                DicoDataLine.put(Integer.toString(filteredData.get(i).getDate().getYear()),1);
            }
        }
        for(Map.Entry<String, Integer> entry : DicoDataLine.entrySet()) {
            XYChart.Data donne = new XYChart.Data(entry.getKey(), entry.getValue());
            DataGrapheLineChart.add(donne);
        }
        GrapheLineChart.getData().add(SeriesGrapheLineChart);
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

    public void AddInGrapheBarChart(ArrayList<Earthquake> filteredData){
        DicoDataBar.put(Double.toString(filteredData.get(0).getEpicentralIntensity()),1);
        for(int i = 0;i < filteredData.size();++i){
            boolean lock = false;
            for(Map.Entry<String, Integer> entry : DicoDataBar.entrySet()) {
                if(Double.toString(filteredData.get(i).getEpicentralIntensity()).equals(entry.getKey())){
                    entry.setValue(entry.getValue()+1);
                    lock = true;
                }
            }
            if(lock == false){
                DicoDataBar.put(Double.toString(filteredData.get(i).getEpicentralIntensity()),1);
            }
        }

        DicoDataBar = SortDico(DicoDataBar);

        for(Map.Entry<String, Integer> entry : DicoDataBar.entrySet()) {
            XYChart.Data donne = new XYChart.Data(entry.getKey(), entry.getValue());
            DataGrapheBarChart.add(donne);
        }
        GrapheBarChart.getData().add(SeriesGrapheBarChart);
    }

    public void AddInGrapheScatterChart(ArrayList<Earthquake> filteredData){
        for (Earthquake earthquake : filteredData) {
            SeriesGrapheScatterChart.getData().add(new XYChart.Data<>(String.valueOf(earthquake.getDate().getYear()), earthquake.getEpicentralIntensity()));
        }
        GrapheScatterChart.getData().add(SeriesGrapheScatterChart);
    }

    @FXML
    public void testo(){
        clearGraphe();
        ArrayList<Earthquake> filteredData = new ArrayList<>(database.getInitialData());
        AddInGrapheLineChart(filteredData);
        AddInGraphePieChart(filteredData);
        AddInGrapheBarChart(filteredData);
        AddInGrapheScatterChart(filteredData);
    }

    public void clearGraphe(){
        DataGrapheLineChart.clear();
        GrapheLineChart.getData().clear();

        DataGraphePieChart.clear();
        GraphePieChart.getData().clear();

        DataGrapheBarChart.clear();
        GrapheBarChart.getData().clear();

        DicoDataLine.clear();
        DataGrapheLineChart.clear();
        SeriesGrapheLineChart.getData().clear();

        DicoDataBar.clear();
        DataGrapheBarChart.clear();
        SeriesGrapheBarChart.getData().clear();
    }

    public Map<String, Integer> SortDico(Map<String, Integer> dico){

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(dico.entrySet());

        entries.sort(Map.Entry.comparingByKey());
        Map<String, Integer> sortedDico = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedDico.put(entry.getKey(), entry.getValue());
        }

        return sortedDico;
    }
}