package map;
import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Map extends Application {

    @Override
    public void start(Stage stage) {


        System.setProperty("javafx.platform", "desktop");


        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        VBox root = new VBox();


        MapView mapView = new MapView();

        MapPoint centre = new MapPoint(46.227638, 2.213749);


        mapView.setOnMouseClicked(e -> {
            MapPoint centerPoint = mapView.getCenter();

            double centerLat = centerPoint.getLatitude();
            double centerLon = centerPoint.getLongitude();
            double zoom = mapView.getZoom();

            double clickX = e.getX() - mapView.getWidth() / 2;
            double clickY = e.getY() - mapView.getHeight() / 2;

            double scale = Math.pow(2, zoom);

            double dLon = clickX / scale;
            double dLat = clickY / scale;

            double newLat = centerLat - dLat;
            double newLon = centerLon + dLon;

            MapPoint clickedPoint = new MapPoint(newLat, newLon);

            System.out.println("Clicked latitude: " + newLat + ", longitude: " + newLon);
            MapLayer mapLayer = new CustomPinLayer(clickedPoint);
            mapView.addLayer(mapLayer);
        });



        mapView.setZoom(5);


        mapView.flyTo(0, centre , 0.1);

        root.getChildren().add(mapView);

        Scene scene = new Scene(root, 640, 480);

        stage.setScene(scene);
        stage.show();

    }

}