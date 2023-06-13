package com.g4d.sismoinfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The SismoinfoApp class extends the Application class and is the main entry point for any JavaFX application.
 * It loads the home scene when the application starts.
 *
 */
public class SismoinfoApp extends Application {

    /**
     * An instance of the primary screen device.
     */
    Screen screen = Screen.getPrimary();

    /**
     * Visual bounds of the primary screen device.
     * This is used to adapt the application window to the current screen size.
     */
    Rectangle2D bounds = screen.getVisualBounds();

    /**
     * The width of the application window.
     * It is set to the width of the primary screen.
     */
    private final double WINDOW_WIDTH = bounds.getWidth(); // Window width

    /**
     * The height of the application window.
     * It is set to the height of the primary screen minus 20 to account for the system task bar.
     */
    private final double WINDOW_HEIGHT = bounds.getHeight()-20; // Window height

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned, and after the system is ready for the application to begin running.
     * NOTE: This method is called on the JavaFX Application Thread.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws IOException if the location of the FXML file cannot be resolved.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SismoinfoApp.class.getResource("/com/g4d/sismoinfo/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH,WINDOW_HEIGHT );
        stage.setTitle("Sismoinfo");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX applications.
     * main() serves only as fallback in case the application can not be launched through deployment artifacts, e.g., in IDEs with limited FX support.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
