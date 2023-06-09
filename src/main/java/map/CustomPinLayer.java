package map;
import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CustomPinLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final Circle circle;


    public CustomPinLayer(MapPoint mapPoint) {
        this.mapPoint = mapPoint;

        /* Cercle rouge de taille 5 */
        this.circle = new Circle(5, Color.RED);

        /* Ajoute le cercle au MapLayer */
        this.getChildren().add(circle);
    }


    @Override
    protected void layoutLayer() {

        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
    }
}