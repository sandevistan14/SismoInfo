package com.g4d.sismoinfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class SismoinfoApp extends Application {
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    private final double WINDOW_WIDTH = bounds.getWidth(); // Largeur de la fenêtre
    private final double WINDOW_HEIGHT = bounds.getHeight()-20; // Hauteur de la fenêtre
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SismoinfoApp.class.getResource("/com/g4d/sismoinfo/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH,WINDOW_HEIGHT );
        stage.setTitle("Sismoinfo");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
