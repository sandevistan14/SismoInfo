package com.g4d.sismoinfo;

import javafx.fxml.FXML;
import de.saxsys.mvvmfx.FxmlView;
import javafx.scene.control.Label;

public class TestView implements FxmlView<TestViewModel>{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}