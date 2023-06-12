package com.g4d.sismoinfo.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.g4d.sismoinfo.model.earthquakedata.Earthquake;
import com.g4d.sismoinfo.model.earthquakedata.database;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.RangeSlider;
import javafx.stage.Screen;

public class HomeController  extends GridPane implements Initializable {
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    private final double WINDOW_WIDTH = bounds.getWidth(); // Largeur de la fenêtre
    private final double WINDOW_HEIGHT = bounds.getHeight()-20; // Hauteur de la fenêtre
    private final String[] regionsFrance = {"Auvergne-Rhône-Alpes", "Bourgogne-Franche-Comté", "Bretagne", "Centre-Val de Loire", "Corse", "Grand Est", "Hauts-de-France", "Île-de-France", "Normandie", "Nouvelle-Aquitaine", "Occitanie", "Pays de la Loire", "Provence-Alpes-Côte d'Azur"};
    private boolean fileInsert = false;

    @FXML
    private TextField longitudeTextField;

    @FXML
    private TextField latitudeTextField;

    @FXML
    private TextField radiusTextField;

    @FXML
    DatePicker after;

    @FXML
    DatePicker before;

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
    }




    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private HBox imageContainer;

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        choiceBox.setItems(FXCollections.observableArrayList(regionsFrance));
        choiceBox.setOnAction(this::handleChoiceBoxAction);

        longitudeTextField.setOnAction(this::handleTextFieldAction);
        latitudeTextField.setOnAction(this::handleTextFieldAction);
        radiusTextField.setOnAction(this::handleTextFieldAction);
    }

    @FXML
    private void handleTextFieldAction(ActionEvent event) {
        String longitude = longitudeTextField.getText();
        String latitude = latitudeTextField.getText();
        String radius = radiusTextField.getText();

        // Do something with the retrieved values
        System.out.println("Longitude: " + longitude);
        System.out.println("Latitude: " + latitude);
        System.out.println("Rayon: " + radius);
    }


    @FXML
    protected void DateAction (ActionEvent event){
        DatePicker sourceOfEvent = (DatePicker) event.getSource();
        System.out.println(sourceOfEvent.getValue());
    }

    @FXML
    private void handleChoiceBoxAction(ActionEvent event) {
        String selectedRegion = choiceBox.getValue();
        System.out.println("Région sélectionnée : " + selectedRegion);
    }

    @FXML
    private void handleInsertAction(ActionEvent event) {
        fileInsert = true;

        // Mettre à jour l'image en "valid.png"
        String imagePath = "file:/Users/maxime/Documents/SismoInfo/src/main/resources/com/g4d/sismoinfo/valid.png";
        Image image = new Image(imagePath);
        imageView.setImage(image);

        // Définir la taille de l'image en utilisant une règle CSS
        imageView.setFitWidth(24); // Remplacez la valeur par la largeur souhaitée
        imageView.setFitHeight(24); // Remplacez la valeur par la hauteur souhaitée
        imageView.setPreserveRatio(true); // Conserver le ratio d'aspect de l'image
    }


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
    private void handleRechercherAction(ActionEvent event) throws IOException {
        if (fileInsert) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/map.fxml"));
            Parent mapRoot = fxmlLoader.load();
            Scene mapScene = new Scene(mapRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
            stage.setScene(mapScene);
            stage.show();
        }
    }
}
