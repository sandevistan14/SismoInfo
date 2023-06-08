package com.g4d.sismoinfo;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    private void handleMenuAction(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        String menuText = menuItem.getText();

        Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();

        if (menuText.equals("Accueil")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/home.fxml"));
            Parent homeRoot = fxmlLoader.load();
            Scene scene = new Scene(homeRoot, 320, 240);
            stage.setScene(scene);
        } else if (menuText.equals("Carte")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/map.fxml"));
            Parent mapRoot = fxmlLoader.load();
            Scene scene = new Scene(mapRoot, 320, 240);
            stage.setScene(scene);
        } else if (menuText.equals("Dashboard")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/g4d/sismoinfo/dashboard.fxml"));
            Parent dashboardRoot = fxmlLoader.load();
            Scene scene = new Scene(dashboardRoot, 320, 240);
            stage.setScene(scene);
        }
        stage.show();
    }
}
