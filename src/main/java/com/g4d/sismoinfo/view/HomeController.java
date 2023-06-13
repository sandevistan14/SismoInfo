package com.g4d.sismoinfo.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.g4d.sismoinfo.model.earthquakedata.database;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.converter.DoubleStringConverter;
import org.controlsfx.control.RangeSlider;

public class HomeController  extends GridPane implements Initializable {
    @FXML
    private TextField longitudeTextField;

    @FXML
    private TextField latitudeTextField;

    @FXML
    private TextField radiusTextField;

    @FXML
    private DatePicker after;

    @FXML
    private DatePicker before;

    private FileChooser csvFileChooser = new FileChooser();
    private File csvFile;

    private BooleanProperty fileInserted = new SimpleBooleanProperty(false);


    @FXML
    private RangeSlider epicentralIntensitySlider;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private HBox imageContainer;

    @FXML
    private ImageView checkMarkView;

    @FXML
    protected void csvButtonAction (ActionEvent event){
        Button sourceOfEvent = (Button) event.getSource();
        csvFile = csvFileChooser.showOpenDialog(sourceOfEvent.getScene().getWindow());
        fileInserted.set(database.readCSV(csvFile));
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
        });
        longitudeTextField.onActionProperty().set(e -> {
            if (radiusTextField.textProperty().get().isEmpty())
                database.setFilterByLongitude(database.generateFiltersLatitude(longitudeTextField.textProperty().get(),"0"));
            else
                database.setFilterByLongitude(database.generateFiltersLatitude(longitudeTextField.textProperty().get(),radiusTextField.textProperty().get()));
        });
        radiusTextField.onActionProperty().set(e -> {
            if (!(longitudeTextField.textProperty().get().isEmpty()))
                database.setFilterByLongitude(database.generateFiltersLatitude(longitudeTextField.textProperty().get(),radiusTextField.textProperty().get()));
            if (!(latitudeTextField.textProperty().get().isEmpty()))
                database.setFilterByLatitude(database.generateFiltersLatitude(latitudeTextField.textProperty().get(),radiusTextField.textProperty().get()));
        });

        epicentralIntensitySlider.setLowValue(2);
        epicentralIntensitySlider.setHighValue(12);
        epicentralIntensitySlider.lowValueProperty().addListener((observable) -> {
            epicentralIntensitySlider.lowValueProperty().set(epicentralIntensitySlider.lowValueProperty().intValue());
            database.setFilterByMinIntensity(earthquake -> earthquake.getEpicentralIntensity() >= epicentralIntensitySlider.lowValueProperty().intValue());
        });
        epicentralIntensitySlider.highValueProperty().addListener((observable) -> {
            epicentralIntensitySlider.highValueProperty().set(epicentralIntensitySlider.highValueProperty().intValue());
        });

        choiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(""))
                database.setFilterByRegion(earthquake -> true);
            else
                database.setFilterByRegion(earthquake -> earthquake.getEpicentralRegion().contains(newValue));
        });

        after.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null)
                database.setFilterAfterDate(earthquake -> earthquake.getDate().isAfter(LocalDate.MIN));
            else
                database.setFilterAfterDate(earthquake -> earthquake.getDate().isAfter(newValue));
        });
        before.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null)
                database.setFilterBeforeDate(earthquake -> earthquake.getDate().isBefore(LocalDate.MAX));
            else
                database.setFilterBeforeDate(earthquake -> earthquake.getDate().isBefore(newValue));
        });
        checkMarkView.imageProperty().bind(Bindings.when(fileInserted)
                .then(new Image(getClass().getResourceAsStream("/com/g4d/sismoinfo/valid.png"),25, 25, true,false))
                .otherwise(new Image(getClass().getResourceAsStream("/com/g4d/sismoinfo/notValid.png"),25, 25, true,false)));
    }

    @FXML
    protected void DateAction (ActionEvent event){
        DatePicker sourceOfEvent = (DatePicker) event.getSource();
        System.out.println(sourceOfEvent.getValue());
    }

//    @FXML
//    private void handleInsertAction(ActionEvent event) {
//        fileInsert = true;
//
//        // Mettre à jour l'image en "valid.png"
//        String imagePath = "file:/Users/maxime/Documents/SismoInfo/src/main/resources/com/g4d/sismoinfo/valid.png";
//        Image image = new Image(imagePath);
//        imageView.setImage(image);
//
//        // Définir la taille de l'image en utilisant une règle CSS
//        imageView.setFitWidth(24); // Remplacez la valeur par la largeur souhaitée
//        imageView.setFitHeight(24); // Remplacez la valeur par la hauteur souhaitée
//        imageView.setPreserveRatio(true); // Conserver le ratio d'aspect de l'image
//    }

    @FXML
    private void handleViewChange(ActionEvent event) {
        ViewLoaders.loadView(event);
    }

   @FXML
    private void handleFiltersApplicationAction(ActionEvent event) throws IOException {
//            Button button = (Button) event.getSource();
//            Stage stage = (Stage) button.getScene().getWindow();
//
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/map.fxml"));
//            Parent mapRoot = fxmlLoader.load();
//            Scene mapScene = new Scene(mapRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
//            stage.setScene(mapScene);
//            stage.show();
            database.getFilteredData().setPredicate(database.getAllPredicates());
    }
}
