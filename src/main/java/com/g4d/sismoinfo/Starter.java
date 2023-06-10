package com.g4d.sismoinfo;

import com.g4d.sismoinfo.view.*;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application{
    public static void main(String...args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("Hello World Application");

        ViewTuple<TestView, TestViewModel> viewTuple = FluentViewLoader.fxmlView(TestView.class).load();

        Parent root = viewTuple.getView();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
