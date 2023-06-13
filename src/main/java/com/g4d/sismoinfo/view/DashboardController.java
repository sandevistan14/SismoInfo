package com.g4d.sismoinfo.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.g4d.sismoinfo.model.earthquakedata.Earthquake;
import com.g4d.sismoinfo.model.earthquakedata.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.MenuItem;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DashboardController {
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
            Scene scene = ViewLoaders.getHomeView();
            stage.setScene(scene);
        } else if (menuText.equals("Carte")) {
//            Scene scene = ViewLoaders.getMapView();
//            stage.setScene(scene);
        } else if (menuText.equals("Dashboard")) {
            Scene scene = ViewLoaders.getDashboardView();
            stage.setScene(scene);
        }
        stage.show();
    }




    Map<String, Integer> DicoDataLine = new LinkedHashMap<>();

    ObservableList< XYChart.Data<String, Number> > DataGrapheLineChart = FXCollections.observableArrayList();
    XYChart.Series<String, Number> SeriesGrapheLineChart = new XYChart.Series<>(DataGrapheLineChart);

    Map<String, Integer> DicoDataPie = new LinkedHashMap<>(5);
    ObservableList<PieChart.Data> DataGraphePieChart = FXCollections.observableArrayList();

    Map<String, Integer> DicoDataBar = new LinkedHashMap<>();
    ObservableList< XYChart.Data<String, Number> > DataGrapheBarChart = FXCollections.observableArrayList();
    XYChart.Series<String, Number> SeriesGrapheBarChart = new XYChart.Series<>(DataGrapheBarChart);

    XYChart.Series SeriesGrapheScatterChart = new XYChart.Series();

    @FXML
    LineChart GrapheLineChart;
    @FXML
    PieChart GraphePieChart = new PieChart();
    @FXML
    BarChart GrapheBarChart;
    @FXML
    ScatterChart GrapheScatterChart;

    @FXML
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
        InitPieGraphData();
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

    boolean test = false;

    @FXML
    public void InitGraphe(){
        clearGraphe();
        ArrayList<Earthquake> filteredData = new ArrayList<>(database.getFilteredData());
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

        SeriesGrapheScatterChart.getData().clear();
        GrapheScatterChart.getData().clear();
    }

    public static Map<String, Integer> SortDico(Map<String, Integer> dico){

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(dico.entrySet());

        entries.sort(Map.Entry.comparingByKey());
        Map<String, Integer> sortedDico = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedDico.put(entry.getKey(), entry.getValue());
        }

        return sortedDico;
    }
}
