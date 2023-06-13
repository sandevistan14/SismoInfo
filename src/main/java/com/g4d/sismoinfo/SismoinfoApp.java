package com.g4d.sismoinfo;

import com.g4d.sismoinfo.view.ViewLoaders;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class SismoinfoApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(SismoinfoApp.class.getResource("/com/g4d/sismoinfo/map.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH,WINDOW_HEIGHT );
        System.setProperty("javafx.platform", "desktop");
        Scene scene = ViewLoaders.getHomeView();
        stage.setTitle("Sismoinfo");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
