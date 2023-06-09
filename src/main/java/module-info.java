module com.g4d.sismoinfo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires org.apache.commons.csv;
    requires javafx.graphics;

    opens com.g4d.sismoinfo to javafx.fxml;
    exports com.g4d.sismoinfo;
    exports com.g4d.sismoinfo.model;
    opens com.g4d.sismoinfo.model to javafx.fxml;
    exports com.g4d.sismoinfo.view;
    opens com.g4d.sismoinfo.view to javafx.fxml;
}