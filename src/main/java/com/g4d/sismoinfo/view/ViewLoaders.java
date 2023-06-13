package com.g4d.sismoinfo.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is responsible for loading different views in the application.
 * It includes methods to create and switch between Home view and Dashboard view.
 */
public class ViewLoaders {
    private static Screen screen = Screen.getPrimary();
    private static Rectangle2D bounds = screen.getVisualBounds();
    private final static double WINDOW_WIDTH = bounds.getWidth(); // Largeur de la fenêtre
    private final static double WINDOW_HEIGHT = bounds.getHeight()-20; // Hauteur de la fenêtre
    private static Scene HomeView = createHomeview();
    private static Scene DashboardView = createDashboardview();
    private static Scene MapView = createMapview();

    /**
     * This method creates and returns the Home view Scene.
     * It loads the FXML file and sets up the scene with the window dimensions.
     *
     * @return The created Home view scene.
     * @throws RuntimeException If the FXML file cannot be loaded.
     */
    private static Scene createHomeview() {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewLoaders.class.getResource("/com/g4d/sismoinfo/home.fxml"));
        Parent homeRoot = null;
        try {
            homeRoot = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(homeRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        return scene;
    }

    /**
     * This method creates and returns the Dashboard view Scene.
     * It loads the FXML file and sets up the scene with the window dimensions.
     *
     * @return The created Dashboard view scene.
     * @throws RuntimeException If the FXML file cannot be loaded.
     */
    private static Scene createDashboardview() {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewLoaders.class.getResource("/com/g4d/sismoinfo/Fxml/dashboard.fxml"));
        Parent dashboardRoot = null;
        try {
            dashboardRoot = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(dashboardRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        return scene;
    }

    private static Scene createMapview() {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewLoaders.class.getResource("/com/g4d/sismoinfo/map.fxml"));
        Parent dashboardRoot = null;
        try {
            dashboardRoot = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(dashboardRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        return scene;
    }

    public static Scene getHomeView() {
        return HomeView;
    }

    public static Scene getDashboardView() {
        return DashboardView;
    }

    public static Scene getMapView() {
        return MapView;
    }

    public static void loadView(ActionEvent event){
        Button sourceOfEvent = (Button) event.getSource();
        Stage stage = (Stage) sourceOfEvent.getScene().getWindow();
        if (sourceOfEvent.getUserData().equals("home")) {
            Scene scene = ViewLoaders.getHomeView();
            stage.setScene(scene);
        } else if (sourceOfEvent.getUserData().equals("map")) {
            Scene scene = ViewLoaders.getMapView();
            stage.setScene(scene);
        } else if (sourceOfEvent.getUserData().equals("dashboard")) {
            Scene scene = ViewLoaders.getDashboardView();
            stage.setScene(scene);
        }
        stage.show();
    }
}
