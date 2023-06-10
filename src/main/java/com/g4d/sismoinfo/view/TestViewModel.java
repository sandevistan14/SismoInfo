package com.g4d.sismoinfo.view;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TestViewModel implements ViewModel {
    private StringProperty hwText = new SimpleStringProperty("");
    private ObjectProperty<EventHandler<ActionEvent>> hwBtnActionEvent = new SimpleObjectProperty<>();

    public TestViewModel() {
        hwBtnActionEvent.set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hwTextProperty().set("Hello World !");
            }
        });
    }
    public String getHwText() {
        return hwText.get();
    }

    public StringProperty hwTextProperty() {
        return hwText;
    }

    public void setHwText(String hwText) {
        this.hwText.set(hwText);
    }

    public EventHandler<ActionEvent> getHwBtnActionEvent() {
        return hwBtnActionEvent.get();
    }

    public ObjectProperty<EventHandler<ActionEvent>> hwBtnActionEventProperty() {
        return hwBtnActionEvent;
    }

    public void setHwBtnActionEvent(EventHandler<ActionEvent> hwBtnActionEvent) {
        this.hwBtnActionEvent.set(hwBtnActionEvent);
    }
}
