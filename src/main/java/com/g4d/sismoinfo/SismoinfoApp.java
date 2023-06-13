package com.g4d.sismoinfo;

import com.g4d.sismoinfo.view.ViewLoaders;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class is the main entry point for the Sismoinfo application.
 */
public class SismoinfoApp extends Application {

    /**
     * This method is the main entry point for all JavaFX applications.
     * It sets the scene for the stage and displays the home view.
     *
     * @param stage The primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched from a browser.
     *
     * @throws IOException If the fxml file for the scene cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = ViewLoaders.getHomeView();
        stage.setTitle("Sismoinfo");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    /**
     * The main() launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
