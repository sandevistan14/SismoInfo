module com.g4d.sismoinfo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires org.apache.commons.csv;
    requires com.gluonhq.maps;

    opens com.g4d.sismoinfo to javafx.fxml;
    exports com.g4d.sismoinfo;
    exports com.g4d.sismoinfo.earthquakedata;
    opens com.g4d.sismoinfo.earthquakedata to javafx.fxml;
    exports map;
    opens map to javafx.fxml;
}