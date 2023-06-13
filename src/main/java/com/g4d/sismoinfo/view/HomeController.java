package com.g4d.sismoinfo.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
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
    private ChoiceBox<String> choiceBox;

    @FXML
    private HBox imageContainer;

    @FXML
    private ImageView imageView;

    @FXML
    protected void csvButtonAction (ActionEvent event){
        Button sourceOfEvent = (Button) event.getSource();
        csvFile = csvFileChooser.showOpenDialog(sourceOfEvent.getScene().getWindow());
        database.readCSV(csvFile);
        choiceBox.setItems(database.allRegions);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        csvFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        latitudeTextField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        longitudeTextField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        radiusTextField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));

        latitudeTextField.onActionProperty().set(e -> {
            if (radiusTextField.textProperty().get().isEmpty())
                database.setFilterByLatitude(database.generateFiltersLatitude(latitudeTextField.textProperty().get(),"0"));
            else
                database.setFilterByLatitude(database.generateFiltersLatitude(latitudeTextField.textProperty().get(),radiusTextField.textProperty().get()));
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });
        longitudeTextField.onActionProperty().set(e -> {
            if (radiusTextField.textProperty().get().isEmpty())
                database.setFilterByLongitude(database.generateFiltersLatitude(longitudeTextField.textProperty().get(),"0"));
            else
                database.setFilterByLongitude(database.generateFiltersLatitude(longitudeTextField.textProperty().get(),radiusTextField.textProperty().get()));
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });
        radiusTextField.onActionProperty().set(e -> {
            if (!(longitudeTextField.textProperty().get().isEmpty()))
                database.setFilterByLongitude(database.generateFiltersLatitude(longitudeTextField.textProperty().get(),radiusTextField.textProperty().get()));
            if (!(latitudeTextField.textProperty().get().isEmpty()))
                database.setFilterByLatitude(database.generateFiltersLatitude(latitudeTextField.textProperty().get(),radiusTextField.textProperty().get()));
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });

        epicentralIntensitySlider.setLowValue(2);
        epicentralIntensitySlider.setHighValue(12);
        epicentralIntensitySlider.lowValueProperty().addListener((observable) -> {
            epicentralIntensitySlider.lowValueProperty().set(epicentralIntensitySlider.lowValueProperty().intValue());
            database.setFilterByMinIntensity(earthquake -> earthquake.getEpicentralIntensity() >= epicentralIntensitySlider.lowValueProperty().intValue());
            database.getFilteredData().setPredicate(database.getAllPredicates());

        });
        epicentralIntensitySlider.highValueProperty().addListener((observable) -> {
            epicentralIntensitySlider.highValueProperty().set(epicentralIntensitySlider.highValueProperty().intValue());
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });

        choiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            database.setFilterByRegion(earthquake -> earthquake.getEpicentralRegion().contains(newValue));
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });

        after.valueProperty().addListener((observable, oldValue, newValue) -> {
            database.setFilterAfterDate(earthquake -> earthquake.getDate().isAfter(newValue));
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });
        before.valueProperty().addListener((observable, oldValue, newValue) -> {
            database.setFilterBeforeDate(earthquake -> earthquake.getDate().isBefore(newValue));
            database.getFilteredData().setPredicate(database.getAllPredicates());
        });
    }

    @FXML
    protected void DateAction (ActionEvent event){
        DatePicker sourceOfEvent = (DatePicker) event.getSource();
        System.out.println(sourceOfEvent.getValue());
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
