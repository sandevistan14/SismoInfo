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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/home.fxml"));
            Parent homeRoot = fxmlLoader.load();
            Scene scene = new Scene(homeRoot,WINDOW_WIDTH,WINDOW_HEIGHT);
            stage.setScene(scene);
        } else if (menuText.equals("Carte")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/map.fxml"));
            Parent mapRoot = fxmlLoader.load();
            Scene scene = new Scene(mapRoot,WINDOW_WIDTH,WINDOW_HEIGHT);
            stage.setScene(scene);
        } else if (menuText.equals("Dashboard")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/dashboard.fxml"));
            Parent dashboardRoot = fxmlLoader.load();
            Scene scene = new Scene(dashboardRoot,WINDOW_WIDTH,WINDOW_HEIGHT);
            stage.setScene(scene);
        }
        stage.show();
    }



    @FXML
    TableView<Earthquake> Tableview = new TableView<Earthquake>();

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
        ArrayList<Earthquake> filteredData = new ArrayList<>(database.getInitialData());
        AddInGrapheLineChart(filteredData);
        AddInGraphePieChart(filteredData);
        AddInGrapheBarChart(filteredData);
        AddInGrapheScatterChart(filteredData);
        AddInGrapheBarChart2(filteredData);
        AddInTableView(filteredData);
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
