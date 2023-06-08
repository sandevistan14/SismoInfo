package map;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Affiche une épingle sur la carte */
public class CustomPinLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final ImageView mapPinImageView;

    private static final int PIN_WIDTH = 15, PIN_HEIGHT = 20;

    /**
     * @param mapPoint le point (latitude et longitude) où afficher l'épingle
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomPinLayer(MapPoint mapPoint) {
        this.mapPoint = mapPoint;

        Image image = new Image("map-pin.png", PIN_WIDTH, PIN_HEIGHT, false, false);
        this.mapPinImageView = new ImageView(image);

        /* Ajoute l'épingle au MapLayer */
        this.getChildren().add(this.mapPinImageView);
    }

    /* La fonction est appelée à chaque rafraichissement de la carte */
    @Override
    protected void layoutLayer() {
        /* Conversion du MapPoint vers Point2D */
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        /* Déplace l'épingle selon les coordonnées du point */
        mapPinImageView.setTranslateX(point2d.getX() - (PIN_WIDTH / 2));
        mapPinImageView.setTranslateY(point2d.getY() - PIN_HEIGHT);
    }
}