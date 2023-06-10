module com.g4d.sismoinfo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires org.apache.commons.csv;
    requires de.saxsys.mvvmfx;

    opens com.g4d.sismoinfo to javafx.fxml,de.saxsys.mvvmfx;
    exports com.g4d.sismoinfo;
    exports com.g4d.sismoinfo.earthquakedata;
    opens com.g4d.sismoinfo.earthquakedata to javafx.fxml;
    exports com.g4d.sismoinfo.view;
    opens com.g4d.sismoinfo.view to de.saxsys.mvvmfx, javafx.fxml;
}