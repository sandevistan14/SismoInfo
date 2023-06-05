module com.g4d.sismoinfo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens com.g4d.sismoinfo to javafx.fxml;
    exports com.g4d.sismoinfo;
}