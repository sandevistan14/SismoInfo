package com.g4d.sismoinfo.view;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A custom MapLayer that represents a pin on a map.
 */
public class CustomPinLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final Circle circle;

    /**
     * Constructs a CustomPinLayer with the specified MapPoint.
     *
     * @param mapPoint The MapPoint representing the location of the pin.
     */
    public CustomPinLayer(MapPoint mapPoint) {
        this.mapPoint = mapPoint;

        /* Cercle rouge de taille 5 */
        this.circle = new Circle(5, Color.RED);

        /* Ajoute le cercle au MapLayer */
        this.getChildren().add(circle);
    }

    /**
     * Positions the circle on the map based on the map point's coordinates.
     */
    @Override
    protected void layoutLayer() {

        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
    }
}