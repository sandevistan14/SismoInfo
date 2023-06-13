package com.g4d.sismoinfo.view;

import com.g4d.sismoinfo.model.earthquakedata.Earthquake;
import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class CustomLayerMap extends MapLayer {
    private MapPoint mapPoint;
    private Circle circle;

    public CustomLayerMap(Earthquake earthquake) {
        mapPoint = new MapPoint(earthquake.getLatitude(),earthquake.getLongitude());

        /* Cercle rouge de taille 5 */
        this.circle = new Circle(5, getColorFromIntensity(earthquake.getEpicentralIntensity()));

        /* Ajoute le cercle au MapLayer */
        this.getChildren().add(circle);
    }

    public Color getColorFromIntensity(double intensity){
        if (intensity == 2 || intensity == 2.5)
            return Color.GREY;
        else if (intensity == 3 ||intensity == 3.5)
            return Color.CYAN;
        else if (intensity == 4 ||intensity == 4.5)
            return Color.GREEN;
        else if (intensity == 5 ||intensity == 5.5)
            return Color.YELLOW;
        else if (intensity == 6 ||intensity == 6.5)
            return Color.ORANGE;
        else if (intensity == 7 ||intensity == 7.5)
            return Color.RED;
        else if (intensity == 8 ||intensity == 8.5)
            return Color.PURPLE;
        else if (intensity == 9 ||intensity == 9.5)
            return Color.DARKMAGENTA;
        return Color.BLUE;
    }

    /* La fonction est appelée à chaque rafraichissement de la carte */
    @Override
    protected void layoutLayer() {
        /* Conversion du MapPoint vers Point2D */
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        /* Déplace le cercle selon les coordonnées du point */
        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
        circle.setOnMouseClicked(e -> {System.out.println("test");});
    }
}
