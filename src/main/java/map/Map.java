package map;
import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Map extends Application {

    @Override
    public void start(Stage stage) {

        /* Définit la plate-forme pour éviter "javafx.platform is not defined" */
        System.setProperty("javafx.platform", "desktop");

        /*
         * Définit l'user agent pour éviter l'exception
         * "Server returned HTTP response code: 403"
         */
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        VBox root = new VBox();

        /* Création de la carte Gluon JavaFX */
        MapView mapView = new MapView();

        /* Création du point avec latitude et longitude */
        MapPoint mapPoint = new MapPoint(46.227638, 2.213749);

        /* Création et ajoute une couche à la carte */

        MapLayer mapLayer = new CustomPinLayer(mapPoint);
        mapView.addLayer(mapLayer);

        /* Zoom de 5 */
        mapView.setZoom(5);

        /* Centre la carte sur le point */
        mapView.flyTo(0, mapPoint, 0.1);

        root.getChildren().add(mapView);

        /*
         * IMPORTANT mettre la taille de la fenêtre pour éviter l'erreur
         * java.lang.OutOfMemoryError
         */
        Scene scene = new Scene(root, 640, 480);

        stage.setScene(scene);
        stage.show();

    }

}