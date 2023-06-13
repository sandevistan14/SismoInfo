package com.g4d.sismoinfo.view;

import java.io.IOException;
import java.time.LocalTime;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This class handles the Dashboard interface of the earthquake data application.
 * It is responsible for loading the earthquake data, displaying them in different types of charts and table view.
 */
public class DashboardController {

    /**
     * Handles the action event to change the view.
     *
     * @param event the action event to be handled.
     */
    @FXML
    private void handleViewChange(ActionEvent event) {
        ViewLoaders.loadView(event);
    }

    /**
     * Loads the filtered data into the table view for display.
     *
     * @param filteredData The filtered earthquake data.
     */
    @FXML
    TableView<Earthquake> Tableview = new TableView<Earthquake>();

    /**
     * Adds the specified earthquake data to the table view.
     *
     * @param filteredData the earthquake data to be added to the table view.
     */
    public void AddInTableView(ArrayList<Earthquake> filteredData) {
        ObservableList<Earthquake> teamMembers = FXCollections.observableArrayList(filteredData);
        Tableview.setItems(teamMembers);

        TableColumn<Earthquake,Integer> Column1 = new TableColumn<Earthquake,Integer>("id");
        Column1.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn<Earthquake,Double> Column2 = new TableColumn<Earthquake,Double>("Date");
        Column2.setCellValueFactory(new PropertyValueFactory("Date"));

        TableColumn<Earthquake,Double> Column3 = new TableColumn<Earthquake,Double>("time");
        Column3.setCellValueFactory(new PropertyValueFactory("time"));

        TableColumn<Earthquake,Double> Column4 = new TableColumn<Earthquake,Double>("localisation");
        Column4.setCellValueFactory(new PropertyValueFactory("localisation"));

        TableColumn<Earthquake,Double> Column5 = new TableColumn<Earthquake,Double>("epicentralRegion");
        Column5.setCellValueFactory(new PropertyValueFactory("epicentralRegion"));

        TableColumn<Earthquake,Double> Column6 = new TableColumn<Earthquake,Double>("shock");
        Column6.setCellValueFactory(new PropertyValueFactory("shock"));

        TableColumn<Earthquake,Double> Column11 = new TableColumn<Earthquake,Double>("epicentralIntensity");
        Column11.setCellValueFactory(new PropertyValueFactory("epicentralIntensity"));

        TableColumn<Earthquake,Double> Column12 = new TableColumn<Earthquake,Double>("epicentralIntensityQuality");
        Column12.setCellValueFactory(new PropertyValueFactory("epicentralIntensityQuality"));


        Tableview.getColumns().setAll(Column1, Column2, Column3, Column4, Column5, Column6, Column11, Column12);


        for (Earthquake earthquake : filteredData) {
            Tableview.getItems().add(earthquake);
        }
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
    BarChart GrapheBarChart2;

    Map<String, Double> DicoDataBar2 = new LinkedHashMap<>();
    ObservableList< XYChart.Data<String, Number> > DataGrapheBarChart2 = FXCollections.observableArrayList();
    XYChart.Series<String, Number> SeriesGrapheBarChart2 = new XYChart.Series<>(DataGrapheBarChart2);

    /**
     * Initializes the Pie Graph data.
     */
    @FXML
    public void InitPieGraphData(){
        DicoDataPie.put("A",0);
        DicoDataPie.put("B",0);
        DicoDataPie.put("C",0);
        DicoDataPie.put("K",0);
        DicoDataPie.put("E",0);
        DicoDataPie.put("I",0);
    }

    /**
     * Adds the specified earthquake data to the line chart.
     *
     * @param filteredData the earthquake data to be added to the line chart.
     */
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

    /**
     * Adds the specified earthquake data to the pie chart.
     *
     * @param filteredData the earthquake data to be added to the pie chart.
     */
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

    /**
     * Adds the specified earthquake data to the bar chart.
     *
     * @param filteredData the earthquake data to be added to the bar chart.
     */
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

    /**
     * Adds the specified earthquake data to the second bar chart.
     *
     * @param filteredData the earthquake data to be added to the second bar chart.
     */
    public void AddInGrapheBarChart2(ArrayList<Earthquake> filteredData){
        DicoDataBar2.put(filteredData.get(0).getEpicentralIntensityQuality().getValue(),filteredData.get(0).getEpicentralIntensity());
        for(int i = 0;i < filteredData.size();++i){
            boolean lock = false;
            for(Map.Entry<String, Double> entry : DicoDataBar2.entrySet()) {
                if(filteredData.get(i).getEpicentralIntensityQuality().getValue().equals(entry.getKey())){
                    entry.setValue((entry.getValue() + filteredData.get(i).getEpicentralIntensity())/2);
                    lock = true;
                }
            }
            if(lock == false){
                DicoDataBar2.put(filteredData.get(i).getEpicentralIntensityQuality().getValue(),filteredData.get(i).getEpicentralIntensity());
            }
        }


        for(Map.Entry<String, Double> entry : DicoDataBar2.entrySet()) {
            XYChart.Data donne = new XYChart.Data(entry.getKey(), entry.getValue());
            DataGrapheBarChart2.add(donne);
        }
        GrapheBarChart2.getData().add(SeriesGrapheBarChart2);
    }

    /**
     * Adds the specified earthquake data to the scatter chart.
     *
     * @param filteredData the earthquake data to be added to the scatter chart.
     */
    public void AddInGrapheScatterChart(ArrayList<Earthquake> filteredData){
        for (Earthquake earthquake : filteredData) {
            SeriesGrapheScatterChart.getData().add(new XYChart.Data<>(String.valueOf(earthquake.getDate().getYear()), earthquake.getEpicentralIntensity()));
        }
        GrapheScatterChart.getData().add(SeriesGrapheScatterChart);
    }

    boolean test = false;

    /**
     * Initializes the chart with the filtered earthquake data.
     */
    @FXML
    public void InitGraphe(){
        clearGraphe();
        ArrayList<Earthquake> filteredData = new ArrayList<>(database.getFilteredData());
        if(filteredData.isEmpty()){
        }else{
            AddInGrapheLineChart(filteredData);
            AddInGraphePieChart(filteredData);
            AddInGrapheBarChart(filteredData);
            AddInGrapheScatterChart(filteredData);
            AddInGrapheBarChart2(filteredData);
            AddInTableView(filteredData);
        }
    }

    /**
     * Clears the charts and table view.
     */
    public void clearGraphe(){
        DataGrapheLineChart.clear();
        GrapheLineChart.getData().clear();

        DataGraphePieChart.clear();
        GraphePieChart.getData().clear();

        DataGrapheBarChart.clear();
        GrapheBarChart.getData().clear();

        DataGrapheBarChart2.clear();
        GrapheBarChart2.getData().clear();

        DicoDataLine.clear();
        DataGrapheLineChart.clear();
        SeriesGrapheLineChart.getData().clear();

        DicoDataBar.clear();
        DataGrapheBarChart.clear();
        SeriesGrapheBarChart.getData().clear();

        SeriesGrapheScatterChart.getData().clear();
        GrapheScatterChart.getData().clear();

        Tableview.getItems().clear();
    }

    /**
     * Sorts the specified dictionary.
     *
     * @param dico the dictionary to be sorted.
     * @return the sorted dictionary.
     */
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
