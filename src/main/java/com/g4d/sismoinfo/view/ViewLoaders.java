package com.g4d.sismoinfo.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

import java.io.IOException;

public class ViewLoaders {
    private static Screen screen = Screen.getPrimary();
    private static Rectangle2D bounds = screen.getVisualBounds();
    private final static double WINDOW_WIDTH = bounds.getWidth(); // Largeur de la fenêtre
    private final static double WINDOW_HEIGHT = bounds.getHeight()-20; // Hauteur de la fenêtre
    private static Scene HomeView = createHomeview();
    private static Scene DashboardView = createDashboardview();
//    private static Scene MapView = createMapview();


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

    private static Scene createDashboardview() {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewLoaders.class.getResource("/com/g4d/sismoinfo/dashboard.fxml"));
        Parent dashboardRoot = null;
        try {
            dashboardRoot = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(dashboardRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        return scene;
    }

//    private static Scene createMapview() {
//        FXMLLoader fxmlLoader = new FXMLLoader(ViewLoaders.class.getResource("/com/g4d/sismoinfo/map.fxml"));
//        Parent dashboardRoot = null;
//        try {
//            dashboardRoot = fxmlLoader.load();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Scene scene = new Scene(dashboardRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
//        return scene;
//    }

    public static Scene getHomeView() {
        return HomeView;
    }

    public static Scene getDashboardView() {
        return DashboardView;
    }

//    public static Scene getMapView() {
//        return MapView;
//    }
}
