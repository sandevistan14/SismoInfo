package com.g4d.sismoinfo.view;

import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TestView implements FxmlView<TestViewModel>, Initializable {

    @FXML
    private Label hwLabel;

    @FXML
    private Button hwBtn;

    @InjectViewModel
    private TestViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hwLabel.textProperty().bind(viewModel.hwTextProperty());
        hwBtn.onActionProperty().bind(viewModel.hwBtnActionEventProperty());
    }
}